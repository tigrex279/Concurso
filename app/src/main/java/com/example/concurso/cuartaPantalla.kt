package com.example.concurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast

class cuartaPantalla : AppCompatActivity() {
    var opciones: Spinner? = null
    private var opcion: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cuarta_pantalla)

        opciones = findViewById(R.id.spinner)
        var aim = arrayOf("Punteria", "Buena", "Normal", "Mala")
        var adaptador: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, aim)
        opciones?.adapter = adaptador
    }

    fun resultado(view: View){

        opcion = opciones?.getSelectedItem().toString()
        if(opcion == "Punteria"){
            Toast.makeText(this, "No has seleccionado ninguna opción", Toast.LENGTH_SHORT).show()
        }
        else{
            val intent = Intent(this, resultado::class.java).apply {
                val nombre = intent.getStringExtra("usuario")
                val check = intent.getStringExtra("check")
                val radio = intent.getStringExtra("radio")

                putExtra("usuario", nombre)
                putExtra("check", check)
                putExtra("radio", radio)
                putExtra("spinner", opcion)
            }
            startActivity(intent)
        }
    }
}