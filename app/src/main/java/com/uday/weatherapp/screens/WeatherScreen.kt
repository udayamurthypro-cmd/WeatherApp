package com.uday.weatherapp.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.uday.weatherapp.viewmodel.WeatherUiState
import com.uday.weatherapp.viewmodel.WeatherViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()
    val locations by viewModel.locations.collectAsState()
    val selectedLocation by viewModel.selectedLocation.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Weather App using Hilt") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LocationSelector(
                locations = locations,
                selectedLocation = selectedLocation ?: "",
                onLocationSelected = { viewModel.selectLocation(it) }
            )
            Spacer(modifier = Modifier.height(32.dp))
            when (val state = uiState) {
                is WeatherUiState.Loading -> {
                    CircularProgressIndicator()
                }

                is WeatherUiState.Success -> {
                    WeatherInfoDisplay(weatherInfo = state.weatherInfo)
                }

                is WeatherUiState.Error -> {
                    Text(text = "Error: ${state.errorMessage}")
                }
            }
        }
    }
}
