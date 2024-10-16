package com.demo.app.di.usecase

import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.domain.core.usecase.AuthUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthUseCaseModule {
    @Binds
    abstract fun bindsAuthUseCase(authUseCaseImpl: AuthUseCaseImpl): AuthUseCase
}
