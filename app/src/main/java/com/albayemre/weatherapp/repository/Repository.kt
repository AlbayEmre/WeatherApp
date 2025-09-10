package com.albayemre.weatherapp.repository



import com.albayemre.weatherapp.data.DataOrException
import com.albayemre.weatherapp.model.Weather
import com.albayemre.weatherapp.network.WeatherApi
import javax.inject.Inject

// Repository sınıfı: veri kaynağını (API) yönetmek için kullanılır.
// Burada WeatherApi üzerinden hava durumu verilerini çekiyoruz.
class Repository @Inject constructor(
    private val api: WeatherApi
) {
    suspend fun getWeather(cityQuery: String): DataOrException<Weather> {
        return try {
            val response = api.getWeather(cityQuery)
            DataOrException(data = response, loading = false, e = null)
        } catch (e: Exception) {
            DataOrException(data = null, loading = false, e = e)
        }
    }
}
