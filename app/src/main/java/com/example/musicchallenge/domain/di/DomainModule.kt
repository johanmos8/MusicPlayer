package com.example.musicchallenge.domain.di

import com.example.musicchallenge.domain.repositories.ISongRepository
import com.example.musicchallenge.domain.usesCases.MusicUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Provides
    @Singleton
    fun provideSongUseCase(songRepository: ISongRepository): MusicUseCase {
        return MusicUseCase(songRepository)
    }
}
