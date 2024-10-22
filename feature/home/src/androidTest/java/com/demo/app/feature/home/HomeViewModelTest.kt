package com.demo.app.feature.home

import android.location.Location
import com.demo.app.domain.core.model.CurrentWeather
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.domain.core.usecase.OpenWeatherUseCase
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.TaskCompletionSource
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val currentWeather = CurrentWeather(
        city = "city",
        country = "country",
        temp = 20.0,
        type = "type",
        typeDescription = "desc",
        icon = "",
        sunset = 0L,
        sunrise = 0L,
        dateMillis = 0L
    )

    private val currentLocationLatitude = 14.599512
    private val currentLocationLongitude = 120.984222

    private val mockWeatherUseCase =
        mock<OpenWeatherUseCase> {
            onBlocking {
                fetchOpenWeather(currentLocationLatitude, currentLocationLongitude)
            }.thenReturn(
                Result.success(currentWeather)
            )
        }

    private val mockAuthUseCase =
        mock<AuthUseCase> {
            onBlocking { log(1, "jsonString") }.thenReturn(
                Result.success(true)
            )
        }

    private val mockFusedLocationProviderClient = mock<FusedLocationProviderClient> {
        onBlocking { lastLocation }.doReturn(
            TaskCompletionSource<Location>().apply {
                setResult(
                    Location("provider").apply {
                        latitude = currentLocationLatitude
                        longitude = currentLocationLongitude
                    }
                )
            }.task
        )
    }

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        homeViewModel = HomeViewModel(
            weatherUseCase = mockWeatherUseCase,
            authUseCase = mockAuthUseCase,
            fusedLocationProviderClient = mockFusedLocationProviderClient
        )
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun fetch_current_weather_with_valid_result() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                homeViewModel.fetchState.collect()
                homeViewModel.requestState.collect()
            }

            homeViewModel.fetchLocationAndLogWeather()

            assertEquals(FetchState.Idle, homeViewModel.fetchState.value)
            assertEquals(RequestState.Idle, homeViewModel.requestState.value)
        }
}
