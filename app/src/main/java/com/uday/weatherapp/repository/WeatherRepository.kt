package com.uday.weatherapp.repository

import com.uday.weatherapp.model.WeatherInfo

interface WeatherRepository {

    fun getWeatherForLocation(location: String): WeatherInfo
    fun getAllLocations(): List<String>
}