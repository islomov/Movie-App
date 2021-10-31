package com.example.movieapp.ui.movies

import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.common.getPath
import com.example.movieapp.common.glideClear
import com.example.movieapp.common.loadGlide
import com.example.movieapp.common.viewForVH
import com.example.movieapp.ui.model.MovieItemUI

class MoviesAdapter(private val click: ((MovieItemUI?) -> Unit)? = null) :
    PagingDataAdapter<MovieItemUI, MoviesAdapter.MovieViewHolder>(MovieDiffCallBack()) {

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = parent.viewForVH(R.layout.list_movie_item)
        return MovieViewHolder(view)
    }


    inner class MovieViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        // We don't use viewBinding here, cuz it has bug with recyclerviewViewHolder,
        // Even in official doc of google, there is no example of viewBinding with ViewHolder

        val ivPoster = view.findViewById<AppCompatImageView>(R.id.iv_poster)
        val tvTitle = view.findViewById<AppCompatTextView>(R.id.tv_title)
        val tvDesc = view.findViewById<AppCompatTextView>(R.id.tv_desc)
        val tvRating = view.findViewById<AppCompatTextView>(R.id.tv_rating)

        fun bind(movieItem:  MovieItemUI?) {
            ivPoster.glideClear()
            ivPoster.loadGlide(movieItem?.posterPath?.getPath())
            tvTitle.text = movieItem?.originalName
            tvDesc.text = movieItem?.overview
            tvRating.text = "Rating:${movieItem?.voteAverage}"
            itemView.setOnClickListener {
                click?.invoke(movieItem)
            }
        }

    }

    class MovieDiffCallBack : DiffUtil.ItemCallback<MovieItemUI>() {
        override fun areItemsTheSame(
            oldItem:  MovieItemUI,
            newItem:  MovieItemUI
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem:  MovieItemUI,
            newItem:  MovieItemUI
        ): Boolean {
            return oldItem == newItem
        }
    }


}