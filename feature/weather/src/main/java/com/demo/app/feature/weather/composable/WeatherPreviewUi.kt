package com.demo.app.feature.weather.composable

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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.app.core.design.R
import com.demo.app.core.design.theme.AppColor
import com.demo.app.core.design.theme.appTypography

// TODO: update parameter default values
@Composable
internal fun WeatherPreviewUi(
    city: String = "City",
    country: String = "Country",
    temperature: Double = 30.0,
    description: String = "Sunny",
    millisDate: Long = 0L
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = AppColor.blue,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_location_pin_24),
                contentDescription = null
            )

            Text(
                text = "$city, $country",
                style = appTypography.titleMedium.copy(
                    color = Color.White,
                    fontSize = 20.sp
                )
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = temperature.toString(),
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
                Icon(
                    modifier = Modifier.size(50.dp),
                    painter = painterResource(R.drawable.ic_sunny_filled_24), // TODO: update icon
                    contentDescription = null,
                    tint = AppColor.orange
                )

                Text(
                    text = description,
                    style = appTypography.labelSmall.copy(
                        color = Color.White
                    )
                )
            }
        }

        Text(
            text = "Thu, Oct 17",
            style = appTypography.labelSmall.copy(
                color = Color.White
            ),
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.End)
        )
    }
}
