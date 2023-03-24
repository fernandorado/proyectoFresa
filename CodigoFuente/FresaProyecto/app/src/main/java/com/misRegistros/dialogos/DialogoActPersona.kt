package com.misRegistros.dialogos

import android.app.Dialog
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.misRegistros.R
import com.misRegistros.clases.ConexionSQLiteHelper
import com.misRegistros.clases.Utilidades

class DialogoActPersona : DialogFragment() {
    lateinit var vista: View
    lateinit var campoId: EditText
    lateinit var campoNombre: EditText
    lateinit var btnCerrar: ImageButton
    lateinit var btnActualizar: Button

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.window!!.setGravity(Gravity.TOP)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_dialogo_act_persona, container, false)
        campoId = vista.findViewById(R.id.campoIdentificacionAct)
        campoNombre = vista.findViewById(R.id.campoNombreAct)
        btnCerrar = vista.findViewById(R.id.btnIcoCerrrar)
        btnActualizar = vista.findViewById(R.id.btnActualizarAct)

        campoId.setText("" +identificacion)
        campoNombre.setText(nombre)
        eventosMenu()
        return vista
    }

    private fun eventosMenu(){
        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                actualizarUsuario()
            }

        })


        btnCerrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //d?.dismiss()
                dismiss()
            }

        })
    }

    private fun actualizarUsuario() {

        if (campoId.text.isEmpty() or campoNombre.text.toString().trim().equals("")) {
            if(campoId.text.toString().isBlank()){
                //id.setError("Este campo no puede quedar vacio")
                campoId.error = "Este Campo no puede quedar vacio"
            }else if (campoNombre.text.toString().isBlank()){
                campoNombre.setError("Este campo no puede quedar vacio")
            }
            Toast.makeText(context, "Verifique que todos los campos esten llenos\n ", Toast.LENGTH_LONG).show()
        }else{

            val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
            val db: SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()
            values.put(Utilidades.CAMPO_ID_PERSONA,campoId.text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_NOMBRE_PERSONA, campoNombre.text.toString().trim())

            val idResultante: Number = db.update(
                Utilidades.TABLA_PERSONA,
                values,
                Utilidades.CAMPO_ID_PERSONA+"="+identificacion,
                null
            )

            if (idResultante != -1) {
                Toast.makeText(
                    context,
                    "¡El usuario se Actualizó Exitosamente!",
                    Toast.LENGTH_SHORT
                ).show()
                DialogoGesPersona.llenarAdaptadorUsuarios()
                dismiss()
                db.close()

            } else {
                Toast.makeText(
                    context,
                    "EL usuario no se pudo Actualizar, intente nuevamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

    companion object {
        var identificacion: Int = 0
        var nombre: String = ""
    }
}