package com.albayemre.weatherapp.network

import com.albayemre.weatherapp.model.Weather
import com.albayemre.weatherapp.util.Constant

import retrofit2.http.GET
import retrofit2.http.Query




//Bunu retrofit otomatik implements ediyor
interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("appid") apiKey: String = Constant.API_KEY,
        @Query("units") units: String = "metric" // opsiyonel: sıcaklık için °C
    ): Weather
}
