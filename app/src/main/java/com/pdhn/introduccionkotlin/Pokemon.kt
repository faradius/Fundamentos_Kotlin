package com.pdhn.introduccionkotlin

class Pokemon (private var name:String = "Pok",
               private var attackPower:Float = 30f,
               private var life:Float = 100f){

    fun Pokemon(n:String, aP:Float){
        this.name = n
        this.attackPower = aP
        this.life = 100f
    }

    fun getName():String{ return this.name }
    fun getAttackPower():Float{ return this.attackPower }
    fun getLife():Float{ return this.life }

    fun setLife(l: Float){ this.life = l}


}