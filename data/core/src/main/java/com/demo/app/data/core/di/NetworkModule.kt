package com.demo.app.data.core.di

import com.demo.app.data.core.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideOkHttpCallFactory(): Call.Factory =
        OkHttpClient.Builder()
            .build()

    @Provides
    @Singleton
    fun providesRetrofit(okHttpCallFactory: Call.Factory): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_WEATHER_BASE_URL)
            .callFactory(okHttpCallFactory)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
}
