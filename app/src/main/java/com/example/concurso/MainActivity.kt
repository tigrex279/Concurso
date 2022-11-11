package com.example.concurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.concurso.listaPuntuaciones.MostrarLista

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    
    fun nombreUsuario(view: View){
        val text = findViewById<EditText>(R.id.Nombre)
        val nombre = text.text.toString()
        if(nombre != ""){
            val intent = Intent(this, hacerFoto::class.java).apply {
                putExtra("usuario",nombre )
            }
            startActivity(intent)
        }
        else{
            Toast.makeText(this, "Introduzca un nombre de usuario por favor", Toast.LENGTH_SHORT).show()
        }
    }

    fun ListarUsuarios(view: View){
           val ListaPuntuaciones = Intent(this, ListaPuntuaciones::class.java)
            startActivity(ListaPuntuaciones)
        }
    }
