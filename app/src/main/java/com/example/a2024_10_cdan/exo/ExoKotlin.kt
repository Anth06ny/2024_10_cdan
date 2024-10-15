package com.example.a2024_10_cdan.exo




fun main() {
    var res = boulangerie(nbSand =  5)// boulangerie(0, 5, 0)
    println("Le résultat est $res")

    println(scanNumber("Donnez un nombre : "))
}



fun scanText(question:String): String {
    println(question)
    return readlnOrNull() ?: "-"
}

fun scanNumber(question:String) = scanText(question).toIntOrNull() ?: 0



fun boulangerie(nbCroissant:Int = 0, nbSand:Int = 0, nbBag : Int = 0)
    = nbCroissant * PRIX_CROISSANT + nbBag * PRIX_BAGUETTE + nbSand * PRIX_SANDWITCH


fun toto(){
    print("Entrez un texte : ")
    val a = readlnOrNull()
    println("Le texte : $a")
}

