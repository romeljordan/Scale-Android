package com.demo.app.feature.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.demo.app.core.design.theme.appTypography
import com.demo.app.core.design.R
import com.demo.app.core.design.theme.AppColor
import com.demo.app.feature.weather.composable.TimeInfoBoxUi
import com.demo.app.feature.weather.composable.WeatherPreviewUi

@Composable
internal fun WeatherScreen() {
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

            WeatherPreviewUi()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                TimeInfoBoxUi(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_sunny_filled_24,
                    title = "Sunrise",
                    dateMillis = 0L
                )

                TimeInfoBoxUi(
                    modifier = Modifier.weight(1f),
                    icon = R.drawable.ic_sunny_outline_24,
                    title = "Sunset",
                    dateMillis = 0L
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewWeatherScreen() {
    WeatherScreen()
}
