package com.demo.app.feature.weather

import android.location.Location
import com.demo.app.domain.core.model.CurrentWeather
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.domain.core.usecase.OpenWeatherUseCase
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.tasks.TaskCompletionSource
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private val testUserId = 1
    private val currentLocationLatitude = 1.0
    private val currentLocationLongitude = 1.0
    private val currentWeather = CurrentWeather(
        city = "city",
        country = "country",
        temp = 20.0,
        type = "type",
        typeDescription = "desc",
        icon = "icon",
        sunset = 0L,
        sunrise = 0L,
        dateMillis = 0L
    )

    private val mockAuthUseCase =
        mock<AuthUseCase> {
            onBlocking { logout(testUserId) }.doReturn(
                Result.success(true)
            )
        }

    private val mockWeatherUseCase =
        mock<OpenWeatherUseCase> {
            onBlocking {
                fetchOpenWeather(currentLocationLatitude, currentLocationLongitude)
            }.thenReturn(
                Result.success(currentWeather)
            )
        }

    private val fusedLocationProviderClient = mock<FusedLocationProviderClient> {
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

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())

        viewModel = WeatherViewModel(
            authUseCase = mockAuthUseCase,
            useCase = mockWeatherUseCase,
            fusedLocationProviderClient = fusedLocationProviderClient
        )
    }

    @After
    fun teardown() {
        Dispatchers.resetMain()
    }

    @Test
    fun initial_current_weather_is_empty() =
        runTest {
            assertEquals(FetchState.Idle, viewModel.fetchState.value)
            assertEquals(null, viewModel.currentWeather.value)
        }

    @Test
    fun fetch_current_weather_successfully() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher((testScheduler))) {
                viewModel.fetchState.collect()
                viewModel.currentWeather.collect()
            }

            viewModel.fetchCurrentWeather(currentLocationLatitude, currentLocationLongitude)

            advanceUntilIdle()

            assertEquals(FetchState.Idle, viewModel.fetchState.value)
            assertNotNull(viewModel.currentWeather.value)
            assertEquals(currentWeather.city, viewModel.currentWeather.value?.city)
            assertEquals(currentWeather.country, viewModel.currentWeather.value?.country)
        }

    @Test
    fun user_logs_out_successfully() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher((testScheduler))) {
                viewModel.requestState.collect()
            }

            viewModel.logOut()

            assertEquals(
                RequestState.Done(WeatherRequestAction.LogOut),
                viewModel.requestState.value
            )
        }
}
