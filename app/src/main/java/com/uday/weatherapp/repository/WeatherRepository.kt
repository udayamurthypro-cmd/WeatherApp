package com.uday.weatherapp.repository

import com.uday.weatherapp.model.WeatherInfo

/**
 * Interface defining operations for fetching weather data and available locations.
 */
interface WeatherRepository {

    /**
     * Retrieves current weather details for a specified [location].
     *
     * @param location Name of the city to fetch weather for.
     * @return [WeatherInfo] containing weather metrics.
     */
    suspend fun getWeatherForLocation(location: String): WeatherInfo

    /**
     * Retrieves the list of all supported weather locations.
     *
     * @return List of location names.
     */
    suspend fun getAllLocations(): List<String>
}
