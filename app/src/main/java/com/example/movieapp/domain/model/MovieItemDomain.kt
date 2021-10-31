package com.example.movieapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItemDomain(
    val backdropPath: String?,
    val firstAirDate: String?,
    val id: Long,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val posterPath: String?,
    val voteAverage: Double?
): Parcelable