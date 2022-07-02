package com.pdhn.introduccionkotlin

enum class Dias(val laboral:Boolean, val jornada:Int) {
    LUNES(true,8),
    MARTES(true,8),
    MIERCOLES(true,5),
    JUEVES(true,4),
    VIERNES(true,8),
    SABADO(false,0),
    DOMINGO(false,0);

    fun saludo():String{
        when(this){
            LUNES -> return "Empezando con alegria!!"
            MARTES -> return "Ya queda menos!!"
            MIERCOLES -> return "Sabias que los miercoles son mas productivos"
            JUEVES -> return "Esta noche es juernes!!"
            VIERNES -> return "Hoy es viernes y el cuerpo lo sabe"
            else -> return "a quemar el findeseo!"
        }
    }
}