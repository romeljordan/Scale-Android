package com.demo.app.feature.login

import com.demo.app.domain.core.model.Session
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {

    private val userId = 1
    private val sessionKey = 1234
    private val username = "username"
    private val password = "password"
    private val accessToken = "access_token"

    private val mockAuthUseCase =
        mock<AuthUseCase> {
            onBlocking { session(userId) }.thenReturn(
                Result.success(
                    Session(
                        userId,
                        sessionKey,
                        accessToken
                    )
                )
            )

            onBlocking { login(username, password) }.thenReturn(
                Result.success(
                    Session(
                        userId,
                        sessionKey,
                        accessToken
                    )
                )
            )

            onBlocking { signUp(username, password) }.thenReturn(
                Result.success(true)
            )

            onBlocking { fetchCurrentSessionKey() }.thenReturn(
                Result.success(sessionKey.toString())
            )
        }

    private val loginViewModel = LoginViewModel(
        useCase = mockAuthUseCase
    )

    @Test
    fun user_has_an_existing_valid_session_key() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.requestState.collect()
                loginViewModel.fetchState.collect()
            }

            loginViewModel.fetchCurrentSession()

            assertEquals(FetchState.Idle, loginViewModel.fetchState.value)
            assertEquals(RequestState.Done(LoginRequestAction.LoginRequest), loginViewModel.requestState.value)
        }

    @Test
    fun check_if_log_in_input_is_initial_screen() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.screenType.collect()
            }

            assertEquals(ScreenType.Login, loginViewModel.screenType.value)
        }

    @Test
    fun switch_screen_to_sign_up_inputs() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.screenType.collect()
            }

            loginViewModel.switchScreenType()

            assertEquals(ScreenType.SignUp, loginViewModel.screenType.value)
        }

    @Test
    fun login_with_existing_valid_credentials() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.requestState.collect()
            }

            assertEquals(RequestState.Idle, loginViewModel.requestState.value)

            loginViewModel.login(username, password)

            assertEquals(RequestState.Done(LoginRequestAction.LoginRequest), loginViewModel.fetchState.value)
        }

    @Test
    fun sign_up_with_valid_credentials() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.requestState.collect()
            }

            assertEquals(RequestState.Idle, loginViewModel.requestState.value)

            loginViewModel.signUp(username, password)

            assertEquals(RequestState.Done(LoginRequestAction.SignUp(username)), loginViewModel.fetchState.value)
        }
}
