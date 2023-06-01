package com.example.musicchallenge.data.di

import android.content.Context
import com.example.movies.data.utils.StringUtils
import com.example.musicchallenge.BuildConfig
import com.example.musicchallenge.data.localdatasource.MusicLocalDataSource
import com.example.musicchallenge.data.localdatasource.MusicLocalDataSourceImpl
import com.example.musicchallenge.data.mappers.ChartResponseToChartMapper
import com.example.musicchallenge.data.mappers.FavoriteSongToSongMapper
import com.example.musicchallenge.data.mappers.GenreDTOListToGenreMapper
import com.example.musicchallenge.data.mappers.SongResponseListToSongsMapper
import com.example.musicchallenge.data.remotedatasource.MusicRemoteDataSource
import com.example.musicchallenge.data.remotedatasource.MusicRemoteDataSourceImpl
import com.example.musicchallenge.data.remotedatasource.api.SongApiService
import com.example.musicchallenge.data.repositories.SongRepositoryImpl
import com.example.musicchallenge.domain.repositories.ISongRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    @Singleton
    fun provideBaseUrl() = StringUtils.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        val requestInterceptor = Interceptor { chain ->
            val url = chain.request()
                .url
                .newBuilder()

                .build()

            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        OkHttpClient
            .Builder()
            .addInterceptor(requestInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

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
        baseUrl: String,
        moshiConverterFactory: MoshiConverterFactory,
        client: OkHttpClient
    ): SongApiService = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(moshiConverterFactory)
        .build()
        .create(SongApiService::class.java)


    @Provides
    @Singleton
    fun provideSongResponseListToSongsMapper(): SongResponseListToSongsMapper {
        return SongResponseListToSongsMapper()
    }

    @Provides
    @Singleton
    fun provideGenreDTOListToGenreMapper(): GenreDTOListToGenreMapper {
        return GenreDTOListToGenreMapper()
    }

    @Provides
    @Singleton
    fun provideChartResponseToChartMapper(): ChartResponseToChartMapper {
        return ChartResponseToChartMapper()
    }
    @Provides
    @Singleton
    fun provideFavoriteSongToSongMapper(): FavoriteSongToSongMapper {
        return FavoriteSongToSongMapper()
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

    @Singleton
    @Provides
    fun provideMusicLocalDataSource(
        @ApplicationContext context: Context
    ): MusicLocalDataSource {
        return MusicLocalDataSourceImpl(context)
    }

}


@Module
@InstallIn(SingletonComponent::class)
object ISongRepositoryModule {

    @Singleton
    @Provides
    fun provideISongRepository(

        musicRemoteDataSource: MusicRemoteDataSource,
        musicLocalDataSource: MusicLocalDataSource,
        songResponseListToSongMapper: SongResponseListToSongsMapper,
        genreDTOListToGenreMapper: GenreDTOListToGenreMapper,
        chartResponseToChartMapper: ChartResponseToChartMapper,
        favoriteSongToSongMapper: FavoriteSongToSongMapper
    ): ISongRepository {
        return SongRepositoryImpl(
            musicRemoteDataSource,
            musicLocalDataSource,
            songResponseListToSongMapper,
            genreDTOListToGenreMapper,
            chartResponseToChartMapper,
            favoriteSongToSongMapper
        )
    }


}