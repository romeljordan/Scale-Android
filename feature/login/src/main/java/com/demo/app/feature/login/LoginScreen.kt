package com.demo.app.feature.login

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.demo.app.core.design.R
import com.demo.app.core.design.composable.LoadingAnimUi
import com.demo.app.core.design.theme.AppColor
import com.demo.app.core.design.theme.appTypography
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestState
import com.demo.app.feature.core.util.OnNavResult
import com.demo.app.feature.login.composable.LoginCredentialsInputUi
import com.demo.app.feature.login.composable.SignUpCredentialsInputUi
import com.demo.app.feature.login.navigation.LoginNavResult
import com.demo.app.feature.login.navigation.LoginScreenAction

@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavResult: OnNavResult<LoginNavResult>
) {
    val screenType by viewModel.screenType.collectAsState()
    val requestState by viewModel.requestState.collectAsState()
    val fetchState by viewModel.fetchState.collectAsState()

    LaunchedEffect(requestState) {
        when (val state = requestState) {
            is RequestState.Done -> {
                if (state.action is LoginRequestAction.LoginRequest) {
                    onNavResult.invoke(LoginNavResult.MoveToHomeScreen)
                    viewModel.updateRequestState(RequestState.Idle)
                }
            }
            else -> { /* no-op*/ }
        }
    }

    LoginScreen(
        screenType = screenType,
        requestState = requestState,
        fetchState = fetchState
    ) { action ->
        when (action) {
            LoginScreenAction.OnSwitchScreen -> {
                viewModel.switchScreenType()
            }

            is LoginScreenAction.OnLoginRequest -> {
                viewModel.login(action.username, action.password)
            }

            is LoginScreenAction.OnSignUpRequest -> {
                viewModel.signUp(action.username, action.password)
            }
        }
    }
}

@Composable
private fun LoginScreen(
    screenType: ScreenType,
    requestState: RequestState,
    fetchState: FetchState,
    onScreenAction: (action: LoginScreenAction) -> Unit
) {
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        containerColor = AppColor.primaryBlue,
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { innerPadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(modifier = Modifier.fillMaxHeight(0.1f))
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

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.animation.AnimatedVisibility (
                    visible = screenType == ScreenType.Login,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    val initialUserName =
                        if (requestState is RequestState.Done && requestState.action is LoginRequestAction.SignUp) {
                            (requestState.action as LoginRequestAction.SignUp).username
                        } else ""
                    LoginCredentialsInputUi(
                        initialUserName = initialUserName,
                        onShowSignUpInput = { onScreenAction.invoke(LoginScreenAction.OnSwitchScreen) },
                        onLogin = { username, pw ->
                            onScreenAction.invoke(
                                LoginScreenAction.OnLoginRequest(username, pw)
                            )
                        }
                    )
                }

                androidx.compose.animation.AnimatedVisibility(
                    visible = screenType == ScreenType.SignUp,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    SignUpCredentialsInputUi(
                        onShowLoginInput = { onScreenAction.invoke(LoginScreenAction.OnSwitchScreen) },
                        onSignUp = { username, pw ->
                            onScreenAction.invoke(
                                LoginScreenAction.OnSignUpRequest(username, pw)
                            )
                        }
                    )
                }
            }
        }

        if (requestState == RequestState.Loading || fetchState == FetchState.Loading) {
            LoadingAnimUi()
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    LoginScreen(
        screenType = ScreenType.Login,
        onScreenAction = { },
        requestState = RequestState.Idle,
        fetchState = FetchState.Idle
    )
}
