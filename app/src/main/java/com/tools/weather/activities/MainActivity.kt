package com.tools.weather.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tools.weather.R
import com.tools.weather.ViewModel.WeatherViewModel
import com.tools.weather.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var weatherViewModel:WeatherViewModel
    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
         weatherViewModel= ViewModelProvider(this).get(WeatherViewModel::class.java)
        val view =binding.root
        setContentView(view)
        init();
        weatherViewModel.getCityData()
        weatherViewModel.getData.observe(
            this, Observer {
                response->
                if(response.weather[0].description == "clear sky" || response.weather[0].description == "mist"){
                    Glide.with(this)
                        .load(R.drawable.clouds)
                        .into(binding.image)
                }else
                    if(response.weather[0].description == "haze" || response.weather[0].description == "overcast clouds" || response.weather[0].description == "fog" ){
                        Glide.with(this)
                            .load(R.drawable.haze)
                            .into(binding.image)
                    }else
                        if(response.weather[0].description == "rain"){
                            Glide.with(this)
                                .load(R.drawable.rain)
                                .into(binding.image)
                        }
                binding.description.text=response.weather[0].description
                binding.name.text=response.name
                binding.degree.text=response.wind.deg.toString()
                binding.speed.text=response.wind.speed.toString()
                binding.temp.text=response.main.temp.toString()
                binding.humidity.text=response.main.humidity.toString()
            }
        )
    }
    @ExperimentalCoroutinesApi
    private fun init(){
        binding.searchView.setOnQueryTextListener(
            object :SearchView.OnQueryTextListener,

                androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        weatherViewModel.getSearchData(it)
                    }
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                  return true
                }


            }
        )
    }
}