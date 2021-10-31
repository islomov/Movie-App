package com.example.movieapp.ui.movies

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.common.getAppropriateMessage
import com.example.movieapp.common.viewForVH

class MoviesAdapterLoadingState(val retry: (()-> Unit)? = null ): LoadStateAdapter<MoviesAdapterLoadingState.LoadingStateViewHolder>()  {

    override fun onBindViewHolder(holder: LoadingStateViewHolder, loadState: LoadState) {
        holder.bindState(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadingStateViewHolder {
        return LoadingStateViewHolder(parent.viewForVH(R.layout.list_item_loading_state))
    }

    inner class LoadingStateViewHolder(view: View) : RecyclerView.ViewHolder(view){

        val btnRetry: Button = view.findViewById(R.id.btn_retry)
        val tvErrorMessage: TextView = view.findViewById(R.id.tv_error_message)
        val pbState: ProgressBar = view.findViewById(R.id.pb_state)

        init {
            btnRetry.setOnClickListener {
                retry?.invoke()
            }
        }

        fun bindState(loadState: LoadState){

            if (loadState is LoadState.Error) {
                tvErrorMessage.text = loadState.error.getAppropriateMessage()
            }

            pbState.isVisible = loadState is LoadState.Loading
            tvErrorMessage.isVisible = loadState !is LoadState.Loading
            btnRetry.isVisible = loadState !is LoadState.Loading
        }

    }
}