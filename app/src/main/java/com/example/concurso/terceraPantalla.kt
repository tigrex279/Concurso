package com.example.concurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup

class terceraPantalla : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tercera_pantalla)
    }

    fun radioOpcion() : String {

        var palabra: String
        var radio = findViewById<RadioButton>(R.id.muyDificil)

        if (radio.isChecked) {
            palabra = "muy dificl"
            return palabra
        } else {
            radio = findViewById(R.id.dificl)
            if (radio.isChecked) {
                palabra = "dificil"
                return palabra
            } else {
                radio = findViewById(R.id.normal)
                if (radio.isChecked) {
                    palabra = "normal"
                    return palabra
                } else {
                    radio = findViewById(R.id.facil)
                    if (radio.isChecked) {
                        palabra = "facil"
                        return palabra
                    } else {
                        radio = findViewById(R.id.muyFacil)
                        if (radio.isChecked) {
                            palabra = "muy facil"
                            return palabra
                        }
                    }
                }
            }
        }
        return ""
    }
    fun ultimaPregunta(view: View){
        val grupo = findViewById<RadioGroup>(R.id.armas)
        var id: Int = grupo.checkedRadioButtonId
        if(id != -1){
            val intent = Intent(this, cuartaPantalla::class.java).apply {
                val nombre = intent.getStringExtra("usuario")
                val check = intent.getStringExtra("check")
                putExtra("usuario", nombre)
                putExtra("check", check)
                putExtra("radio", radioOpcion())
            }
            startActivity(intent)
        }
    }
}