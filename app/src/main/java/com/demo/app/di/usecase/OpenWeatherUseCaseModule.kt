package com.demo.app.di.usecase

import com.demo.app.domain.core.usecase.OpenWeatherUseCase
import com.demo.app.domain.core.usecase.OpenWeatherUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class OpenWeatherUseCaseModule {
    @Binds
    abstract fun bindsOpenWeatherUseCase(openWeatherImpl: OpenWeatherUseCaseImpl): OpenWeatherUseCase
}
