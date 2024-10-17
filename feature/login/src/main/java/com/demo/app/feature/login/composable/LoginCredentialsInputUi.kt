package com.demo.app.feature.login.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.demo.app.core.design.R
import com.demo.app.core.design.theme.AppColor
import com.demo.app.core.design.theme.appTypography

@Composable
internal fun LoginCredentialsInputUi(
    initialUserName: String = "",
    onShowSignUpInput: () -> Unit,
    onLogin: (username: String, password: String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(initialUserName) {
        username = initialUserName
    }

    Column(
        modifier = Modifier.fillMaxWidth(0.85f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            currentValue = username,
            placeholderText = "Username",
            leadingIcon = R.drawable.ic_person_24,
            onValueChange = { username = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputTextField(
            currentValue = password,
            placeholderText = "Password",
            isPassword = true,
            leadingIcon = R.drawable.ic_lock_24,
            onValueChange = { password = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?",
                style = appTypography.labelMedium.copy(
                    color = Color.White
                )
            )
            TextButton(
                onClick = { onShowSignUpInput.invoke() },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Sign up.",
                    style = appTypography.labelMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        TextButton(
            modifier = Modifier
                .fillMaxWidth()
                .background(AppColor.blue),
            onClick = {
                onLogin.invoke(username, password)
            }
        ) {
            Text(
                text = "LOG IN",
                style = appTypography.labelMedium.copy(
                    color = Color.White
                )
            )
        }
    }
}
