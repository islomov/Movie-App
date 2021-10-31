package com.example.movieapp.koin

import com.example.movieapp.data.paging.MoviesPagingSource
import com.example.movieapp.data.paging.SimilarMoviesPagingSource
import com.example.movieapp.data.repository.MoviesRepositoryImpl
import com.example.movieapp.domain.repository.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory <MoviesRepository> {
        createMoviesRepository(get(),get())
    }
}

fun createMoviesRepository(
    similarMoviesPagingSource: SimilarMoviesPagingSource,
    moviesPagingSource: MoviesPagingSource
): MoviesRepository {
    return MoviesRepositoryImpl(similarMoviesPagingSource, moviesPagingSource)
}