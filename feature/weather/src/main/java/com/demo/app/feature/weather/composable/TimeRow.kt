package com.demo.app.feature.weather.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.demo.app.core.design.theme.appTypography
import com.demo.app.feature.core.util.TIME_12HR_FORMAT
import com.demo.app.feature.core.util.formatDate

@Composable
internal fun TimeRow(
    @DrawableRes icon: Int,
    title: String,
    dateMillis: Long,
    textColor: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(0.65f),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null
            )
            Text(
                text = title,
                style = appTypography.labelLarge.copy(
                    color = textColor
                )
            )
        }

        Text(
            text = dateMillis.formatDate(TIME_12HR_FORMAT),
            style = appTypography.labelLarge.copy(
                color = textColor
            )
        )
    }
}
