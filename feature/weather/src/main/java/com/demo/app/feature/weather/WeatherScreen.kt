package com.demo.app.feature.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.demo.app.core.design.theme.appTypography
import com.demo.app.core.design.R
import com.demo.app.core.design.composable.LoadingAnimUi
import com.demo.app.core.design.theme.AppColor
import com.demo.app.domain.core.model.CurrentWeather
import com.demo.app.feature.core.util.OnNavResult
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestState
import com.demo.app.feature.core.util.DATE_FORMAT
import com.demo.app.feature.core.util.formatDate
import com.demo.app.feature.weather.composable.TimeRow
import com.demo.app.feature.weather.navigation.WeatherNavResult
import com.demo.app.feature.weather.navigation.WeatherScreenAction

@Composable
fun WeatherRoute(
    viewModel: WeatherViewModel = hiltViewModel(),
    onNavResult: OnNavResult<WeatherNavResult>
) {
    val currentWeather by viewModel.currentWeather.collectAsState()
    val lastFetchedDate by viewModel.fetchedDate.collectAsState()
    val fetchState by viewModel.fetchState.collectAsState()
    val requestState by viewModel.requestState.collectAsState()

    LaunchedEffect(requestState) {
        when (val state = requestState) {
            is RequestState.Done -> {
                if (state.action == WeatherRequestAction.LogOut) {
                    onNavResult.invoke(WeatherNavResult.OnLogOut)
                }
            }
            else -> { /* no-op */ }
        }
    }

    currentWeather?.let {
        WeatherScreen(
            currentWeather = it,
            lastFetchedDate = lastFetchedDate,
            fetchState = fetchState,
            requestState = requestState,
            onScreenAction = { action ->
                when(action) {
                    WeatherScreenAction.OnRefresh -> viewModel.requestLocation()
                    WeatherScreenAction.OnLogOut -> viewModel.logOut()
                }
            }
        )
    } ?: Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    listOf(AppColor.primaryBlue, Color.White)
                )
            )
    ) {
        LoadingAnimUi()
    }
}

@Composable
private fun WeatherScreen(
    currentWeather: CurrentWeather,
    lastFetchedDate: Long,
    fetchState: FetchState,
    requestState: RequestState,
    onScreenAction: (action: WeatherScreenAction) -> Unit
) {
    val nightMode by remember { mutableStateOf(currentWeather.icon.contains("n")) }
    val (bgColor, textColor) = colorMode(nightMode)

    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
            ) {
                TextButton(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .align(Alignment.CenterEnd),
                    onClick = { }
                ) {
                    Text(
                        text = "Log out",
                        style = appTypography.labelSmall.copy(
                            color = textColor
                        )
                    )
                }
            }
        }
    ) { innerPaddingValues ->
        Column(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        listOf(AppColor.primaryBlue, bgColor)
                    )
                )
                .padding(innerPaddingValues)
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Today, " + lastFetchedDate.formatDate(DATE_FORMAT),
                    style = appTypography.labelMedium.copy(
                        fontWeight = FontWeight.Medium,
                        color = textColor
                    ),
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                IconButton(
                    modifier = Modifier.size(20.dp),
                    onClick = {
                        onScreenAction.invoke(
                            WeatherScreenAction.OnRefresh
                        )
                    }
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_refresh_24),
                        contentDescription = null,
                        tint = textColor
                    )
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "${currentWeather.city}, ${currentWeather.country}",
                    style = appTypography.titleMedium,
                    color = textColor
                )

                Text(
                    text = currentWeather.type,
                    style = appTypography.titleSmall.copy(
                        fontSize = 20.sp,
                        color = textColor
                    )
                )

                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .aspectRatio(1f),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://openweathermap.org/img/wn/${currentWeather.icon}@4x.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    loading = {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .fillMaxSize(0.5f)
                                    .align(Alignment.Center)
                            )
                        }
                    }
                )

                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = currentWeather.temp.toString(),
                        style = appTypography.titleLarge.copy(
                            color =  textColor,
                            fontSize = 55.sp
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "°C",
                        style = appTypography.titleSmall.copy(
                            color =  textColor
                        )
                    )
                }

                Text(
                    text = currentWeather.typeDescription,
                    style = appTypography.labelMedium,
                    color = textColor
                )
            }

            val infoBgColor = if (nightMode) Color.DarkGray else Color(0xFFF6F6F6)
            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .background(
                        color = infoBgColor.copy(alpha = 0.65f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                TimeRow(
                    icon = R.drawable.ic_sunrise,
                    title = "Sunrise",
                    dateMillis = currentWeather.sunrise * 1000,
                    textColor = textColor
                )
                TimeRow(
                    icon = R.drawable.ic_sunset,
                    title = "Sunset",
                    dateMillis = currentWeather.sunset * 1000,
                    textColor = textColor
                )
            }
        }

        if (requestState == RequestState.Loading || fetchState == FetchState.Loading) {
            LoadingAnimUi()
        }
    }
}

@Composable
private fun colorMode(isNight: Boolean) =
    if (isNight) Pair(Color.Black, Color.White) else Pair(Color.White, Color.Black)

@Preview
@Composable
private fun PreviewWeatherScreen() {
    WeatherScreen(
        requestState = RequestState.Idle,
        lastFetchedDate = System.currentTimeMillis(),
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
