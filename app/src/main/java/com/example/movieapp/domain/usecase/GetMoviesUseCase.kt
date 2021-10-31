package com.example.movieapp.domain.usecase

import androidx.paging.PagingData
import com.example.movieapp.common.BaseUseCase
import com.example.movieapp.common.DefParams
import com.example.movieapp.data.model.GetMoviesResponse
import com.example.movieapp.data.model.MovieItemData
import com.example.movieapp.domain.model.MovieItemDomain
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetMoviesUseCase constructor(private val moviesRepository: MoviesRepository) :
    BaseUseCase<DefParams, Flow<PagingData<MovieItemDomain>>> {

    override suspend fun execute(params: DefParams): Flow<PagingData<MovieItemDomain>> {
        return moviesRepository.getPaginatedMovies()
    }
}