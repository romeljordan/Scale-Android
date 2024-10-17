package com.demo.app.feature.login

import com.demo.app.domain.core.model.Session
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.feature.core.state.RequestState
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.kotlin.mock

class LoginViewModelTest {

    private val userId = 1
    private val sessionKey = 1234
    private val username = "username"
    private val password = "password"

    private val mockAuthUseCase =
        mock<AuthUseCase> {
            onBlocking { session(userId) }.thenReturn(
                Result.success(
                    Session(
                        userId,
                        sessionKey
                    )
                )
            )

            onBlocking { login(username, password) }.thenReturn(
                Result.success(
                    Session(
                        userId,
                        sessionKey
                    )
                )
            )

            onBlocking { signUp(username, password) }.thenReturn(
                Result.success(true)
            )
        }

    private val loginViewModel = LoginViewModel(
        useCase = mockAuthUseCase
    )


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetch_user_has_stored_session_key() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.fetchCurrentSession()
            }

            assertEquals(RequestState.Done(LoginRequestAction.LoginRequest), loginViewModel.fetchState)
        }

    @Test
    fun screen_switch_to_sign_up_inputs() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.switchScreenType()
            }

            assertEquals(ScreenType.SignUp, loginViewModel.screenType.value)
        }

    @Test
    fun login_with_existing_credentials() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.login(username, password)
            }

            assertEquals(RequestState.Done(LoginRequestAction.LoginRequest), loginViewModel.fetchState)
        }

    @Test
    fun sign_up_with_valid_credentials() =
        runTest {
            backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
                loginViewModel.signUp(username, password)
            }

            assertEquals(RequestState.Done(LoginRequestAction.SignUp(username)), loginViewModel.fetchState)
        }
}