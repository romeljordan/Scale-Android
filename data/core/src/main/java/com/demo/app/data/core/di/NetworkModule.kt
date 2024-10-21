package com.demo.app.data.core.di

import com.demo.app.data.core.BuildConfig
import com.demo.app.data.core.interceptor.AuthTokenInterceptor
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Call.Factory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Qualifier
annotation class AuthRetrofit

@Qualifier
annotation class AuthOkHttpCallFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpCallFactory(): Factory =
        OkHttpClient.Builder()
            .build()

    @Provides
    @Singleton
    @AuthOkHttpCallFactory
    fun provideAuthOkHttpCallFactory(
        authTokenInterceptor: AuthTokenInterceptor
    ): Factory =
        OkHttpClient.Builder()
            .addInterceptor(authTokenInterceptor)
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpCallFactory: Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_WEATHER_BASE_URL)
            .callFactory(okHttpCallFactory)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

    @Provides
    @Singleton
    @AuthRetrofit
    fun providesAuthRetrofit(
        @AuthOkHttpCallFactory okHttpCallFactory: Factory
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_AUTH_BASE_URL)
            .callFactory(okHttpCallFactory)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }
}
