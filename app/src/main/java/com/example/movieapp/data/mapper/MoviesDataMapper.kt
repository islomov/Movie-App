package com.example.movieapp.data.mapper

import com.example.movieapp.data.model.GetMoviesResponse
import com.example.movieapp.data.model.MovieItemData


fun GetMoviesResponse.MovieItem.toDataModel(): MovieItemData {
    return MovieItemData(
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