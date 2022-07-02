package com.pdhn.introduccionkotlin

data class Star(var name:String = "", val radius:Float = 0f, var galaxy:String = ""){
    var alive = true

    //fun die(){ alive = false}
}
