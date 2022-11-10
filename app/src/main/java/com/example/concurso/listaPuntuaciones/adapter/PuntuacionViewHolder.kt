package com.example.concurso.listaPuntuaciones.adapter

import android.content.DialogInterface.OnClickListener
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.concurso.Puntuacion
import com.example.concurso.R
import com.squareup.picasso.Picasso
import java.io.File

class PuntuacionViewHolder(view: View) : RecyclerView.ViewHolder(view){

    val foto = view.findViewById<ImageView>(R.id.fotoPerfil)
    val texto = view.findViewById<TextView>(R.id.txtPuntuacion)
    val botonBorrar = view.findViewById<Button>(R.id.borrar)
    fun render(
        puntuacion: Puntuacion,
        onClickListener: (Puntuacion) -> Unit,
        onClickDelete: (Int) -> Unit
    ){
        itemView.setOnClickListener { onClickListener(puntuacion) }
        botonBorrar.setOnClickListener { onClickDelete(adapterPosition) }

        texto.text = puntuacion.nombre
        val imageFile = File("/storage/emulated/0/Android/data/com.example.Prueba3/files/Pictures/${puntuacion.Foto}")
        if (imageFile.exists()){

            val uriImagen = Uri.fromFile(imageFile)
            Picasso.get().load(uriImagen).fit().centerCrop().into(foto)
        }
    }
}