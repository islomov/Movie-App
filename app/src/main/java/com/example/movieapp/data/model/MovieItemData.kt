package com.example.movieapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieItemData(
    val backdropPath: String?,
    val firstAirDate: String?,
    val id: Long,
    val originalLanguage: String?,
    val originalName: String?,
    val overview: String?,
    val posterPath: String?,
    val voteAverage: Double?
): Parcelable