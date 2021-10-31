package com.example.movieapp.data.network

import com.example.movieapp.BuildConfig
import com.example.movieapp.common.LANGUAGE_EN
import com.example.movieapp.data.model.GetMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("tv/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int): GetMoviesResponse

    @GET("tv/{tv_id}/similar")
    suspend fun getSimilarMovies(
        @Path("tv_id") tvId: Long?,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("language") language: String = LANGUAGE_EN,
        @Query("page") page: Int): GetMoviesResponse

}

