package com.example.a2024_10_cdan.model

import java.util.Random


fun main() {
    //    var house = HouseBean("Rouge", 10, 20)
    //    house.area = 5
    //    house.print()

    //PrintRandomIntBean()

//    val t1 = ThermometerBean(min = -20, max = 50, value = 10000)
//    println("Température de ${t1.value}") // 0
//    t1.value = 10000
//    println("Température de ${t1.value}") //
//
//    val t2 = ThermometerBean.getCelsiusThermometer()

//    val randomName = RandomName()
//    //randomName.add("bobby")
//    repeat(30) {
//        println(randomName.nextDiff() + " ")
//    }

}



data class PictureBean(val id:Int, val url: String, val title: String, val longText: String)

class RandomName {
    private val list = arrayListOf("Toto", "Tata", "Titi")
    private var oldValue = ""

    fun next2() = Pair(nextDiff(), nextDiff())

    fun nextDiff2(): String {
        oldValue = list.filter { oldValue != it  }.random()
        return oldValue
    }

    fun nextDiff3() = list.filter { oldValue != it  }.random().also{ oldValue = it}

    fun nextDiff(): String {
        var newName = next()
        while(oldValue == newName) {
            newName = next()
        }
        oldValue = newName

        return newName
    }

    fun add(name:String?){
        if(name !in list && !name.isNullOrBlank()) {
            list.add(name)
        }
    }

    fun next() =  list.random()

    fun addAll(vararg  names : String){
        for(name in names) {
            add(name)
        }
    }
}

class ThermometerBean(var min: Int, var max: Int, value: Int) {
    var value: Int = value.coerceIn(min, max)
        set(newValue) {
            //field = if(newValue < min) min else if(newValue > max) max else newValue
            field = newValue.coerceIn(min, max)
        }

    companion object {
        fun getCelsiusThermometer() = ThermometerBean(-30, 50, 0)
        fun getFahrenheitThermometer() = ThermometerBean(20, 120, 32)
    }
}


class PrintRandomIntBean(var max: Int) {
    private val random: Random = Random()

    init {
        println("init")
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println("constructor")
        println(random.nextInt(max))
    }
}

class HouseBean(var color: String, length: Int, width: Int) {
    var area = length * width

    fun print() = println("La maison $color fait ${area}m²")
}

data class CarBean(var marque: String = "", var model: String? = null, var thermometer: ThermometerBean) {
    var color = ""
}

