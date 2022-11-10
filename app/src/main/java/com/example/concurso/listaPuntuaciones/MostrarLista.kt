package com.example.concurso.listaPuntuaciones

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.concurso.ProfileActivity
import com.example.concurso.Puntuacion
import com.example.concurso.R
import com.example.concurso.databinding.ActivityMostrarListaBinding
import com.example.concurso.listaPuntuaciones.adapter.PuntuacionAdapter


const val NOMBRE = "com.exmaple.concurso.MostrarLista.nombre"
const val RUTA_IAMGEN = "com.exmaple.concurso.MostrarLista.ruta_imagen"

class MostrarLista : AppCompatActivity() {

    private lateinit var binding: ActivityMostrarListaBinding
    private var listaPuntuaciones: MutableList<Puntuacion> =
        PuntuacionesProvider.obtenerTodasLasPuntuaciones()
    private lateinit var puntuacionAdapater: PuntuacionAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMostrarListaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
    }


    fun initRecyclerView() {
        //Declaramos el adapatador del recyclerview
        puntuacionAdapater = PuntuacionAdapter(
            listaPuntuacion = listaPuntuaciones,
            onClickListener = { puntuacion -> selecionarElemento(puntuacion) },
            onClickDelete = { posicion -> borrarElemento(posicion) })
        //Establecemos su layoutmanager y el adaptador
        binding.listaRycleView.layoutManager = LinearLayoutManager(this)
        binding.listaRycleView.adapter = puntuacionAdapater

    }

    //Funcion que permita borrar un elemento en funcion de su posicion
    fun borrarElemento(posicion: Int) {
        listaPuntuaciones.removeAt(posicion)
        puntuacionAdapater.notifyDataSetChanged()
    }

    //Funcion que se ejecuta cada vez que se toca en un elemento de la lista
    fun selecionarElemento(puntuacion: Puntuacion) {
        Toast.makeText(this, "El elemento seleccionado es ${puntuacion.nombre} ", Toast.LENGTH_LONG)
            .show()

        val intent = Intent(this, ProfileActivity::class.java).apply {
            putExtra(NOMBRE, puntuacion.nombre)
            putExtra(RUTA_IAMGEN, puntuacion.Foto)
        }
        startActivity(intent)

    }
}