package com.example.concurso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val nombre = intent.getStringExtra("usuario")
        val opcion1 = intent.getStringExtra("check")
        val opcion2 = intent.getStringExtra("radio")
        val opcion3 = intent.getStringExtra("spinner")

        if(opcion1 == "Tanque") {
            if (opcion2 == "muy dificl"){

            }
        }
        else if(opcion1 == "DPS"){

        }
        else{

        }

        var usuario = findViewById<TextView>(R.id.Nombre).apply {
            text = nombre
        }
        val respuesta1 = findViewById<TextView>(R.id.respuesta1).apply {
            text = opcion1
        }

        val respuesta2 = findViewById<TextView>(R.id.respuesta2).apply {
            text = opcion2
        }

        val respuesta3 = findViewById<TextView>(R.id.respuesta3).apply {
            text = opcion3
        }
    }
}