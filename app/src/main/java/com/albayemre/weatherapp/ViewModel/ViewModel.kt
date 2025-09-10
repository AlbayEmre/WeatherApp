package com.albayemre.weatherapp.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.albayemre.weatherapp.data.DataOrException
import com.albayemre.weatherapp.model.Weather
import com.albayemre.weatherapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


//Repository bu view modeli beslicek
@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    val data = mutableStateOf(DataOrException<Weather>(loading = true))

    init { getWeather("Seattle") }

    fun getWeather(city: String) {
        viewModelScope.launch {
            if (city.isBlank()) return@launch
            data.value = data.value.copy(loading = true, e = null)

            val result = repository.getWeather(city)

            // ÖNEMLİ: e'yi de geçiriyoruz
            data.value = data.value.copy(
                data = result.data,
                loading = false,
                e = result.e
            )

        }
    }
}

