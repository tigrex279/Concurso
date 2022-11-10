package com.example.concurso

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast

class segundaPantalla : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_segunda_pantalla)
    }

    fun checkboxElegida() : String {
        var palabra : String
        var check = findViewById<CheckBox>(R.id.energia)
        if(check.isChecked){
            palabra = "Tanque"
            return palabra
        }
        else{
            var check = findViewById<CheckBox>(R.id.dps)
            if(check.isChecked) {
                palabra = "DPS"
                return palabra
            }
            else {
                var check = findViewById<CheckBox>(R.id.healer)
                if(check.isChecked) {
                    palabra = "Healer"
                    return palabra
                }
            }
        }
        return ""
    }

    fun siguientePantalla(view: View) {
        var check1 = findViewById<CheckBox>(R.id.energia)
        var check2 = findViewById<CheckBox>(R.id.dps)
        var check3 = findViewById<CheckBox>(R.id.healer)

        if(check1.isChecked && check2.isChecked || check1.isChecked && check3.isChecked ||
            check2.isChecked && check3.isChecked) {
            Toast.makeText(this, "Elige solo una opcion, por favor", Toast.LENGTH_SHORT).show()
        }
        else if(!check1.isChecked && !check2.isChecked && !check3.isChecked){
            Toast.makeText(this, "Elige alguna opcion, por favor", Toast.LENGTH_SHORT).show()
        }
        else{
            val intent = Intent(this, terceraPantalla::class.java).apply {
                val nombre = intent.getStringExtra("usuario")
                putExtra("usuario", nombre)
                putExtra("check", checkboxElegida())
            }
            startActivity(intent)
        }
    }
}