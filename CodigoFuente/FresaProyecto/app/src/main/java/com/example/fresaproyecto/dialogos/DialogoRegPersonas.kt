package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.MainActivity
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments

class DialogoRegPersonas : DialogFragment() {

    lateinit var campoNombre: EditText
    lateinit var campoIdentificacion: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnCerrar: ImageButton



    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments

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



        vista = inflater.inflate(R.layout.fragment_dialogo_reg_personas, container,false)


        campoNombre = vista.findViewById(R.id.campoNombreP)
        campoIdentificacion = vista.findViewById(R.id.campoIdentificacion)
        btnRegistrar = vista.findViewById(R.id.btnRegistrar)
        btnCerrar = vista.findViewById(R.id.btnCerrar)

        eventosMenu()
        // Inflate the layout for this fragment
        return vista
    }

    private fun eventosMenu(){
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

    private fun registrarPersona(){




        if((campoIdentificacion.text.toString()!=null && !campoIdentificacion.text.toString().trim().equals("")) and (campoNombre.text.toString()!=null && !campoNombre.text.toString().trim().equals(""))) {

            DialogoGesPersona.identificacion=campoIdentificacion.text.toString()


            var registro = "Identificacion: " + campoIdentificacion.text.toString() + "\n"
            registro += "Nombre: " + campoNombre.text.toString() + "\n"
            print("Registrar:  " + registro)
            Toast.makeText(actividad, "REGISTRAR:\n" + registro, Toast.LENGTH_LONG).show()


            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null,1)
            val db:SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()


            //Esto podria modificarlo
            values.put(Utilidades.CAMPO_ID_PERSONA, campoIdentificacion.text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_NOMBRE_PERSONA, campoNombre.text.toString().trim())
            val idResultante:Number = db.insert(Utilidades.TABLA_PERSONA, Utilidades.CAMPO_ID_PERSONA, values)

            if(idResultante != -1){
                println("Registrar: " +registro)
                Toast.makeText(actividad, "¡Registro Éxitoso! " +registro, Toast.LENGTH_SHORT).show()
                DialogoGesPersona.llenarAdaptadorUsuarios()
            }else{
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT).show()
            }
            db.close()
            dismiss()
        }else{
            if(campoIdentificacion.text.toString().isEmpty()){
                campoIdentificacion.setError("Este campo no puede quedar vacio")
            }else if (campoNombre.text.toString().isEmpty()){
                campoNombre.setError("Este campo no puede quedar vacio")
            }
            Toast.makeText(actividad, "Verifique que todos los campos esten registrados \n ", Toast.LENGTH_LONG).show()
        }

    }
}