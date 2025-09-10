package com.albayemre.weatherapp.ui.Screens.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.albayemre.weatherapp.navigation.WeatherScreens
import kotlinx.coroutines.delay
import com.example.weatherapp.R // paket adına göre düzenle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherSplashScreen(navController: NavController) {
    // Animasyon state'i: 0f → 1f
    val scale = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Logo büyüme animasyonu
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )
        delay(1000)
        navController.popBackStack()
        navController.navigate(WeatherScreens.MainScreen.name)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(
            modifier = Modifier
                .size(220.dp) // daha kompakt
                .graphicsLayer {
                    scaleX = scale.value
                    scaleY = scale.value
                },
            shape = CircleShape,
            color = Color.White,
            border = BorderStroke(2.dp, Color.LightGray),
            shadowElevation = 6.dp // hafif gölge
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Logoyu ortala ve boyutlandır
                Image(
                    painter = painterResource(R.drawable.a),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .size(100.dp) // sabit boyut, kayma engellenir
                )

                Spacer(modifier = Modifier.size(12.dp))

                Text(
                    text = "Find the sun?",
                    style = MaterialTheme.typography.titleMedium.copy(
                        color = Color.Gray,
                        fontSize = 18.sp
                    ),
                    modifier = Modifier.alpha(0.7f)
                )
            }
        }
    }
}

