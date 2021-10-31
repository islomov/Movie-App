package com.example.movieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.movieapp.common.DefParams
import com.example.movieapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.ui.mapper.toUI
import com.example.movieapp.ui.model.MovieItemUI
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MoviesViewModel constructor(private val getMoviesUseCase: GetMoviesUseCase): ViewModel() {

    lateinit var moviesFlow : Flow<PagingData<MovieItemUI>>

    fun setUpPaging() {
        viewModelScope.launch {
            moviesFlow = getMoviesUseCase
                .execute(DefParams())
                .map { paging -> paging.map { it.toUI() } }
        }
    }


}