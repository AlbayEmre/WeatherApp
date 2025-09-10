@file:OptIn(ExperimentalMaterial3Api::class)

package com.albayemre.weatherapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.albayemre.weatherapp.ViewModel.MainViewModel
import com.albayemre.weatherapp.model.Weather





@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.data.value
    val focus = LocalFocusManager.current

    LaunchedEffect(state.e) {
        state.e?.let { snackbarHostState.showSnackbar(it.message ?: "Bilinmeyen hata") }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("🌤 Weather App", style = MaterialTheme.typography.titleLarge) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            var city by rememberSaveable { mutableStateOf("Seattle") }

            // Arama kutusu
            OutlinedTextField(
                value = city,
                onValueChange = { city = it },
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Şehir giriniz") },
                singleLine = true,
                shape = RoundedCornerShape(12.dp),
                trailingIcon = {
                    if (city.isNotBlank()) {
                        IconButton(onClick = { city = "" }) {
                            Icon(Icons.Default.Clear, contentDescription = "Temizle")
                        }
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = {
                        if (city.isNotBlank()) {
                            viewModel.getWeather(city)
                            focus.clearFocus()
                        }
                    }
                )
            )

            // Butonlar
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = {
                        if (city.isNotBlank()) {
                            viewModel.getWeather(city)
                            focus.clearFocus()
                        }
                    }
                ) {
                    Icon(Icons.Default.Search, contentDescription = "Ara")
                    Spacer(Modifier.width(6.dp))
                    Text("Getir")
                }

                OutlinedButton(
                    modifier = Modifier.weight(1f),
                    onClick = { city = "" }
                ) {
                    Text("Temizle")
                }
            }

            // İçerik
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                ShowData(mainViewModel = viewModel)

                if (state.loading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Composable
fun ShowData(mainViewModel: MainViewModel) {
    val weatherState = mainViewModel.data.value

    when {
        weatherState.loading -> {
            Spacer(Modifier.height(1.dp))
        }
        weatherState.e != null -> {
            Text("⚠️ Hata: ${weatherState.e.message ?: "Bilinmeyen hata"}")
        }
        weatherState.data != null -> {
            WeatherCard(weather = weatherState.data)
        }
        else -> {
            Text("Henüz veri yok. Bir şehir arayın.")
        }
    }
}

@Composable
fun WeatherCard(weather: Weather) {
    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(6.dp)
    ) {
        Column(
            Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(weather.name, style = MaterialTheme.typography.headlineMedium)

            Divider()

            Text("🌡 Sıcaklık: ${weather.main.temp} °C", style = MaterialTheme.typography.bodyLarge)
            Text("🤔 Hissedilen: ${weather.main.feels_like} °C")
            Text("☁️ Durum: ${weather.weather.firstOrNull()?.description ?: "-"}")
            Text("💧 Nem: ${weather.main.humidity}%")
            Text("💨 Rüzgar: ${weather.wind.speed} m/s")
        }
    }
}
