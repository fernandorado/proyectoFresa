package com.misRegistros.dialogos

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.FileProvider
import androidx.fragment.app.DialogFragment
import com.misRegistros.R
import com.misRegistros.clases.vo.CultivoVo
import com.misRegistros.controllers.CultivoRestController
import com.misRegistros.interfaces.IComunicaFragments
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class DialogoRegCultivo : DialogFragment() {

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    var cultivoController: CultivoRestController = CultivoRestController()
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

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.window!!.setGravity(Gravity.TOP)
        return dialog
    }

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
        vista = inflater.inflate(R.layout.fragment_dialogo_reg_cultivo, container, false)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        campoName = vista.findViewById(R.id.campoNombre)
        campoCant = vista.findViewById(R.id.campoCantidad)
        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        imgCultivo = vista.findViewById(R.id.imgCultivo)
        btnCamaraCultivo = vista.findViewById(R.id.btnCamaraCultivo)

        var miPath: Uri? = null
        val archivo = "android.resource://" + actividad.packageName + "/" + R.drawable.sin_foto
        miPath = Uri.parse(archivo)
        bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, miPath)
        imgCultivo.setImageBitmap(bitmap)
        eventosMenu()
        return vista
    }

    private fun eventosMenu() {
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

    @Throws(IOException::class)
    private fun createImageFile(): File? {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val imageFileName = "JPEG_" + timeStamp + "_"
        val storageDir: File? = requireContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image: File = File.createTempFile(
            imageFileName,  /* prefix */
            ".jpg",  /* suffix */
            storageDir /* directory */
        )
        path = image.getAbsolutePath()
        return image
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(requireActivity().getPackageManager())?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    val photoURI: Uri = FileProvider.getUriForFile(
                        actividad,
                        "com.misRegistros",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, COD_FOTO)
                }

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var ancho: Float = (800).toFloat()
        var alto: Float = (600).toFloat()
        var miPath: Uri? = null
        when (requestCode) {

            COD_SELECCIONA -> {

                if (data == null) {
                    Toast.makeText(
                        actividad,
                        "¡No has seleccionado una imagen!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    miPath = data!!.data
                    imgCultivo.setImageURI(miPath)
                    try {
                        bitmap =
                            MediaStore.Images.Media.getBitmap(
                                requireContext().contentResolver,
                                miPath
                            )
                        bitmap = redimensionarImagen(bitmap, ancho, alto)
                        imgCultivo.setImageBitmap(bitmap)

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

            }
            COD_FOTO -> {
                if (data == null) {
                    Toast.makeText(
                        actividad,
                        "¡No has tomado una foto!",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    bitmap = BitmapFactory.decodeFile(path)
                    bitmap = redimensionarImagen(bitmap, ancho, alto)
                    imgCultivo.setImageBitmap(bitmap)
                }
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
                dispatchTakePictureIntent()
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

    private fun registrarCultivo() {
        if ((campoName.text.toString().trim().equals("")) or (campoCant.text.isEmpty())
        ) {
            Toast.makeText(
                actividad,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_LONG
            ).show()
            if (campoName.text.toString().trim().equals("")) {
                campoName.setError("Este campo no puede quedar vacio")
            } else if (campoCant.text.isEmpty()) {
                campoCant.setError("Este campo no puede quedar vacio")
            }

        } else {
            var baos: ByteArrayOutputStream = ByteArrayOutputStream(20480)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            var blob = baos.toByteArray()
            var cultivo : CultivoVo
            cultivo = CultivoVo()
            cultivo.nombre = (campoName.text.toString().trim())
            cultivo.cantidad = (campoCant.text.toString().toInt())
            cultivo.imgCultivo = blob

            var cultivoActual : CultivoVo? = cultivoController.create(actividad as Context,cultivo)


            //valores para agregar a la tabla de cultivos


            if (cultivoActual != null) {
                Toast.makeText(actividad, "¡Registro Éxitoso! ", Toast.LENGTH_SHORT)
                    .show()
                DialogoGesCultivo.llenarAdaptadorCultivos()
                dismiss()
            } else {
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT)
                    .show()
            }


        }
    }
}