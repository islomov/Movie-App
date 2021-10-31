package com.example.movieapp.common

import java.lang.Exception

interface BaseUseCase<P : DefParams, T> {

    suspend fun execute(params: P): T
}

interface BaseUseCaseCoroutine<P : DefParams, T> {

    suspend fun execute(params: P, success: (T) -> Unit, fail: (Exception) -> Unit)
}

interface BaseUseCaseNoSuspend<P : DefParams, T> {

    fun execute(params: P): T
}

open class DefParams