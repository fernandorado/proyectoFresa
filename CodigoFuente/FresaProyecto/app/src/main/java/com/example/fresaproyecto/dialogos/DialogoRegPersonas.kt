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
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.MainActivity
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogoRegPersonas.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoRegPersonas : DialogFragment() {


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var campoNombre: EditText
    lateinit var campoIdentificacion: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnCancelar: Button



    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments


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

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {


        return activity?.let {
            val builder = AlertDialog.Builder(it)
            // Get the layout inflater
            val inflater = requireActivity().layoutInflater;



            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(inflater.inflate(R.layout.fragment_dialogo_reg_personas, null))
                // Add action buttons
                .setPositiveButton(R.string.Registrar,
                    DialogInterface.OnClickListener { dialog, id ->


                        //Se hace el llamado al metodo de registrarPersona
                        //registrarPersona()
                        if (campoIdentificacion.text.toString().isBlank()){

                            campoIdentificacion.setError("Esto no puede quedar vacio")
                            Toast.makeText(actividad, "Verifique que todos los campos esten registrados \n ", Toast.LENGTH_LONG).show()


                        }else{
                            var registro= "Identificacion: "+campoIdentificacion.text.toString()+"\n"
                            registro +="Nombre: "+campoNombre.text.toString()+"\n"
                            print("Registrar:  "+registro)
                            Toast.makeText(actividad, "REGISTRAR:\n"+registro, Toast.LENGTH_LONG).show()

                            interfaceComunicaFragments.menuPersonal()
                        }


                    })
                .setNegativeButton(R.string.Cancelar,
                    DialogInterface.OnClickListener { dialog, id ->
                        dismiss()
                    })
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        vista = inflater.inflate(R.layout.fragment_dialogo_reg_personas, container,false)


        campoNombre = vista.findViewById(R.id.campoNombreP)
        campoIdentificacion = vista.findViewById(R.id.campoIdentificacion)
        btnRegistrar = vista.findViewById(R.id.btnRegistrar)
        btnCancelar = vista.findViewById(R.id.btnCancelar)

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

        btnCancelar.setOnClickListener(object : View.OnClickListener {
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
            values.put(Utilidades.CAMPO_NOMBRE_PERSONA, campoNombre.text.toString())
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

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DialogoRegPersonas.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoRegPersonas().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}