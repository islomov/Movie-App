package com.example.movieapp.koin

import com.example.movieapp.domain.repository.MoviesRepository
import com.example.movieapp.domain.usecase.GetMoviesUseCase
import com.example.movieapp.domain.usecase.GetSimilarMoviesUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        createGetMoviesUseCase(get())
    }
    factory {
        createGetSimilarMoviesUseCase(get())
    }
}

fun createGetMoviesUseCase(repository: MoviesRepository) = GetMoviesUseCase(repository)

fun createGetSimilarMoviesUseCase(repository: MoviesRepository) = GetSimilarMoviesUseCase(repository)