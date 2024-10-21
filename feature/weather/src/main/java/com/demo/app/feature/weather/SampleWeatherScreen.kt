package com.demo.app.feature.weather

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.demo.app.core.design.R
import com.demo.app.core.design.theme.appTypography
import com.demo.app.feature.core.util.TIME_12HR_FORMAT
import com.demo.app.feature.core.util.formatDate

@Composable
fun SampleWeatherScreen() {
    Scaffold { innerPaddingValues ->
        Column(
            modifier = Modifier
                .padding(innerPaddingValues)
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Today, Oct 20",
                    style = appTypography.labelMedium.copy(
                        fontWeight = FontWeight.Medium
                    )
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "City, CO",
                    style = appTypography.titleMedium
                )

                Text(
                    text = "Sunny",
                    style = appTypography.titleSmall.copy(
                        fontSize = 20.sp
                    )
                )

                SubcomposeAsyncImage(
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .aspectRatio(1f),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://openweathermap.org/img/wn@4x.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
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
                        text = "31.1",
                        style = appTypography.titleLarge.copy(
                            color =  Color.Black,
                            fontSize = 55.sp
                        )
                    )

                    Text(
                        modifier = Modifier.padding(top = 8.dp),
                        text = "°C",
                        style = appTypography.titleSmall.copy(
                            color =  Color.Black
                        )
                    )
                }

                Text(
                    text = "moderate rain",
                    style = appTypography.labelMedium
                )
            }

            Column(
                modifier = Modifier
                    .padding(bottom = 24.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.35f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(20.dp)
            ) {
                TimeRow(
                    icon = R.drawable.ic_sunny_filled_24,
                    title = "Sunrise",
                    dateMillis = System.currentTimeMillis()
                )
                TimeRow(
                    icon = R.drawable.ic_sunny_filled_24,
                    title = "Sunset",
                    dateMillis = System.currentTimeMillis()
                )
            }
        }
    }
}

@Composable
private fun TimeRow(
    @DrawableRes icon: Int,
    title: String,
    dateMillis: Long
) {
    Row {
        Icon(
            painter = painterResource(icon),
            contentDescription = null
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = title,
            style = appTypography.labelLarge
        )

        Spacer(Modifier.width(24.dp))

        Text(
            text = dateMillis.formatDate(TIME_12HR_FORMAT),
            style = appTypography.labelLarge
        )
    }
}

@Preview
@Composable
fun PreviewSomething() {
    SampleWeatherScreen()
}
