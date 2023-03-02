package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorCultivo
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.io.ByteArrayOutputStream
import java.io.IOException

class DialogoActCultivo : DialogFragment() {lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnActualizar: Button
    lateinit var fabAtras: ImageButton
    lateinit var campoName: EditText
    lateinit var campoCant: EditText

    lateinit var imgCultivo: ImageView
    lateinit var btnCamaraCultivo: Button

    lateinit var path: String
    private val COD_SELECCIONA = 10
    private val COD_FOTO = 20

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            interfaceComunicaFragments = actividad as IComunicaFragments
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_dialogo_act_cultivo, container, false)
        btnActualizar = vista.findViewById(R.id.idBtnActualizar)
        campoName = vista.findViewById(R.id.campoNombre)
        campoCant = vista.findViewById(R.id.campoCantidad)
        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        imgCultivo = vista.findViewById(R.id.imgCultivo)
        btnCamaraCultivo = vista.findViewById(R.id.btnCamaraCultivo)

        campoName.setText(nombre)
        campoCant.setText("" + cantidad)
        imgCultivo.setImageBitmap(bitmap)

        eventosMenu()

        return vista
    }

    private fun eventosMenu(){
        fabAtras.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()

            }

        })
        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                actualizarCultivo()
            }

        })
        btnCamaraCultivo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                mostrarDialogOpciones()
            }

        })
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                var imageBitmap = intent?.extras?.get("data") as Bitmap
                var ancho: Float = (600).toFloat()
                var alto: Float = (800).toFloat()
                bitmap = redimensionarImagen(imageBitmap, ancho, alto)
                imgCultivo.setImageBitmap(bitmap)
            }

        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            COD_SELECCIONA -> {
                var ancho: Float = (600).toFloat()
                var alto: Float = (800).toFloat()
                var miPath: Uri? = null

                if(data == null){
                    Toast.makeText(actividad, "¡No has seleccionado una imagen.! ", Toast.LENGTH_SHORT).show()
                }else{
                    miPath = data!!.data
                    imgCultivo.setImageURI(miPath)
                    try {
                        bitmap =
                            MediaStore.Images.Media.getBitmap(requireContext().contentResolver, miPath)
                        bitmap = redimensionarImagen(bitmap, ancho, alto)
                        imgCultivo.setImageBitmap(bitmap)

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

            }
            COD_FOTO -> {
                MediaScannerConnection.scanFile(
                    context, arrayOf<String>(path), null
                ) { path, uri -> Log.i("Path", "" + path) }
                bitmap = BitmapFactory.decodeFile(path)
            }
        }

    }

    private fun redimensionarImagen(bitmap: Bitmap, anchoNuevo: Float, altoNuevo: Float): Bitmap {
        var ancho = bitmap.width
        var alto = bitmap.height
        if (ancho > anchoNuevo || alto > altoNuevo) {
            var escalaAncho = anchoNuevo / ancho
            var escalaAlto = altoNuevo / alto

            var matrix: Matrix = Matrix()
            matrix.postScale(escalaAncho, escalaAlto)
            return Bitmap.createBitmap(bitmap, 0, 0, ancho, alto, matrix, false)
        } else {
            return bitmap
        }
    }

    private fun mostrarDialogOpciones() {
        val opciones = arrayOf<CharSequence>("Tomar Foto", "Elegir de Galeria", "Cancelar")
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Elige una Opción")
        builder.setItems(opciones, DialogInterface.OnClickListener { dialogInterface, i ->
            if (opciones[i] == "Tomar Foto") {
                startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { })
            } else {
                if (opciones[i] == "Elegir de Galeria") {
                    val intent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    intent.type = "image/"
                    startActivityForResult(
                        Intent.createChooser(intent, "Seleccione"),
                        COD_SELECCIONA
                    )
                } else {
                    dialogInterface.dismiss()
                }
            }
        })
        builder.show()
    }

    private fun actualizarCultivo() {
        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase


        if ((campoName.text.toString() != null && !campoName.text.toString().trim()
                .equals("")) and (campoCant.text.toString() != null && !campoCant.text.toString()
                .trim().equals(""))
        ) {

            val values = ContentValues()
            var baos: ByteArrayOutputStream = ByteArrayOutputStream(20480)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            var blob = baos.toByteArray()
            //Esto podria modificarlo
            //values.put(Utilidades.CAMPO_ID_PERSONA,id.text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_NOMBRE_CULTIVO, campoName.text.toString())
            values.put(Utilidades.CAMPO_CANT_PLANTAS, campoCant.text.toString())
            values.put(Utilidades.CAMPO_FOTO_CULTIVO, blob)

            val idResultante: Number = db.update(
                Utilidades.TABLA_CULTIVO,
                values,
                Utilidades.CAMPO_ID_CULTIVO + "=" + identificacion,
                null
            )

            if (idResultante != -1) {
                /*Utilidades.listaCultivos!!.removeAt(identificacion)
            notifyDataSetChanged()
            //Es para remover con un efecto bonito pero no funcionó del todo bien, elimanaba unos y a veces los confundia en la base de datos*/
                println("El usuario se Actualizó Exitosamente")
                Toast.makeText(
                    context,
                    "¡El usuario se Actualizó Exitosamente!",
                    Toast.LENGTH_SHORT
                ).show()
                //Utilidades.consultarlistaCultivos(MainActivity())
                DialogoGesCultivo.llenarAdaptadorCultivos()

                dismiss()


            } else {
                Toast.makeText(
                    context,
                    "EL usuario no se pudo Actualizar, intente nuevamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            if (campoName.text.toString().isEmpty()) {
                campoName.setError("Este campo no puede quedar vacio")
            } else if (campoCant.text.toString().isEmpty()) {
                campoCant.setError("Este campo no puede quedar vacio")
            }
            Toast.makeText(
                context,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_LONG
            ).show()
        }
        db.close()
    }

    companion object {
        var identificacion: Int = 0
        var nombre: String = ""
        var cantidad: Int = 0
        lateinit var bitmap: Bitmap
    }
}
