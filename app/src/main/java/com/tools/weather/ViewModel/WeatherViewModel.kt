package com.tools.weather.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tools.weather.Model.City
import com.tools.weather.Repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository): ViewModel() {
    val getData:MutableLiveData<City> = MutableLiveData()

    @ExperimentalCoroutinesApi
    val searchcity=ConflatedBroadcastChannel<String>();

    @ExperimentalCoroutinesApi
    fun getSearchData(city:String){
        searchcity.offer(city);
    }

    @ExperimentalCoroutinesApi
    @FlowPreview
    fun getCityData()= viewModelScope.launch{

        searchcity.asFlow()
            .flatMapLatest {
                city-> repository.getCityData(city)
            }
            .catch{
                e->
                Log.d("","")
            }.collect {
                    city-> getData.value=city
            }
    }


}