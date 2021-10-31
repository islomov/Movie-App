package com.example.movieapp.koin

import com.example.movieapp.data.paging.MoviesPagingSource
import com.example.movieapp.data.paging.SimilarMoviesPagingSource
import org.koin.dsl.module

val pagingSourceModule = module {
    factory {
        MoviesPagingSource(get())
    }
    factory {
        SimilarMoviesPagingSource(get())
    }
}