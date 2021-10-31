package com.example.movieapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.movieapp.common.PER_PAGE_20
import com.example.movieapp.data.paging.MoviesPagingSource
import com.example.movieapp.data.paging.SimilarMoviesPagingSource
import com.example.movieapp.domain.mapper.toDomainModel
import com.example.movieapp.domain.model.MovieItemDomain
import com.example.movieapp.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MoviesRepositoryImpl constructor(
    private val similarMoviesPagingSource: SimilarMoviesPagingSource,
    private val moviesPagingSource: MoviesPagingSource
) : MoviesRepository {

    override suspend fun getPaginatedSimilarMovies(tvId: Long?): Flow<PagingData<MovieItemDomain>> {
        similarMoviesPagingSource.tvId = tvId
        return Pager(
            PagingConfig(pageSize = PER_PAGE_20))
            { similarMoviesPagingSource }
            .flow.map { paging -> paging.map { it.toDomainModel() } }
    }

    override suspend fun getPaginatedMovies(): Flow<PagingData< MovieItemDomain>> {
        return Pager(
            PagingConfig(pageSize = PER_PAGE_20))
            { moviesPagingSource }
            .flow.map { paging -> paging.map { it.toDomainModel() } }
    }

}