package com.uday.weatherapp.di

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Custom [Application] class initializing Hilt dependency injection across the application process.
 */
@HiltAndroidApp
class WeatherApplication : Application()
