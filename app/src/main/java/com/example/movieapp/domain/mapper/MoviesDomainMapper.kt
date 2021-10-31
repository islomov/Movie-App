package com.example.movieapp.domain.mapper

import com.example.movieapp.data.model.MovieItemData
import com.example.movieapp.domain.model.MovieItemDomain

fun MovieItemData.toDomainModel(): MovieItemDomain {
    return MovieItemDomain(
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