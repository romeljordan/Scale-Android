package com.demo.app.feature.weather.composable

import androidx.annotation.DrawableRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.demo.app.core.design.theme.appTypography
import com.demo.app.feature.core.util.TIME_12HR_FORMAT
import com.demo.app.feature.core.util.formatDate

@Composable
internal fun TimeInfoBoxUi(
    @DrawableRes icon: Int,
    title: String,
    dateMillis: Long,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .border(
                width = 1.dp,
                color = Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            painter = painterResource(icon),
            contentDescription = null
        )

        Text(
            text = dateMillis.formatDate(TIME_12HR_FORMAT),
            style = appTypography.titleMedium.copy(
                color = Color.White,
                fontWeight = FontWeight.Medium
            ),
            maxLines = 1,
            overflow = TextOverflow.Clip
        )

        Text(
            text = title,
            style = appTypography.labelMedium.copy(
                color = Color.White
            )
        )
    }
}
