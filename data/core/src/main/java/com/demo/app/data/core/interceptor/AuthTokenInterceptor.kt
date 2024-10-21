package com.demo.app.data.core.interceptor

import com.demo.app.data.core.PreferencesKey
import com.demo.app.domain.core.repository.AccessTokenRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthTokenInterceptor @Inject constructor(
    private val repository: AccessTokenRepository
): Interceptor {

    private var accessToken: String? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            repository.fetchAccessToken().collectLatest {
                accessToken = it.ifBlank { null }
            }
        }
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        return runBlocking {
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .apply {
                        accessToken?.let { token ->
                           addHeader(PreferencesKey.AUTH_HEADER_NAME, token)
                        }
                    }.build()
            )
        }
    }
}
