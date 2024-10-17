package com.demoapp.feature.records.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
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
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = AppColor.blue,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = weatherLog.temp.toString(),
                    style = appTypography.titleLarge.copy(
                        color =  Color.White,
                        fontSize = 40.sp
                    )
                )

                Text(
                    text = "°C",
                    style = appTypography.titleSmall.copy(
                        color =  Color.White
                    )
                )
            }

            VerticalDivider(
                modifier = Modifier
                    .width(1.dp)
                    .height(35.dp),
                thickness = 1.dp,
                color = Color.White
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SubcomposeAsyncImage(
                    modifier = Modifier.size(50.dp),
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://openweathermap.org/img/wn/${weatherLog.icon}@4x.png")
                        .crossfade(true)
                        .build(),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    loading = {
                        CircularProgressIndicator()
                    }
                )

                Text(
                    text = weatherLog.type,
                    style = appTypography.labelSmall.copy(
                        color = Color.White
                    )
                )
            }
        }

        Text(
            text = "Fetched at " + weatherLog.dateMillis.formatDate(FULL_DATE_TIME_12HR_FORMAT),
            style = appTypography.labelSmall.copy(
                color = Color.White,
                fontStyle = FontStyle.Italic
            ),
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.Start)
        )
    }
}
