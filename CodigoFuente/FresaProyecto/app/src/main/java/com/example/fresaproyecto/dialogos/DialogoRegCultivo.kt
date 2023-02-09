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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.io.ByteArrayOutputStream
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegCultivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoRegCultivo : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnGuardar: Button
    lateinit var fabAtras: ImageButton
    lateinit var campoName: EditText
    lateinit var campoCant: EditText

    lateinit var imgCultivo: ImageView
    lateinit var btnCamaraCultivo: Button

    lateinit var bitmap: Bitmap
    lateinit var path: String
    private val COD_SELECCIONA = 10
    private val COD_FOTO = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            interfaceComunicaFragments = actividad as IComunicaFragments
        }
    }


    /**
     * Here we define the methods that we can fire off
     * in our parent Activity once something has changed
     * within the fragment.
     */

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_dialogo_reg_cultivo, container, false)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        campoName = vista.findViewById(R.id.campoNombre)
        campoCant = vista.findViewById(R.id.campoCantidad)
        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        imgCultivo = vista.findViewById(R.id.imgCultivo)
        btnCamaraCultivo = vista.findViewById(R.id.btnCamaraCultivo)
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
        btnGuardar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                registrarCultivo()
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

    private fun registrarCultivo(){
         if((campoName.text.toString()!=null && !campoName.text.toString().trim().equals("")) and (campoCant.text.toString()!=null && !campoCant.text.toString().trim().equals(""))){
             var registro= "Nombre: "+campoName.text.toString()+"\n"
             registro +="Cantidad: "+campoCant.text.toString()+"\n"
             print("Registrar:  "+registro)
             Toast.makeText(actividad, "REGISTRAR:\n"+registro, Toast.LENGTH_LONG).show()
             //La linea sigueinte deberia ir dentro de un IF que verifique si la consulta SQL es correcta
             //interfaceComunicaFragments.menuCultivo()
             //conexion con la base de datos
             val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null,1)
             val db: SQLiteDatabase = conexion.writableDatabase
             val values = ContentValues()

             //valores para agregar a la tabla de cultivos
             //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
             values.put(Utilidades.CAMPO_NOMBRE_CULTIVO, campoName.text.toString())
             values.put(Utilidades.CAMPO_CANT_PLANTAS, campoCant.text.toString())
             var baos: ByteArrayOutputStream = ByteArrayOutputStream(20480)
             bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
             var blob = baos.toByteArray()
             values.put(Utilidades.CAMPO_FOTO_CULTIVO, blob)
             val idResultante:Number = db.insert(Utilidades.TABLA_CULTIVO, Utilidades.CAMPO_ID_CULTIVO, values)

             if(idResultante != -1){
                 println("Registrar: " +registro)
                 Toast.makeText(actividad, "¡Registro Éxitoso! " +registro, Toast.LENGTH_SHORT).show()
                 DialogoGesCultivo.llenarAdaptadorCultivos()
             }else{
                 Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT).show()
             }
             db.close()

             dismiss()
         }else{
             if(campoName.text.toString().isEmpty()){
                 campoName.setError("Este campo no puede quedar vacio")
             }else if (campoCant.text.toString().isEmpty()){
                 campoCant.setError("Este campo no puede quedar vacio")
             }
             Toast.makeText(actividad, "Verifique que todos los campos esten registrados \n ", Toast.LENGTH_LONG).show()
         }
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegCultivoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoRegCultivo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}