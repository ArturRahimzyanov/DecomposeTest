package com.example.decomposetest.data

data class RepositoryResponse<T>(
    val error: Error? = null,
    val success: Success <T>? = null,

){
    data class Error(
        val status: String? = null,
        val code: Int? = null,
        val msg: String? = null,
    )

    data class Success<T> (
        val status: String? = null,
        val data: T? = null
    )
}