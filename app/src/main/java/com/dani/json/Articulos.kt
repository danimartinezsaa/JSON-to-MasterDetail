package com.dani.json

import android.util.Log
import org.jetbrains.anko.doAsync
import org.json.JSONArray
import java.lang.Exception
import java.net.URL
import java.util.ArrayList

object Articulos{

    val lista: MutableList<Articulo> = ArrayList()

    init {
        peticionwp()
    }

    fun peticionwp() {
        var respuesta = ""
        // lanza la corutina NO en el hilo principal
        doAsync {
            // peticion a wordpress
            respuesta = URL("http://18.191.161.6/wp5/?rest_route=/wp/v2/posts").readText()
            // Accedemos al hilo principal
            if (respuesta!=null) {
                try {
                    var jsonArray = JSONArray(respuesta)
                    for (jsonIndex in 0..(jsonArray.length() - 1)) {
                        var titulo = jsonArray.getJSONObject(jsonIndex).getJSONObject("title").getString("rendered")
                        Log.d("wordpress", titulo)
                        var descripcion = jsonArray.getJSONObject(jsonIndex).getJSONObject("content").getString("rendered")
                        Log.d("wordpress", descripcion)
                        var dato = Articulo(titulo, descripcion)
                        lista.add(jsonIndex, dato)
                    }
                }catch (e: Exception){
                    Log.d("error","Connection timeout")
                }
            }
        }
    }

    data class Articulo(val titulo : String, val descripcion : String){
        // personalizamos to String
        override fun toString(): String {
            return "El título es: $titulo"
        }
    }


}