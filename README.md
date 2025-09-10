# 🌦️ WeatherApp

Basit ama güçlü bir **hava durumu uygulaması**.  
Kullanıcılar şehir adı girerek anlık hava durumu bilgisini görüntüleyebilir.  
Uygulama **Jetpack Compose, MVVM, Retrofit, Hilt (DI)** ve **Material 3** ile geliştirilmiştir.  

---

## 🚀 Özellikler
- 🔍 Şehir adına göre hava durumu arama  
- 📡 OpenWeather API üzerinden canlı veri  
- 📝 Sıcaklık, hissedilen sıcaklık, nem, rüzgar hızı, durum bilgisi  
- 🎨 Material 3 modern tasarım  
- ⏳ Splash ekran animasyonu  
- ⚡ MVVM + Repository + UseCase yapısı  
- ♻️ Hilt ile dependency injection  

---

## 🛠️ Kullanılan Teknolojiler
- **Kotlin**  
- **Jetpack Compose** (UI)  
- **Material 3 Components**  
- **Retrofit** (REST API istekleri)  
- **Hilt** (Dependency Injection)  
- **Coroutines + Flow** (Asenkron veri akışı)  
- **MVVM Mimarisi**  

---

## 📂 Proje Yapısı
```bash
app/
└── src/main/java/com/albayemre/weatherapp/
    ├── data
    │   └── DataOrException.kt
    │
    ├── di
    │   └── AppModule.kt
    │
    ├── model
    │   ├── Clouds.kt
    │   ├── Coord.kt
    │   ├── Main.kt
    │   ├── Sys.kt
    │   ├── Weather.kt
    │   ├── WeatherItem.kt
    │   └── Wind.kt
    │
    ├── navigation
    │   ├── WeatherNavigation.kt
    │   └── WeatherScreens.kt
    │
    ├── network
    │   └── WeatherApi.kt
    │
    ├── repository
    │   └── Repository.kt
    │
    ├── ui
    │   ├── Screens
    │   │   ├── main/MainScreen.kt
    │   │   └── splash/WeatherSplashScreen.kt
    │   └── theme (renkler, typography, tema)
    │
    ├── util
    │   └── Constant.kt
    │
    ├── MainActivity.kt
    └── WeatherApp.kt
