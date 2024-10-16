package com.demo.app.feature.login

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.demo.app.core.design.R
import com.demo.app.core.design.theme.AppColor
import com.demo.app.core.design.theme.appTypography
import com.demo.app.feature.login.composable.LoginCredentialsInputUi
import com.demo.app.feature.login.composable.SignUpCredentialsInputUi

@Composable
fun LoginScreen() {
    // TODO: update data
    var hasAnAccount by remember { mutableStateOf(true) }

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

            // TODO: fix start and exit animation (slide in and out)
            AnimatedVisibility(
                visible = hasAnAccount,
            ) {
                LoginCredentialsInputUi(
                    onShowSignUpInput = { hasAnAccount = false },
                    onLogin = { _, _ ->
                        // TODO: add viewmodel
                    }
                )
            }

            AnimatedVisibility(
                visible = !hasAnAccount
            ) {
                SignUpCredentialsInputUi(
                    onShowLoginInput = { hasAnAccount = true },
                    onSignUp = { _, _ ->
                        // TODO: add viewmodel
                    }
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    LoginScreen()
}
