package com.example.concurso

import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider.getUriForFile
import com.squareup.picasso.Picasso
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVPrinter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Files.createDirectory

class hacerFoto : AppCompatActivity() {

    private var uri: Uri? = null
    lateinit var currentPhotoPath: String
    val storageDir =
        File("/storage/emulated/0/Android/data/com.example.concurso/files/Documents")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hacer_foto)

        findViewById<Button>(R.id.buttonHacerFoto).setOnClickListener{
            hacerFoto()
        }

    }

    fun hacerFoto() {

        val photoFile: File? = try {
            crearFicheroImagen()
        } catch (ex: IOException) {
            // Error occurred while creating the File
            ex.printStackTrace()
            null
        }
        if (photoFile != null) {
            uri = getUriForFile(
                this,
                "com.example.concurso.fileprovider",
                photoFile
            )

            obtenerCamara.launch(uri)
        }

    }
    private fun crearFicheroImagen(): File {
        val nombre_imagen = "Nombrefoto"

        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        return File.createTempFile(
            "FNL_${nombre_imagen}_",
            ".jpg",
            storageDir /* directory */
        ).apply {
            currentPhotoPath = absolutePath
        }
    }
    var obtenerCamara =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
            if (success) {
                Log.i(ContentValues.TAG, "Image Location :$uri")
                recargarImageView(uri)

                val usuario = intent.getStringExtra("usuario")
                val nombre_imagen = "Foto usuario"

                val ficheroCsv = File("${storageDir.path}/puntuaciones.csv")
                val ficheroExiste = ficheroCsv.exists()

                if (!ficheroExiste) {

                    var carpeta = ficheroCsv.parentFile

                    if(!carpeta.exists()){
                        createDirectory(carpeta.toPath())

                    }
                    ficheroCsv.createNewFile()
                }
                val fileWriter = FileWriter(ficheroCsv, true)
                val csvPrinter = CSVPrinter(fileWriter, CSVFormat.EXCEL)
                if (!ficheroExiste) {
                    csvPrinter.printRecord("Nombre", "Nombre_Fichero")
                }
                csvPrinter.printRecord(
                    usuario, nombre_imagen
                )
                csvPrinter.flush()
                csvPrinter.close()

            } else {
                Log.i(ContentValues.TAG, "Image not saved:$uri")
            }
        }
    private fun recargarImageView(uri: Uri?) {

        val archivo = uri!!.lastPathSegment
        val imgFile =
            File("/storage/emulated/0/Android/data/com.example.concurso/files/Pictures/$archivo")
        val imageView = findViewById<ImageView>(R.id.imageView)
        if (imgFile.exists()) {

            val uriImagen = Uri.fromFile(imgFile)


            Picasso.get().load(uriImagen)
                .fit().centerCrop().into(imageView)
        }
    }

    fun siguientePantalla(view: View){
        val intent = Intent(this, segundaPantalla::class.java).apply {
            val nombre = intent.getStringExtra("usuario")
            putExtra("usuario", nombre)
        }
        startActivity(intent)
    }
}