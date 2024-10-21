package com.demo.app.data.core.di

import com.demo.app.data.core.repository.AccessTokenRepositoryImpl
import com.demo.app.domain.core.repository.AccessTokenRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AccessTokenRepositoryModule {
    @Binds
    abstract fun bindsAccessTokenRepository(accessTokenRepositoryImpl: AccessTokenRepositoryImpl): AccessTokenRepository
}
