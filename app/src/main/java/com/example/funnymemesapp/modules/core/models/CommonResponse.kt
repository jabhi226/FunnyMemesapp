package com.example.funnymemesapp.modules.core.models

sealed class CommonResponse<out T : Any> {

    data class Success<T : Any>(val data: T) : CommonResponse<T>()

    data class Error<T : Any>(val error: String) : CommonResponse<T>()
}