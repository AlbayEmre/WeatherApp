package com.albayemre.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.albayemre.weatherapp.navigation.WeatherNavigation
import com.albayemre.weatherapp.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint // Hilt için gerekli
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        // Android 12+ splash ekranını ve core-splashscreen kütüphanesini kur
        installSplashScreen()

        super.onCreate(savedInstanceState)

        // Sistem barlarını içerikle uyumlu hale getir
        enableEdgeToEdge()

        setContent {
            WeatherAppTheme {
                WeatherNavigation()
            }
        }
    }
}
