package com.example.a2024_10_cdan.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a2024_10_cdan.model.PictureBean
import com.example.a2024_10_cdan.model.WeatherAPI
import com.example.a2024_10_cdan.model.WeatherBean
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

fun main() {
    val viewModel = MainViewModel()

    viewModel.loadWeathers("")
    while (viewModel.runInProgress) Thread.sleep(500)

    viewModel.loadWeathers("Paris")
    while (viewModel.runInProgress) Thread.sleep(500)


    //Affichage de la liste ou du message d'erreur
    println("List : ${viewModel.dataList}")
    println("ErrorMessage : ${viewModel.errorMessage}" )
}

class MainViewModel : ViewModel() {

    var dataList: List<PictureBean> = ArrayList()
    var runInProgress = false
    var errorMessage = ""

    fun loadWeathers(cityName: String) {

        runInProgress = true
        errorMessage = ""


        viewModelScope.launch(Dispatchers.Default) {

            try {

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
            catch (e: Exception) {
                e.printStackTrace()
                errorMessage = e.message ?: "Une erreur est survenue"
            }

            runInProgress = false
        }


    }


}