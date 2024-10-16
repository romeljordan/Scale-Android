package com.demo.app.feature.login

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.app.core.design.R
import com.demo.app.core.design.theme.AppColor
import com.demo.app.core.design.theme.appTypography
import com.demo.app.feature.login.composable.InputTextField

@Composable
fun LoginScreen() {
    Scaffold(
        containerColor = AppColor.primaryBlue
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.45f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.icon),
                    contentDescription = null,
                    modifier = Modifier
                        .size(90.dp)
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.height(45.dp),
                    text = "SCALE",
                    style = appTypography.titleLarge.copy(
                        color = Color.White,
                        fontSize = 40.sp
                    )
                )

                Text(
                    text = "A Weather Update App",
                    style = appTypography.titleLarge.copy(
                        color = AppColor.lightBlue,
                        fontSize = 18.sp
                    )
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(0.85f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InputTextField(
                    currentValue = "",
                    placeholderText = "Username",
                    leadingIcon = R.drawable.ic_person_24,
                    onValueChange = {  }
                )

                Spacer(modifier = Modifier.height(8.dp))

                InputTextField(
                    currentValue = "",
                    placeholderText = "Password",
                    isPassword = true,
                    leadingIcon = R.drawable.ic_lock_24,
                    onValueChange = {  }
                )

                Spacer(modifier = Modifier.height(32.dp))

                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(AppColor.blue),
                    onClick = { }
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
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    LoginScreen()
}
