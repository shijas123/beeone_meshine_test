package com.shijas.beeonetest.di

import com.shijas.beeonetest.BuildConfig
import com.shijas.beeonetest.album.data.repository.AlbumRepositoryImpl
import com.shijas.beeonetest.album.data.source.AlbumService
import com.shijas.beeonetest.album.domain.repository.AlbumRepository
import com.shijas.beeonetest.home.data.repository.HomeRepositoryImpl
import com.shijas.beeonetest.home.data.source.HomeService
import com.shijas.beeonetest.home.domain.repository.HomeRepository
import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpLoginInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(httpLoggingInterceptor)
        okHttpClient.connectTimeout(30, TimeUnit.SECONDS)
        okHttpClient.readTimeout(30, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(30, TimeUnit.SECONDS)
        return okHttpClient.build()
    }

    @Provides

    @Singleton
    fun provideRetrofit(okhttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okhttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHomeRepository(homeService: HomeService) : HomeRepository{
        return HomeRepositoryImpl(homeService)
    }

    @Provides
    @Singleton
    fun provideAlbumRepository(albumService: AlbumService) : AlbumRepository{
        return AlbumRepositoryImpl(albumService)
    }

}