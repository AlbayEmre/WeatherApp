package com.albayemre.weatherapp.data

data class DataOrException<T>(
    val data: T? = null,
    val loading: Boolean = false,
    val e: Exception? = null
)
