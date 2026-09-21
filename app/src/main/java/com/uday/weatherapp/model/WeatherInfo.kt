package com.uday.weatherapp.model

/**
 * Domain data model representing comprehensive weather information for a specific location.
 *
 * @property location The name of the city or region (e.g., "New York").
 * @property country The country name where the location resides.
 * @property temperature The current temperature in degrees Celsius.
 * @property feelsLike The perceived temperature in degrees Celsius.
 * @property description A brief summary of the weather condition (e.g., "Sunny", "Rainy").
 * @property highTemp The highest forecasted temperature for the day in degrees Celsius.
 * @property lowTemp The lowest forecasted temperature for the day in degrees Celsius.
 * @property humidity Relative humidity percentage (0-100%).
 * @property windSpeedKmH Wind speed measurement in kilometers per hour.
 * @property uvIndex Ultraviolet radiation index level (1-11+).
 */
data class WeatherInfo(
    val location: String,
    val country: String = "Global",
    val temperature: Int,
    val feelsLike: Int,
    val description: String,
    val highTemp: Int,
    val lowTemp: Int,
    val humidity: Int,
    val windSpeedKmH: Double,
    val uvIndex: Int
)
