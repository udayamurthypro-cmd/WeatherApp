package com.uday.weatherapp.viewmodel

import com.uday.weatherapp.model.WeatherInfo

sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val weatherInfo: WeatherInfo) : WeatherUiState()
    data class Error(val errorMessage: String) : WeatherUiState()
}