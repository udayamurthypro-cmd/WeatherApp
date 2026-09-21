# 🌤️ WeatherApp - Android (Hilt & Jetpack Compose)

![Android](https://img.shields.io/badge/Platform-Android-green.svg)
![Kotlin](https://img.shields.io/badge/Language-Kotlin-purple.svg)
![Jetpack Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg)
![Hilt](https://img.shields.io/badge/DI-Hilt-orange.svg)
![Material 3](https://img.shields.io/badge/Design-Material%203-red.svg)

A modern, highly polished Android Weather Application showcasing modern Android App Architecture best practices using **Jetpack Compose**, **Dagger Hilt**, **Kotlin Coroutines / Flow**, and **Material Design 3**.

---

## 🚀 Key Features

- **Dynamic Weather Display**: Real-time forecast updates with condition-based gradient cards (Sunny, Rainy, Cloudy, Clear Sky).
- **Comprehensive Metrics**: Displays temperature, "Feels Like", High/Low range, Humidity, Wind Speed (km/h), and UV Index.
- **Location Selector**: Horizontally scrollable filter chips with quick search filtering for instant location switching.
- **State-Driven UI**: Reactive state management with `StateFlow` and `collectAsStateWithLifecycle()` covering `Loading`, `Success`, and `Error` states with smooth `Crossfade` animations.
- **Material 3 Design**: Fully responsive, edge-to-edge UI supporting Light and Dark theme modes dynamically.

---

## 🏛️ Architecture & Tech Stack

This project strictly follows the **Modern Android Architecture Guide** recommended by Google (MVVM & Clean Architecture).

- **UI Layer**: Built entirely with **Jetpack Compose** & **Material 3**.
- **State Management**: **ViewModel** + Kotlin **`StateFlow`**.
- **Dependency Injection**: **Dagger Hilt** (`@HiltAndroidApp`, `@AndroidEntryPoint`, `@HiltViewModel`, `@Module`, `@Binds`).
- **Asynchronous Execution**: **Kotlin Coroutines** & **Flow**.
- **Lifecycle Integration**: `androidx.lifecycle:lifecycle-runtime-compose` for lifecycle-aware state collection.

### 📦 Package Structure

```
com.uday.weatherapp/
│
├── di/                     # Dependency Injection (Hilt Application & Modules)
│   ├── WeatherApplication.kt
│   └── RepositoryModule.kt
│
├── model/                  # Domain Data Models
│   └── WeatherInfo.kt
│
├── repository/             # Data Layer (Repository Interface & Implementation)
│   ├── WeatherRepository.kt
│   └── WeatherRepositoryImpl.kt
│
├── viewmodel/              # Presentation State Management
│   ├── WeatherUiState.kt
│   └── WeatherViewModel.kt
│
├── screens/                # Jetpack Compose UI Views
│   ├── WeatherScreen.kt
│   ├── WeatherInfoDisplay.kt
│   └── LocationSelector.kt
│
└── ui/theme/               # Material 3 Custom Styling & Typography
    ├── Color.kt
    ├── Theme.kt
    └── Type.kt
```

---

## 🛠️ Building & Running

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/WeatherApp.git
   ```
2. Open the project in **Android Studio (Ladybug or later)**.
3. Sync Gradle dependencies:
   ```bash
   ./gradlew build
   ```
4. Run the `:app` module on an Android Device or Emulator (API 24+).

---

## 📜 License

```
Copyright 2026 Uday

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
