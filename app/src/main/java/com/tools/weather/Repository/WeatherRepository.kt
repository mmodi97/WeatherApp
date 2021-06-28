package com.tools.weather.Repository

import com.tools.weather.Model.City
import com.tools.weather.Network.ApiServiceImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val apiServiceImpl: ApiServiceImpl){

    suspend fun getCityData(city:String): Flow<City> = flow{
        val response=apiServiceImpl.getCityData(city,"1abf04795974e5f2c76c40f8d0fd91a1");
        emit(response);
    }.flowOn(Dispatchers.IO)
        .conflate()

}