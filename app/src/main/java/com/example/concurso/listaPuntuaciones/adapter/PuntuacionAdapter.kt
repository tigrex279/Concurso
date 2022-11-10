package com.example.concurso.listaPuntuaciones.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.concurso.Puntuacion
import com.example.concurso.R

class PuntuacionAdapter(
    val listaPuntuacion: List<Puntuacion>,
    private val onClickListener: (Puntuacion) -> Unit,
    private val onClickDelete: (Int) -> Unit
): RecyclerView.Adapter<PuntuacionViewHolder>() {

    //Definimos su metedo create estableciendo su layout
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PuntuacionViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PuntuacionViewHolder(layoutInflater.inflate(R.layout.item_list,parent, false))
    }
    //Renederizamos cada elemento de la lista
    override fun onBindViewHolder(holder: PuntuacionViewHolder, position: Int) {
        val item = listaPuntuacion[position]
        holder.render(item,onClickListener,onClickDelete)
    }
    //Obtenemos el tamaño de la lista
    override fun getItemCount(): Int {
        return listaPuntuacion.size

    }
}
