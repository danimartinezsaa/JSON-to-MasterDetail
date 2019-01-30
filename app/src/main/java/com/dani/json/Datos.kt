package com.dani.json

data class Datos(val titulo : String, val descripcion : String){
    // personalizamos to String
    override fun toString(): String {
        return "El título es: $titulo"
    }

}