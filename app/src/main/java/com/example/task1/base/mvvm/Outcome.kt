package com.example.task1.base.mvvm

import java.io.Serializable

sealed class Outcome<T> : Serializable{

    data class Progress<T>(var loading: Boolean) : Outcome<T>(), Serializable
    data class Success<T>(var data: T) : Outcome<T>(), Serializable
    data class Next<T>(var data: T) : Outcome<T>(), Serializable
    data class Failure<T>(val e: Throwable) : Outcome<T>(), Serializable

    companion object {
        fun <T> loading(isLoading: Boolean): Outcome<T> = Progress(isLoading)

        fun <T> success(data: T): Outcome<T> = Success(data)

        fun <T> next(data: T): Outcome<T> = Next(data)

        fun <T> failure(e: Throwable): Outcome<T> = Failure(e)

    }

}