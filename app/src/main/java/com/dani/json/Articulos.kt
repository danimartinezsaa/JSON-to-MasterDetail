package com.dani.json

import java.util.ArrayList

object Articulos{

    val lista: MutableList<Articulo> = ArrayList()

    init {

    }

    data class Articulo(val titulo : String, val descripcion : String){
        // personalizamos to String
        override fun toString(): String {
            return "El título es: $titulo"
        }
    }

}