package com.uday.weatherapp.repository

import com.uday.weatherapp.model.WeatherInfo
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of [WeatherRepository] providing realistic weather data for multiple global locations.
 */
@Singleton
class WeatherRepositoryImpl @Inject constructor() : WeatherRepository {

    /**
     * In-memory mock weather dataset mapping city names to detailed [WeatherInfo].
     */
    private val weatherData = mapOf(
        "New York" to WeatherInfo(
            location = "New York",
            country = "United States",
            temperature = 25,
            feelsLike = 27,
            description = "Sunny",
            highTemp = 28,
            lowTemp = 18,
            humidity = 55,
            windSpeedKmH = 12.5,
            uvIndex = 7
        ),
        "London" to WeatherInfo(
            location = "London",
            country = "United Kingdom",
            temperature = 18,
            feelsLike = 17,
            description = "Cloudy",
            highTemp = 20,
            lowTemp = 12,
            humidity = 78,
            windSpeedKmH = 18.2,
            uvIndex = 4
        ),
        "Tokyo" to WeatherInfo(
            location = "Tokyo",
            country = "Japan",
            temperature = 29,
            feelsLike = 32,
            description = "Rainy",
            highTemp = 31,
            lowTemp = 22,
            humidity = 82,
            windSpeedKmH = 15.0,
            uvIndex = 5
        ),
        "Paris" to WeatherInfo(
            location = "Paris",
            country = "France",
            temperature = 21,
            feelsLike = 21,
            description = "Partly Cloudy",
            highTemp = 23,
            lowTemp = 14,
            humidity = 62,
            windSpeedKmH = 9.8,
            uvIndex = 6
        ),
        "Sydney" to WeatherInfo(
            location = "Sydney",
            country = "Australia",
            temperature = 26,
            feelsLike = 25,
            description = "Clear Sky",
            highTemp = 27,
            lowTemp = 19,
            humidity = 50,
            windSpeedKmH = 16.4,
            uvIndex = 8
        ),
        "Mumbai" to WeatherInfo(
            location = "Mumbai",
            country = "India",
            temperature = 33,
            feelsLike = 38,
            description = "Humid & Sunny",
            highTemp = 35,
            lowTemp = 27,
            humidity = 85,
            windSpeedKmH = 11.0,
            uvIndex = 9
        ),
        "San Francisco" to WeatherInfo(
            location = "San Francisco",
            country = "United States",
            temperature = 17,
            feelsLike = 16,
            description = "Foggy",
            highTemp = 19,
            lowTemp = 11,
            humidity = 80,
            windSpeedKmH = 22.0,
            uvIndex = 3
        )
    )

    override suspend fun getWeatherForLocation(location: String): WeatherInfo {
        // Simulate short network latency for smooth UI loading transitions
        delay(300)
        return weatherData[location]
            ?: WeatherInfo(
                location = location,
                country = "Unknown",
                temperature = 0,
                feelsLike = 0,
                description = "Unavailable",
                highTemp = 0,
                lowTemp = 0,
                humidity = 0,
                windSpeedKmH = 0.0,
                uvIndex = 0
            )
    }

    override suspend fun getAllLocations(): List<String> {
        return weatherData.keys.toList()
    }
}
