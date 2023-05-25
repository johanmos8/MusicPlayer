package com.example.musicchallenge.data.di

import com.example.movies.data.utils.StringUtils
import com.example.musicchallenge.data.api.repositories.SongRepositoryImpl
import com.example.musicchallenge.data.mappers.SongResponseListToSongsMapper
import com.example.musicchallenge.data.remotedatasource.MusicRemoteDataSource
import com.example.musicchallenge.data.remotedatasource.api.SongApiService
import com.example.musicchallenge.data.source.remote.MusicRemoteDataSourceImpl
import com.example.musicchallenge.domain.repositories.ISongRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
        return MoshiConverterFactory.create(
            Moshi.Builder()
                .add(KotlinJsonAdapterFactory())
                .build()
        )
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(
        baseUrl:String,
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

    @Singleton
    @Provides
    fun provideMusicRemoteDataSource(
        songApiService: SongApiService,
    ): MusicRemoteDataSource {
        return MusicRemoteDataSourceImpl(
            songApiService
        )
    }
}


@Module
@InstallIn(SingletonComponent::class)
object ISongRepositoryModule {

    @Singleton
    @Provides
    fun provideISongRepository(
        musicRemoteDataSource: MusicRemoteDataSource,
        songResponseListToSongMapper: SongResponseListToSongsMapper

    ): ISongRepository {
        return SongRepositoryImpl(
            musicRemoteDataSource,
            songResponseListToSongMapper

        )
    }
}

