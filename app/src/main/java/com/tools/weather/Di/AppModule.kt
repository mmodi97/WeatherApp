package com.tools.weather.Di

import com.tools.weather.Network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesBaseUrl():String="https://api.openweathermap.org/data/2.5/";


    @Provides
    @Singleton
    fun providesRetrofitBuilder(baseUrl: String):Retrofit=Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService=
        retrofit.create(ApiService::class.java)



}