package com.demo.app.core.design.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.demo.app.core.design.theme.AppColor

@Composable
fun LoadingAnimUi(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.45f))
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            ) { /* no-op */ },
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = AppColor.lightBlue,
            modifier = Modifier.size(30.dp)
        )
    }
}
