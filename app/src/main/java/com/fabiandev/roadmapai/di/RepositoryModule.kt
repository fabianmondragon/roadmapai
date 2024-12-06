package com.fabiandev.roadmapai.di

import com.fabiandev.roadmapai.login.data.FirebaseAuthenticationRepositoryImpl
import com.fabiandev.roadmapai.login.domain.AuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)

abstract class RepositoryModule {
    @Binds
    abstract fun bindAuthenticationRepository (
        impl: FirebaseAuthenticationRepositoryImpl
    ): AuthenticationRepository
}