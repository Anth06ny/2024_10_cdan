package com.example.a2024_10_cdan.exo

import com.example.a2024_10_cdan.model.UserBean

fun main() {
    //exo1()
    exo3()
}

data class PersonBean(var name:String, var note:Int)

fun exo3(){
    val list = arrayListOf(PersonBean ("toto", 16),
        PersonBean ("Tata", 20),
        PersonBean ("Toto", 8),
        PersonBean ("Charles", 14))

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >=10 }.joinToString("\n") { "-${it.name} : ${it.note}"})

    val isToto : (PersonBean)-> Boolean = { it.name.equals("toto", true) }

    println("\n\nAfficher combien il y a de Toto dans la classe ?")
    list.count (isToto)

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    list.count { isToto(it) && it.note >= 10 }

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val average = list.map { it.note }.average()
    list.count { isToto(it) && it.note >= average }

    println("\n\nAfficher la list triée par nom sans doublon")
    list.distinctBy { it.name }.sortedBy { it.name }

    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    list.filter{it.note < 10}.forEach { it.note++ }

    println("\n\nAjouter un point à tous les Toto")
    list.filter(isToto).forEach { it.note++ }

    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il peut y en avoir plusieurs)")
    val minNote = list.minOf { it.note }
    list.removeIf { it.note == minNote }

    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
    list.filter{it.note >= 10}.sortedBy { it.name }.joinToString { it.name }

    //TODO Créer une variable isToto contenant une lambda qui teste si un PersonBean s'appelle Toto

    println("\n\nDupliquer la liste ainsi que tous les utilisateurs (nouvelle instance) qu'elle contient")
    val newList = list.map { it.copy() }

    println("\n\nAfficher par notes croissantes les élèves ayant eu cette note comme sur l'exemple")
}

fun exo1(){
    //Déclaration
    val lower : (String) -> Unit = { it:String-> println(it.lowercase())    }
    val lower2  = { it:String-> println(it.lowercase())    }
    val lower3 : (String) -> Unit = { it -> println(it.lowercase())    }
    val lower4 : (String) -> Unit = { println(it.lowercase())    }

    val hour :(Int)-> Int = {it/60}
    val max  = {a:Int, b : Int -> Math.max(a,b)}
    val reverse : (String)->String  = {it.reversed()}
    var minToMinHour  : ((Int?)->Pair<Int,Int>?)?  = {if(it != null) Pair(it/60, it%60) else null}
    minToMinHour = null

        //Appel
    lower("Coucou")
    val res = hour(126)
    println(res)
    println(max(5,6))
    println(reverse("toto"))
    println(minToMinHour?.invoke(126))
    println(minToMinHour?.invoke(null))


}

fun compareUsers(u1 : UserBean, u2  :UserBean, u3 : UserBean, comparator : (UserBean, UserBean)->UserBean) : UserBean {
   return comparator(comparator(u1,u2), u3)
}


fun exo2(){

    val u1 = UserBean("Bob", 19)
    val u2 = UserBean("Toto", 45)
    val u3 = UserBean("Charles", 26)


    val compareUsersByName :(UserBean, UserBean)->UserBean = { u1, u2 -> if( u1.name.lowercase() <= u2.name.lowercase()) u1 else u2 }
    val compareUsersByOld :(UserBean, UserBean)->UserBean = { u1, u2 -> if( u1.age >= u2.age) u1 else u2 }

    println(compareUsers(u1, u2, u3, compareUsersByName)) // UserBean(name=Bob old=19)
    println(compareUsers(u1, u2, u3, compareUsersByOld)) // UserBean(name=Toto old=45)

    //val near30Comparator :(UserBean, UserBean)->UserBean = { u1, u2 -> if( Math.abs(30-u1.age) < Math.abs(30-u2.age)) u1 else u2 }
    //val near30 = compareUsers(u1, u2, u3, {...})
    val near30 = compareUsers(u1, u2, u3) { a, b ->
        if( Math.abs(30-a.age) < Math.abs(30-b.age)) a else b
    }
    println(near30) //UserBean("Charles", 26)

}