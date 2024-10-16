package com.demo.app.feature.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.demo.app.core.design.theme.appTypography

@Composable
internal fun WeatherScreen() {
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = "Hello, username!",
                style = appTypography.titleLarge.copy(
                    color = Color.White
                )
            )


        }
    }
}

@Preview
@Composable
private fun PreviewWeatherScreen() {
    WeatherScreen()
}
