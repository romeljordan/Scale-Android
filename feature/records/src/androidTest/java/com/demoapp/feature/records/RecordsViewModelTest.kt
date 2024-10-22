package com.demoapp.feature.records

import com.demo.app.domain.core.model.WeatherLog
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.feature.core.state.FetchState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class RecordsViewModelTest {

    private val testUserId = 1
    private val mockUseCase =
        mock<AuthUseCase> {
            onBlocking { logs(testUserId) }.doReturn(
                Result.success(
                    listOf(
                        WeatherLog(
                            city = "city",
                            country = "country",
                            temp = 30.0,
                            type = "type",
                            icon = "icon",
                            dateMillis = 0L
                        )
                    )
                )
            )
        }

    private val viewModel = RecordsViewModel(useCase = mockUseCase)

    @Test
    fun initial_weather_logs_list_size_is_empty() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher((testScheduler))) {
                viewModel.logs.collect()
                viewModel.fetchState.collect()
            }

            assertEquals(FetchState.Idle, viewModel.fetchState.value)
            assertEquals(0, viewModel.logs.value.size)
        }

    @Test
    fun fetch_weather_logs_successfully() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher((testScheduler))) {
                viewModel.logs.collect()
                viewModel.fetchState.collect()
            }

            viewModel.fetchWeatherLogs()

            advanceUntilIdle()

            assertEquals(FetchState.Idle, viewModel.fetchState.value)
            assertEquals(1, viewModel.logs.value.size)
            assertEquals("city", viewModel.logs.value.first().city)
            assertEquals("country", viewModel.logs.value.first().country)
        }
}
