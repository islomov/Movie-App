package com.example.movieapp.domain.usecase

import androidx.paging.PagingData
import com.example.movieapp.common.BaseUseCase
import com.example.movieapp.common.DefParams
import com.example.movieapp.domain.model.MovieItemDomain
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class GetSimilarMoviesUseCase constructor(private val moviesRepository: MoviesRepository) :
    BaseUseCase<GetSimilarMoviesParam, Flow<PagingData<MovieItemDomain>>> {

    override suspend fun execute(params: GetSimilarMoviesParam): Flow<PagingData<MovieItemDomain>> {
        return moviesRepository.getPaginatedSimilarMovies(params.tvId)
    }

}

data class GetSimilarMoviesParam(val tvId: Long?) : DefParams()