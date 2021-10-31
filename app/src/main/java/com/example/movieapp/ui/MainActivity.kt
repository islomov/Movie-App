package com.example.movieapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.ui.detail.MovieDetailFragment
import com.example.movieapp.ui.model.MovieItemUI
import com.example.movieapp.ui.movies.MoviesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // TODO: replace with Navigation
        supportFragmentManager.beginTransaction().replace(R.id.fl_root, MoviesFragment()).commit()
    }

    fun navigateToDetail(movieItemUI: MovieItemUI?) {
        supportFragmentManager.beginTransaction()
            .add(R.id.fl_root, MovieDetailFragment.newInstance(movieItemUI))
            .addToBackStack(null).commit()

    }

}