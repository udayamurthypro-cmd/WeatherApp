package com.uday.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.uday.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState: StateFlow<WeatherUiState> = _uiState.asStateFlow()

    private val _locations = MutableStateFlow<List<String>>(emptyList())
    val locations: StateFlow<List<String>> = _locations.asStateFlow()

    private val _selectedLocation = MutableStateFlow<String?>("")
    val selectedLocation: StateFlow<String?> = _selectedLocation.asStateFlow()

    init {
        loadLocations()
    }

    private fun loadLocations() {
        viewModelScope.launch {
            val locationList = weatherRepository.getAllLocations()
            _locations.value = locationList

            if (locationList.isNotEmpty()) {
                _selectedLocation.value = locationList[0]
                loadWeatherForLocation(locationList[0])
            }
        }
    }

    private fun loadWeatherForLocation(location: String) {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val weatherData = weatherRepository.getWeatherForLocation(location)
                _uiState.value = WeatherUiState.Success(weatherData)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun selectLocation(location: String) {
        _selectedLocation.value = location
        loadWeatherForLocation(location)
    }
}