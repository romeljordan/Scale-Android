package com.demoapp.feature.records

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.demo.app.core.design.theme.AppColor
import com.demo.app.core.design.theme.appTypography
import com.demo.app.domain.core.model.WeatherLog
import com.demo.app.feature.core.state.FetchState
import com.demoapp.feature.records.composable.WeatherLogRowUi

@Composable
internal fun RecordsRoute(
    viewModel: RecordsViewModel = hiltViewModel()
) {
    val fetchState by viewModel.fetchState.collectAsState()
    val weatherLogs by viewModel.logs.collectAsState()

   RecordsScreen(
       weatherLogs = weatherLogs,
       fetchState = fetchState
   )
}

@Composable
private fun RecordsScreen(
    weatherLogs: List<WeatherLog>, // TODO change to immutable
    fetchState: FetchState
) {
    Scaffold(
        containerColor = AppColor.primaryBlue,
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = "Weather logs",
                    style = appTypography.titleMedium.copy(
                        color = Color.White
                    )
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            items(
                items = weatherLogs
            ) { item ->
                WeatherLogRowUi(weatherLog = item)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewRecordsScreen() {
    RecordsScreen(
        weatherLogs = listOf(
            WeatherLog(
                city = "Hello",
                country = "sda",
                temp = 30.1,
                type = "Rain",
                icon = "10n",
                dateMillis = System.currentTimeMillis()
            )
        ),
        fetchState = FetchState.Idle
    )
}
