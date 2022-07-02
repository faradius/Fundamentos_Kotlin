package com.pdhn.introduccionkotlin

open class Person(var name: String = "Anonimo", var passport: String? = null, var height: Float = 1.6f ) {
    var alive: Boolean = true

    fun die(){
        alive = false
    }

    fun Person(){
        name="Juan"
        passport ="V62648DF"
    }

    /*
    fun checkPolice(fn:(Float)->Boolean):Boolean{
        return fn(height)
    }

     */
}

class Athlete(name: String, passport: String?, var sport: String): Person(name, passport){

}