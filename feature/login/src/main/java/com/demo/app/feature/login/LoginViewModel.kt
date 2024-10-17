package com.demo.app.feature.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.demo.app.data.core.Session
import com.demo.app.domain.core.model.WeatherLog
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestAction
import com.demo.app.feature.core.state.RequestState
import com.demo.app.feature.core.vm.BaseViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.json.JSONObject
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
        fetchCurrentSession()
    }

    private fun fetchCurrentSession() = viewModelScope.launch {
        updateFetchState(FetchState.Loading)
        useCase.fetchCurrentSessionKey().onSuccess {
            updateFetchState(FetchState.Idle)
            if (it.isBlank()) {
                updateFetchState(FetchState.Idle)
            } else {
                updateRequestState(RequestState.Loading)
                useCase.session(it.toInt()).onSuccess { session ->
                    Session.current = session
                    updateRequestState(RequestState.Done(LoginRequestAction.LoginRequest))
                }.onFailure {
                    updateRequestState(RequestState.Idle)
                }
            }
        }.onFailure {
            updateFetchState(FetchState.Error(it.message))
        }
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
            Session.current = it
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
