package com.zj.smart.widget
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.serialization.Serializable

@Serializable
sealed interface WeatherInfo {
    @Serializable
    object Loading : WeatherInfo

    @Serializable
    data class Available(
        val placeName: String,
        val currentData: WeatherData,
        val hourlyForecast: List<WeatherData>,
        val dailyForecast: List<WeatherData>
    ) : WeatherInfo

    @Serializable
    data class Unavailable(val message: String) : WeatherInfo
}

@Serializable
data class WeatherData(
    @DrawableRes val icon: Int,
    @StringRes val status: Int,
    val temp: Int,
    val maxTemp: Int,
    val minTemp: Int,
    val day: String,
    val hour: String,
)