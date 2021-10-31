package com.example.movieapp.koin

import com.example.movieapp.ui.detail.MovieDetailViewModel
import com.example.movieapp.ui.movies.MoviesViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MoviesViewModel(get())
    }
    viewModel {
        MovieDetailViewModel(get())
    }
}