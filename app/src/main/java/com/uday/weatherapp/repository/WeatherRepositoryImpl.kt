package com.uday.weatherapp.repository

import com.uday.weatherapp.model.WeatherInfo
import jakarta.inject.Inject

class WeatherRepositoryImpl @Inject constructor() : WeatherRepository {

    //Dummy Data for demostration purposes
    private val weatherData = mapOf(
        "New York" to WeatherInfo(location = "New York", temperature = 25, description = "Sunny"),
        "London" to WeatherInfo(location = "London", temperature = 20, description = "Cloudy"),
        "Tokyo" to WeatherInfo(location = "Tokyo", temperature = 30, description = "Rainy"),
        "Paris" to WeatherInfo(location = "Paris", temperature = 22, description = "Snowy"),
        "Sydney" to WeatherInfo(location = "Sydney", temperature = 28, description = "Rainy"))

    override fun getWeatherForLocation(location: String): WeatherInfo {
        return weatherData[location] ?: WeatherInfo(location = "Unknown", temperature = 0, description = "Unknown")
    }

    override fun getAllLocations(): List<String> {
        return weatherData.keys.toList()
    }


}