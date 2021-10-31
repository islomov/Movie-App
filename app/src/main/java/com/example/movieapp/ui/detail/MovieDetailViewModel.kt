package com.example.movieapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movieapp.domain.usecase.GetSimilarMoviesParam
import com.example.movieapp.domain.usecase.GetSimilarMoviesUseCase
import com.example.movieapp.ui.mapper.toUI
import com.example.movieapp.ui.model.MovieItemUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MovieDetailViewModel constructor(
    private val getSimilarMoviesUseCase: GetSimilarMoviesUseCase
) : ViewModel() {

    lateinit var similarMoviesFlow: Flow<PagingData<MovieItemUI>>

    fun setUpSimilarMoviesPaging(tvId: Long?) {
        viewModelScope.launch {
            similarMoviesFlow = getSimilarMoviesUseCase
                .execute(GetSimilarMoviesParam(tvId))
                .map { paging -> paging.map { it.toUI() } }

        }
    }

}