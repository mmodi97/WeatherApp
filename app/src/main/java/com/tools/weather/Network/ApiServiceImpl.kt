package com.tools.weather.Network

import com.tools.weather.Model.City
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(var apiService: ApiService){

    suspend fun getCityData(city: String,appId:String): City =
        apiService.getCityData(city,appId)
}