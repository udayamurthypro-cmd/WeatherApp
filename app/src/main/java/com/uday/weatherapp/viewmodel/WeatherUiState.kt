package com.uday.weatherapp.viewmodel

import com.uday.weatherapp.model.WeatherInfo

/**
 * Represents the UI state for the weather display screen.
 */
sealed interface WeatherUiState {

    /**
     * Indicates that weather data is currently being fetched.
     */
    data object Loading : WeatherUiState

    /**
     * Represents successfully loaded weather details.
     *
     * @property weatherInfo The retrieved [WeatherInfo] object.
     */
    data class Success(val weatherInfo: WeatherInfo) : WeatherUiState

    /**
     * Represents an error state encountered while loading weather information.
     *
     * @property errorMessage Descriptive error details to display to the user.
     */
    data class Error(val errorMessage: String) : WeatherUiState
}
