package com.test.a3.di

import android.app.Application
import android.content.Context
import com.test.a3.data.BetsRepositoryImpl
import com.test.a3.data.network.NetworkApi
import com.test.a3.data.network.NetworkFactory
import com.test.a3.data.network.SharedPreferencesRepositoryImpl
import com.test.a3.data.network.SplashApi
import com.test.a3.domain.BetInfoRepository
import com.test.a3.domain.SharedPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {

    @Binds
    @Singleton
    fun bindBetRepository(impl: BetsRepositoryImpl) :BetInfoRepository

    @Binds
    @Singleton
    fun bindSharedPreferencesRepository(impl: SharedPreferencesRepositoryImpl): SharedPreferencesRepository


    @Binds
    fun bindContext(application: Application): Context

    companion object {
        @Provides
        @Singleton
        fun provideNetworkApi(): NetworkApi = NetworkFactory.apiService
        @Provides
        @Singleton
        fun provideSplashApi(): SplashApi = NetworkFactory.splashService
    }
}
