package com.example.a2024_10_cdan.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

//Utilisation
fun main() {


    val list = WeatherAPI.loadWeathers("Toulon")
    val list2 = WeatherAPI.loadWeathers("Toulon")
    val list3 = WeatherAPI.loadWeathers("Toulon")
    //println(list)

//    for(weather in list){
//        println("""
//            Il fait ${weather.main.temp}° à ${weather.name} (id=${weather.id}) avec un vent de ${weather.wind.speed} m/s
//            -Description : ${weather.weather.getOrNull(0)?.description ?: "-"}
//            -Icône : ${weather.weather.getOrNull(0)?.description ?: "-"}
//        """.trimIndent())
//    }


//    val users = WeatherAPI.loadRandomUsers()
//
//    for(user in users) {
//
//        println(
//            """
//        Il s'appelle ${user.name} pour le contacter :
//        Phone : ${user.coord?.phone ?: "-"}
//        Mail : ${user.coord?.mail ?: "-"}
//    """.trimIndent()
//        )
//    }
}

object WeatherAPI {

    val client = OkHttpClient()
    val gson = Gson()

    fun loadRandomUser(): UserBean {
        val json = sendGet("https://www.amonteiro.fr/api/randomuser")
        val user = gson.fromJson(json, UserBean::class.java)
        return user
    }

    fun loadRandomUsers(): List<UserBean> {
        val json = sendGet("https://www.amonteiro.fr/api/randomusers")
        val user = gson.fromJson(json, Array<UserBean>::class.java)
        return user.toList()
    }

    fun loadWeathers(cityName : String): List<WeatherBean> {

       val json =  sendGet("https://api.openweathermap.org/data/2.5/find?q=$cityName&cnt=5&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr")

        Thread.sleep(2000)

        val list =  gson.fromJson(json, WeatherAPIResult::class.java).list

        //traitement
        list.forEach {
            it.weather.forEach {
                it.icon = "https://openweathermap.org/img/wn/${it.icon}@4x.png"
            }
        }

        return list

    }

    //Méthode qui prend en entrée une url, execute la requête
    //Retourne le code HTML/JSON reçu
    fun sendGet(url: String): String {
        println("url : $url")
        //Création de la requête
        val request = Request.Builder().url(url).build()
        //Execution de la requête
        return client.newCall(request).execute().use {   //it:Response
            //use permet de fermer la réponse qu'il y ait ou non une exception
            //Analyse du code retour
            if (!it.isSuccessful) {
                throw Exception("Réponse du serveur incorrect :${it.code}\n${it.body.string()}")
            }
            //Résultat de la requête
            it.body.string()
        }
    }
}

/* -------------------------------- */
//API Meteo
/* -------------------------------- */
data class WeatherAPIResult(var list : List<WeatherBean> )
data class WeatherBean(var id : Int, var name:String, var main: TempBean, var wind :WindBean, var weather: List<DescriptionBean> )
data class TempBean(var temp : Double)
data class WindBean(var speed : Double)
data class DescriptionBean(var description : String, var icon:String)

/* -------------------------------- */
// USER
/* -------------------------------- */
data class UserBean(var name:String,var age : Int,var coord : CoordBean? = null)
data class CoordBean(var phone:String?,var mail : String?)
