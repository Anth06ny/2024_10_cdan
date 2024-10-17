package com.example.a2024_10_cdan.viewmodel

import androidx.lifecycle.ViewModel
import com.example.a2024_10_cdan.model.PictureBean
import com.example.a2024_10_cdan.model.WeatherAPI
import com.example.a2024_10_cdan.model.WeatherBean

fun main() {
    val viewModel = MainViewModel()
    viewModel.loadWeathers("Nice")
    //attente
    //Affichage de la liste, qui doit être remplie
    println(viewModel.dataList)
}

class MainViewModel : ViewModel() {

    var dataList: List<PictureBean> = ArrayList()

    fun loadWeathers(cityName: String) {

        val listWeather: List<WeatherBean> = WeatherAPI.loadWeathers(cityName)

        dataList = listWeather.map {
            PictureBean(
                it.id,
                it.weather.getOrNull(0)?.icon ?: "https://openweathermap.org/img/wn/02d@4x.png",
                it.name,
                """
            Il fait ${it.main.temp}° à ${it.name} (id=${it.id}) avec un vent de ${it.wind.speed} m/s
            -Description : ${it.weather.getOrNull(0)?.description ?: "-"}
            -Icône : ${it.weather.getOrNull(0)?.description ?: "-"}
                """.trimIndent()
            )
        }

    }


}