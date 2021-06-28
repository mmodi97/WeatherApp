package com.tools.weather.Network

import com.tools.weather.Model.City
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("weather/")
    suspend fun  getCityData(
        @Query("q") q:String,
        @Query("appid")appid:String

    ):City
}