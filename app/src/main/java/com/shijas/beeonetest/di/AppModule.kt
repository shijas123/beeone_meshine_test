package com.shijas.beeonetest.di

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.shijas.beeonetest.album.data.source.AlbumService
import com.shijas.beeonetest.home.data.source.HomeService
import com.shijas.beeonetest.settings.data.repository.SharedPreferenceRepositoryImpl
import com.shijas.beeonetest.settings.domain.SharedPreferenceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideShowPost(retrofit: Retrofit) : HomeService {
        return retrofit.create(HomeService::class.java)
    }

    @Provides
    @Singleton
    fun provideShowAlbum(retrofit: Retrofit) : AlbumService {
        return retrofit.create(AlbumService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(application)

    @Provides
    @Singleton
    fun provideSharedPreferenceRepository(sharedPreferences: SharedPreferences) : SharedPreferenceRepository{
        return SharedPreferenceRepositoryImpl(sharedPreferences)
    }

}