package com.example.musicchallenge.data.di

import com.example.movies.data.utils.StringUtils
import com.example.musicchallenge.data.remotedatasource.api.SongApiService
import com.example.musicchallenge.data.api.repositories.SongRepositoryImpl
import com.example.musicchallenge.data.mappers.SongReponseToSongMapper
import com.example.musicchallenge.data.mappers.SongResponseListToSongsMapper
import com.example.musicchallenge.domain.repositories.ISongRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = StringUtils.BASE_URL

    @Provides
    @Singleton
    fun provideMoshiConverterFactory(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        baseUrl: String,
        moshiConverterFactory: MoshiConverterFactory
    ): SongApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(moshiConverterFactory)
        .build()
        .create(SongApiService::class.java)


    @Provides
    @Singleton
    fun provideSongResponseListToSongsMapper(): SongResponseListToSongsMapper {
        return SongResponseListToSongsMapper()
    }
}


@Module
@InstallIn(SingletonComponent::class)
object ISongRepositoryModule {

    @Singleton
    @Provides
    fun provideISongRepository(
        songApiService: SongApiService,
        songResponseListToSongMapper: SongResponseListToSongsMapper

    ): ISongRepository {
        return SongRepositoryImpl(
            songApiService, songResponseListToSongMapper
        )
    }
}

