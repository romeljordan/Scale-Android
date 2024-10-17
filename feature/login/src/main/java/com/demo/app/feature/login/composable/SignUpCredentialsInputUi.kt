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
internal fun SignUpCredentialsInputUi(
    onShowLoginInput: () -> Unit,
    onSignUp: (username: String, password: String) -> Unit
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    /*var retypePassword by remember { mutableStateOf("") }*/

    Column(
        modifier = Modifier.fillMaxWidth(0.85f),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        InputTextField(
            currentValue = username,
            placeholderText = "Create Username",
            leadingIcon = R.drawable.ic_person_24,
            onValueChange = { username = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        InputTextField(
            currentValue = password,
            placeholderText = "Create Password",
            isPassword = true,
            leadingIcon = R.drawable.ic_lock_24,
            onValueChange = { password = it }
        )

        Spacer(modifier = Modifier.height(24.dp))

        /*InputTextField(
            currentValue = retypePassword,
            placeholderText = "Re-type Password",
            isPassword = true,
            leadingIcon = R.drawable.ic_lock_24,
            onValueChange = { retypePassword = it }
        )

        Spacer(modifier = Modifier.height(24.dp))*/

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Already have an account?",
                style = appTypography.labelMedium.copy(
                    color = Color.White
                )
            )
            TextButton(
                onClick = { onShowLoginInput.invoke() },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text(
                    text = "Log in.",
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
                /*if (password == retypePassword) {
                    onSignUp.invoke(username, password)
                }*/
                onSignUp.invoke(username, password)
            }
        ) {
            Text(
                text = "SIGN UP",
                style = appTypography.labelMedium.copy(
                    color = Color.White
                )
            )
        }
    }
}
