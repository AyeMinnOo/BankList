package com.amo.motherfinancetest.model

sealed class Result<out T : Any> {
    class Done<out T : Any>(val data: T?) : Result<T>()
    class Loading(val msg: String) : Result<Nothing>()
    class Error(val e: Exception) : Result<Nothing>()
}