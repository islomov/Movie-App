package com.example.movieapp.koin

import com.example.movieapp.BuildConfig
import com.example.movieapp.data.network.ApiService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor
import kotlin.math.log


val networkModule = module {

    single {
        apiService(get())
    }

    single {
        url()
    }


}


private val connectionTimeout = 10000L

fun url() = if(BuildConfig.DEBUG) BuildConfig.API_DEBUG_ENDPOINT else BuildConfig.API_RELEASE_ENDPOINT

fun apiService(url: String): ApiService {

    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY

    val client = OkHttpClient.Builder()
        .connectTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
        .writeTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
        .readTimeout(connectionTimeout, TimeUnit.MILLISECONDS)
        .addInterceptor(logging)

    return Retrofit.Builder()
        .baseUrl(url)
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

}