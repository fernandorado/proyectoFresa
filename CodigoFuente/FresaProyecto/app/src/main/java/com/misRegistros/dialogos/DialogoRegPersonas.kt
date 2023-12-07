package com.misRegistros.dialogos

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.misRegistros.R
import com.misRegistros.clases.ConexionSQLiteHelper
import com.misRegistros.clases.Utilidades
import com.misRegistros.clases.vo.PersonaVo
import com.misRegistros.controllers.PersonaRestController
import com.misRegistros.interfaces.IComunicaFragments

class DialogoRegPersonas : DialogFragment() {

    lateinit var campoNombre: EditText
    lateinit var campoIdentificacion: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnCerrar: ImageButton


    lateinit var vista: View
    lateinit var actividad: Activity
    var personaController: PersonaRestController = PersonaRestController()
    lateinit var interfaceComunicaFragments: IComunicaFragments

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


        vista = inflater.inflate(R.layout.fragment_dialogo_reg_personas, container, false)


        campoNombre = vista.findViewById(R.id.campoNombreP)
        campoIdentificacion = vista.findViewById(R.id.campoIdentificacion)
        btnRegistrar = vista.findViewById(R.id.btnRegistrar)
        btnCerrar = vista.findViewById(R.id.btnCerrar)

        eventosMenu()
        // Inflate the layout for this fragment
        return vista
    }

    private fun eventosMenu() {
        btnRegistrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                registrarPersona()


            }

        })

        btnCerrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()


            }

        })
    }

    private fun registrarPersona() {


        if (campoIdentificacion.text.isEmpty() or campoNombre.text.toString().trim().equals("")) {

            Toast.makeText(
                actividad,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_LONG
            ).show()
            if (campoIdentificacion.text.isEmpty()) {
                campoIdentificacion.setError("Este campo no puede quedar vacio")
            } else if (campoNombre.text.toString().trim().equals("")) {
                campoNombre.setError("Este campo no puede quedar vacio")
            }

        } else {
            var persona : PersonaVo
            persona = PersonaVo()
            persona.id = (campoIdentificacion.text.toString().toInt())
            persona.nombre = campoNombre.text.toString().trim()

            var personaActual : PersonaVo? = personaController.create(actividad as Context,persona)

            if(personaActual != null){
                Toast.makeText(actividad, "¡Registro Éxitoso! ", Toast.LENGTH_SHORT)
                    .show()
                DialogoGesPersona.llenarAdaptadorUsuarios()
                dismiss()
            } else {
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT)
                    .show()
            }
            /*val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null, 1)
            val db: SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()

            values.put(Utilidades.CAMPO_ID_PERSONA,campoIdentificacion.text.toString()
            ) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_NOMBRE_PERSONA, campoNombre.text.toString().trim())
            val idResultante: Number = db.insert(Utilidades.TABLA_PERSONA, Utilidades.CAMPO_ID_PERSONA, values)

            if (idResultante != -1) {
                Toast.makeText(actividad, "¡Registro Éxitoso! ", Toast.LENGTH_SHORT)
                    .show()
                DialogoGesPersona.llenarAdaptadorUsuarios()
                db.close()
                dismiss()
            } else {
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT)
                    .show()
            }

*/

        }

    }
}