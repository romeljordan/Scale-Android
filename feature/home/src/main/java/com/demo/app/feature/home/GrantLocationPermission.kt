package com.demo.app.feature.home

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import com.demo.app.core.design.theme.appTypography

@Composable
internal fun GrantLocationPermission() {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .clickable {
                (context as? Activity)?.let { activity ->
                    val intent = Intent(
                        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                        Uri.fromParts("package", activity.packageName, null)
                    )
                    activity.startActivity(intent)
                }
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "You need to allow the 'Location' permission in App Settings to use this service\nTap to go to settings",
            style = appTypography.bodySmall.copy(
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
    }
}
