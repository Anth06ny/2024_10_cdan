package com.example.a2024_10_cdan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
    while (viewModel.runInProgress) {
        Thread.sleep(500)
    }

    //Affichage de la liste ou du message d'erreur
    println("List : ${viewModel.dataList}")
    println("ErrorMessage : ${viewModel.errorMessage}")

    viewModel.loadWeathers("Paris")
    while (viewModel.runInProgress) {
        Thread.sleep(500)
    }

    //Affichage de la liste ou du message d'erreur
    println("List : ${viewModel.dataList}")
    println("ErrorMessage : ${viewModel.errorMessage}")
}

const val LONG_TEXT = """Le Lorem Ipsum est simplement du faux texte employé dans la composition
    et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard
    de l'imprimerie depuis les années 1500"""


open class MainViewModel : ViewModel() {

    var dataList by mutableStateOf<List<PictureBean>>(emptyList())
    var runInProgress by mutableStateOf(false)
    var errorMessage by mutableStateOf("")


//    init {//Création d'un jeu de donnée au démarrage
//        println("MainViewModel.init")
//        loadFakeData()
//    }

    fun loadFakeData(){
        dataList = listOf(PictureBean(1, "https://picsum.photos/200", "ABCD", LONG_TEXT),
            PictureBean(2, "https://picsum.photos/201", "BCDE", LONG_TEXT),
            PictureBean(3, "https://picsum.photos/202", "CDEF", LONG_TEXT),
            PictureBean(4, "https://picsum.photos/203", "EFGH", LONG_TEXT)
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }

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