package com.pdhn.introduccionkotlin

class SubClasses {
    private var name = "Padre"
    fun presentar():String {
        return "Hola soy la clase ${this.name}"
    }

    class Anidada{
        private val nameAnidada = "Anidada"
        fun presentar(){
            println("Hola!, soy llamado de la clase ${nameAnidada}")
        }
    }

    inner class Interna{
        private val nameInterna = "Interna"
        fun presentar(saludo:String): String{
            return "${saludo} de la clase ${this.nameInterna} y yo estoy siendo llamado de la clase ${name}"
        }
    }
}