package com.demoapp.feature.records

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.app.core.design.R
import com.demo.app.core.design.composable.DropDownMenu
import com.demo.app.core.design.composable.LoadingAnimUi
import com.demo.app.core.design.composable.MenuItem
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
    var isSortedByNewest by remember { mutableStateOf(true) }
    var isMenuExpanded by remember { mutableStateOf(false) }
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        containerColor = AppColor.primaryBlue,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Weather logs",
                    style = appTypography.titleSmall.copy(
                        color = Color.White
                    )
                )

                if (weatherLogs.isNotEmpty()) {
                    Box {
                        Row(
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    RoundedCornerShape(6.dp)
                                )
                                .clickable {
                                    isMenuExpanded = !isMenuExpanded
                                }
                                .padding(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Sort by: ",
                                style = appTypography.labelSmall.copy(
                                    color = Color.Black,
                                    fontSize = 11.sp
                                )
                            )
                            Text(
                                text = if (isSortedByNewest) "Newest" else "First",
                                style = appTypography.labelSmall.copy(
                                    color = Color.Black,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 11.sp
                                )
                            )

                            Icon(
                                modifier = Modifier.size(18.dp),
                                painter = painterResource(R.drawable.ic_arrow_drop_down_24),
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }

                        if (isMenuExpanded) {
                            DropDownMenu(
                                modifier = Modifier.align(Alignment.BottomEnd),
                                options = listOf(MenuItem.SortByNewest, MenuItem.SortByFirst),
                                onDismiss = { isMenuExpanded = false },
                                onTapItem = { item ->
                                    isSortedByNewest = item == MenuItem.SortByNewest
                                    isMenuExpanded = false
                                }
                            )
                        }
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = if (isSortedByNewest) {
                    weatherLogs.sortedByDescending { it.dateMillis }
                } else {
                    weatherLogs.sortedBy { it.dateMillis }
                }
            ) { item ->
                WeatherLogRowUi(weatherLog = item)
            }
        }

        if (weatherLogs.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No fetched weather items yet.",
                    style = appTypography.labelSmall.copy(
                        color = Color.White
                    )
                )
            }
        }

        if (fetchState == FetchState.Loading) {
            LoadingAnimUi()
        }
    }
}

@Preview
@Composable
private fun PreviewRecordsScreen() {
    RecordsScreen(
        weatherLogs = listOf(
            WeatherLog(
                city = "City",
                country = "CO",
                temp = 30.1,
                type = "Rain",
                icon = "10d",
                dateMillis = System.currentTimeMillis()
            )
        ),
        fetchState = FetchState.Idle
    )
}
