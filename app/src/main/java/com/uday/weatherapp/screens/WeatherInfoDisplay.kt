package com.uday.weatherapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.DeviceThermostat
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Thunderstorm
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.uday.weatherapp.model.WeatherInfo
import com.uday.weatherapp.ui.theme.ClearGradientEnd
import com.uday.weatherapp.ui.theme.ClearGradientStart
import com.uday.weatherapp.ui.theme.CloudyGradientEnd
import com.uday.weatherapp.ui.theme.CloudyGradientStart
import com.uday.weatherapp.ui.theme.RainyGradientEnd
import com.uday.weatherapp.ui.theme.RainyGradientStart
import com.uday.weatherapp.ui.theme.SunnyGradientEnd
import com.uday.weatherapp.ui.theme.SunnyGradientStart

/**
 * Displays comprehensive weather metrics including current temperature, condition hero card,
 * and a grid of secondary metrics (Humidity, Wind Speed, UV Index, Feels Like).
 *
 * @param weatherInfo The [WeatherInfo] dataset to render.
 */
@Composable
fun WeatherInfoDisplay(
    weatherInfo: WeatherInfo,
    modifier: Modifier = Modifier
) {
    val gradientColors = rememberWeatherGradient(weatherInfo.description)
    val weatherIcon = rememberWeatherIcon(weatherInfo.description)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Hero Weather Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(28.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Brush.linearGradient(gradientColors))
                    .padding(24.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(
                                    text = weatherInfo.location,
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                                Text(
                                    text = weatherInfo.country,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }
                        }

                        // Weather Condition Icon Badge
                        Box(
                            modifier = Modifier
                                .size(56.dp)
                                .background(Color.White.copy(alpha = 0.2f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = weatherIcon,
                                contentDescription = weatherInfo.description,
                                tint = Color.White,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Temperature Display
                    Text(
                        text = "${weatherInfo.temperature}°C",
                        fontSize = 58.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White
                    )

                    Text(
                        text = weatherInfo.description,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White.copy(alpha = 0.95f)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // High/Low & Feels Like Row
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "H: ${weatherInfo.highTemp}°  L: ${weatherInfo.lowTemp}°",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                        Text(
                            text = "•",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "Feels like ${weatherInfo.feelsLike}°C",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.85f)
                        )
                    }
                }
            }
        }

        // Additional Weather Metrics Grid
        WeatherMetricsGrid(weatherInfo = weatherInfo)
    }
}

/**
 * Renders a 2x2 grid of key weather parameter cards.
 */
@Composable
private fun WeatherMetricsGrid(weatherInfo: WeatherInfo) {
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricCard(
                title = "Humidity",
                value = "${weatherInfo.humidity}%",
                icon = Icons.Default.WaterDrop,
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                title = "Wind Speed",
                value = "${weatherInfo.windSpeedKmH} km/h",
                icon = Icons.Default.Air,
                modifier = Modifier.weight(1f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            MetricCard(
                title = "UV Index",
                value = weatherInfo.uvIndex.toString(),
                icon = Icons.Default.WbSunny,
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                title = "Feels Like",
                value = "${weatherInfo.feelsLike}°C",
                icon = Icons.Default.DeviceThermostat,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

/**
 * Individual metric display card used in [WeatherMetricsGrid].
 */
@Composable
private fun MetricCard(
    title: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = value,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        }
    }
}

/**
 * Helper to select linear gradient colors based on weather description.
 */
@Composable
private fun rememberWeatherGradient(description: String): List<Color> {
    return when {
        description.contains("Rain", ignoreCase = true) -> listOf(RainyGradientStart, RainyGradientEnd)
        description.contains("Cloud", ignoreCase = true) || description.contains("Fog", ignoreCase = true) ->
            listOf(CloudyGradientStart, CloudyGradientEnd)
        description.contains("Sunny", ignoreCase = true) -> listOf(SunnyGradientStart, SunnyGradientEnd)
        else -> listOf(ClearGradientStart, ClearGradientEnd)
    }
}

/**
 * Helper to select an appropriate vector icon for weather descriptions.
 */
@Composable
private fun rememberWeatherIcon(description: String): ImageVector {
    return when {
        description.contains("Sunny", ignoreCase = true) || description.contains("Clear", ignoreCase = true) -> Icons.Default.WbSunny
        description.contains("Rain", ignoreCase = true) -> Icons.Default.Thunderstorm
        description.contains("Cloud", ignoreCase = true) || description.contains("Fog", ignoreCase = true) -> Icons.Default.Cloud
        else -> Icons.Default.WbSunny
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherInfoDisplayPreview() {
    MaterialTheme {
        WeatherInfoDisplay(
            weatherInfo = WeatherInfo(
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
            )
        )
    }
}
