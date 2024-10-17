package com.demo.app.scale.di.repository

import com.demo.app.data.core.repository.OpenWeatherRepositoryImpl
import com.demo.app.domain.core.repository.OpenWeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OpenWeatherRepositoryModule {
    @Binds
    abstract fun bindsOpenWeatherRepository(openWeatherRepositoryImpl: OpenWeatherRepositoryImpl): OpenWeatherRepository
}
