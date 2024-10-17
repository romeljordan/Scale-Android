package com.demoapp.feature.records

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.app.core.design.theme.AppColor
import com.demo.app.domain.core.model.WeatherLog
import com.demo.app.feature.core.state.FetchState

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
        containerColor = AppColor.primaryBlue
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {

        }
    }
}

@Preview
@Composable
private fun PreviewRecordsScreen() {
    RecordsScreen(
        weatherLogs = emptyList(),
        fetchState = FetchState.Idle
    )
}
