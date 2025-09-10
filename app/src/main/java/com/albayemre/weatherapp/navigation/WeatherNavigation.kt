package com.albayemre.weatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.albayemre.weatherapp.ui.MainScreen
import com.albayemre.weatherapp.ViewModel.MainViewModel

import com.albayemre.weatherapp.ui.Screens.splash.WeatherSplashScreen

@Composable
fun WeatherNavigation() {
  //  rememberNavController() ekrana geçişleri yöneten NavController nesnesini oluşturur ve val navController = ... ile değişkene atayıp kullanabilmemizi sağlar.
    val navController = rememberNavController() //Bu kısım ile





    NavHost(navController = navController, startDestination = WeatherScreens.SplashScreen.name){
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController=navController)
        }
        composable(WeatherScreens.MainScreen.name){
            val viewModel = hiltViewModel<MainViewModel>()
            MainScreen(navController=navController,viewModel)
        }

    }
}