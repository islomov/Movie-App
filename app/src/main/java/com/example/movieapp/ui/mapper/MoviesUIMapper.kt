package com.example.movieapp.ui.mapper

import com.example.movieapp.domain.model.MovieItemDomain
import com.example.movieapp.ui.model.MovieItemUI

fun MovieItemDomain.toUI(): MovieItemUI{
    return MovieItemUI(
        this.backdropPath,
        this.firstAirDate,
        this.id,
        this.originalLanguage,
        this.originalName,
        this.overview,
        this.posterPath,
        this.voteAverage
    )
}