package com.demo.app.feature.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.app.core.design.theme.appTypography
import com.demo.app.core.design.R
import com.demo.app.core.design.composable.LoadingAnimUi
import com.demo.app.core.design.theme.AppColor
import com.demo.app.domain.core.model.CurrentWeather
import com.demo.app.feature.core.util.OnNavResult
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestState
import com.demo.app.feature.weather.composable.TimeInfoBoxUi
import com.demo.app.feature.weather.composable.WeatherPreviewUi
import com.demo.app.feature.weather.navigation.WeatherNavResult
import com.demo.app.feature.weather.navigation.WeatherScreenAction

@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel = hiltViewModel(),
    onNavResult: OnNavResult<WeatherNavResult>
) {
    val currentWeather by viewModel.currentWeather.collectAsState()
    val fetchState by viewModel.fetchState.collectAsState()
    val requestState by viewModel.requestState.collectAsState()

    currentWeather?.let {
        WeatherScreen(
            currentWeather = it,
            fetchState = fetchState,
            requestState = requestState,
            onScreenAction = { action ->

            }
        )
    } ?: Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColor.primaryBlue)
    ) {
        LoadingAnimUi()
    }
}

@Composable
private fun WeatherScreen(
    currentWeather: CurrentWeather,
    fetchState: FetchState,
    requestState: RequestState,
    onScreenAction: (action: WeatherScreenAction) -> Unit
) {
    Scaffold(
        containerColor = AppColor.primaryBlue
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Hello, username!",
                style = appTypography.titleLarge.copy(
                    color = Color.White
                )
            )

            WeatherPreviewUi(
                city = currentWeather.city,
                country = currentWeather.country,
                temperature = currentWeather.temp,
                description = currentWeather.type,
                dateMillis = currentWeather.dateMillis,
                icon = currentWeather.icon
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TimeInfoBoxUi(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_sunny_filled_24,
                    title = "Sunrise",
                    dateMillis = currentWeather.sunrise
                )

                TimeInfoBoxUi(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_sunny_outline_24,
                    title = "Sunset",
                    dateMillis = currentWeather.sunset
                )
            }
        }

        if (requestState == RequestState.Loading || fetchState == FetchState.Loading) {
            LoadingAnimUi()
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherScreen() {
    WeatherScreen(
        requestState = RequestState.Idle,
        fetchState = FetchState.Idle,
        currentWeather = CurrentWeather(
            city = "Antipolo City",
            country = "Philippines",
            temp = 29.8,
            type = "Sunny",
            typeDescription = "Cloudy and sunny",
            sunset = 0L,
            sunrise = 0L,
            icon = "",
            dateMillis = System.currentTimeMillis()
        ),
        onScreenAction = { }
    )
}
