package com.example.movieapp.domain.repository

import androidx.paging.PagingData
import com.example.movieapp.data.model.GetMoviesResponse
import com.example.movieapp.data.model.MovieItemData
import com.example.movieapp.domain.model.MovieItemDomain
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getPaginatedSimilarMovies(tvId: Long?): Flow<PagingData<MovieItemDomain>>
    suspend fun getPaginatedMovies(): Flow<PagingData<MovieItemDomain>>
}