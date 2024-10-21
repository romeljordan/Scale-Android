package com.demoapp.feature.records.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.demo.app.core.design.theme.AppColor
import com.demo.app.core.design.theme.appTypography
import com.demo.app.domain.core.model.WeatherLog
import com.demo.app.feature.core.util.FULL_DATE_TIME_12HR_FORMAT
import com.demo.app.feature.core.util.formatDate

@Composable
fun WeatherLogRowUi(
    weatherLog: WeatherLog,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.clickable { /* no-op */ }
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = AppColor.lightBlue,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = weatherLog.temp.toString(),
                    style = appTypography.titleLarge.copy(
                        color =  Color.DarkGray,
                        fontSize = 50.sp
                    )
                )

                Text(
                    modifier = Modifier.padding(top = 8.dp),
                    text = "°C",
                    style = appTypography.titleSmall.copy(
                        color =  Color.DarkGray
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "${weatherLog.city}, ${weatherLog.country}",
                style = appTypography.bodyMedium.copy(
                    color = Color.DarkGray,
                    fontWeight = FontWeight.Medium
                )
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = weatherLog.dateMillis.formatDate(FULL_DATE_TIME_12HR_FORMAT),
                    style = appTypography.labelSmall.copy(
                        color = Color.DarkGray,
                        fontStyle = FontStyle.Italic
                    ),
                    modifier = Modifier.padding(end = 16.dp)
                )

                Text(
                    text = weatherLog.type,
                    style = appTypography.labelSmall.copy(
                        color = Color.DarkGray,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }

        SubcomposeAsyncImage(
            modifier = Modifier
                .height(125.dp)
                .aspectRatio(1f)
                .align(Alignment.TopEnd),
            model = ImageRequest.Builder(LocalContext.current)
                .data("https://openweathermap.org/img/wn/${weatherLog.icon}@4x.png")
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            loading = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.fillMaxSize(0.35f)
                    )
                }
            }
        )
    }
}

@Preview
@Composable
private fun PreviewWeatherLogRowUi() {
    WeatherLogRowUi(
        weatherLog = WeatherLog(
            city = "City",
            country = "CO",
            temp = 30.1,
            type = "Rain",
            icon = "10d",
            dateMillis = System.currentTimeMillis()
        )
    )
}
