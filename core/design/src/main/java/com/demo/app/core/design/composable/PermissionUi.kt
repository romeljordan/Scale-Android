package com.demo.app.core.design.composable

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionUi(
    permission: String,
    onUserActionListener: (isGranted: Boolean) -> Unit
) {
    val permissionState = rememberPermissionState(permission)

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        onUserActionListener.invoke(isGranted)
    }

    LaunchedEffect(permissionState) {
        if (!permissionState.status.isGranted) {
            requestPermissionLauncher.launch(permission)
        }
    }
}
