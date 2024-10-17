package com.demo.app.feature.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.feature.core.state.RequestAction
import com.demo.app.feature.core.state.RequestState
import com.demo.app.feature.core.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ScreenType {
    Login, SignUp
}

sealed interface LoginRequestAction: RequestAction {
    data class SignUp(val username: String): LoginRequestAction
    data object LoginRequest: LoginRequestAction
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCase: AuthUseCase
): BaseViewModel() {

    private val _screenType = MutableStateFlow(ScreenType.Login)
    val screenType = _screenType.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1_000),
        initialValue = ScreenType.Login
    )

    init {
        // TODO: check if has existing session key
        // if yes, call session api
        // if success go to home screen

//        viewModelScope.launch {
//            useCase.session(9).onSuccess {
//                Log.i("checker", "logout success: $it")
//            }.onFailure {
//                Log.i("checker", "logout error ${it.message}")
//            }
//        }
    }

    fun signUp(
        username: String,
        password: String
    ) = viewModelScope.launch {
        updateRequestState(RequestState.Loading)
        useCase.signUp(username, password).onSuccess {
            switchScreenType()
            updateRequestState(RequestState.Done(LoginRequestAction.SignUp(username)))
        }.onFailure {
            updateRequestState(RequestState.Error(it.message))
        }
    }

    fun login(
        username: String,
        password: String
    ) = viewModelScope.launch {
        updateRequestState(RequestState.Loading)
        useCase.login(username, password).onSuccess {
            Log.i("checker", "user id: $it")
            updateRequestState(RequestState.Done(LoginRequestAction.LoginRequest))
        }.onFailure {
            updateRequestState(RequestState.Error(it.message))
        }
    }

    fun switchScreenType() {
        if (_screenType.value == ScreenType.Login) {
            _screenType.update { ScreenType.SignUp }
        } else {
            _screenType.update { ScreenType.Login }
        }
    }
}
