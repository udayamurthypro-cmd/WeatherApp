package com.uday.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uday.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * [ViewModel] responsible for managing UI state and fetching weather data for selected locations.
 *
 * Communicates with [WeatherRepository] and exposes state via reactive [StateFlow] streams.
 */
@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val _locations = MutableStateFlow<List<String>>(emptyList())
    val locations: StateFlow<List<String>> = _locations.asStateFlow()

    private val _selectedLocation = MutableStateFlow<String?>(null)
    val selectedLocation: StateFlow<String?> = _selectedLocation.asStateFlow()

    init {
        loadLocations()
    }

    /**
     * Initializes location list from repository and loads weather for the default city.
     */
    private fun loadLocations() {
        viewModelScope.launch {
            val locationList = weatherRepository.getAllLocations()
            _locations.value = locationList

            if (locationList.isNotEmpty()) {
                val initialLocation = locationList[0]
                _selectedLocation.value = initialLocation
                loadWeatherForLocation(initialLocation)
            }
        }
    }

    /**
     * Loads weather metrics for a given [location].
     */
    private fun loadWeatherForLocation(location: String) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val weatherData = weatherRepository.getWeatherForLocation(location)
                _uiState.value = WeatherUiState.Success(weatherData)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.localizedMessage ?: "Failed to fetch weather data")
            }
        }
    }

    /**
     * Updates the active location selection and fetches its weather forecast.
     *
     * @param location City name selected by the user.
     */
    fun selectLocation(location: String) {
        if ((_selectedLocation.value == location) && (_uiState.value is WeatherUiState.Success)) {
            return
        }
        _selectedLocation.value = location
        loadWeatherForLocation(location)
    }

    /**
     * Refreshes weather forecast for the currently active location.
     */
    fun refreshCurrentLocation() {
        _selectedLocation.value?.let { location ->
            loadWeatherForLocation(location)
        }
    }
}
