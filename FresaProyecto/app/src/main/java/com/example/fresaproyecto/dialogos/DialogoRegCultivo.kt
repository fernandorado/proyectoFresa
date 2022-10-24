package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.floatingactionbutton.FloatingActionButton

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
    lateinit var fabRegistro: FloatingActionButton
    lateinit var fabAtras: ImageButton
    lateinit var campoName: EditText
    lateinit var campoCant: EditText




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
        fabRegistro = vista.findViewById(R.id.idFabRegistro)
        campoName = vista.findViewById(R.id.campoNombre)
        campoCant = vista.findViewById(R.id.campoCantidad)
        fabAtras = vista.findViewById(R.id.btnIcoAtras)
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
        fabRegistro.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                registrarCultivo()
            }

        })
    }


    private fun registrarCultivo(){
         if((campoName.text.toString()!=null && !campoName.text.toString().trim().equals("")) and (campoCant.text.toString()!=null && !campoCant.text.toString().trim().equals(""))){
             var registro= "Nombre: "+campoName.text.toString()+"\n"
             registro +="Cantidad: "+campoCant.text.toString()+"\n"
             print("Registrar:  "+registro)
             Toast.makeText(actividad, "REGISTRAR:\n"+registro, Toast.LENGTH_LONG).show()
             //La linea sigueinte deberia ir dentro de un IF que verifique si la consulta SQL es correcta
             interfaceComunicaFragments.menuCultivo()
             //conexion con la base de datos
             val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null,1)
             val db: SQLiteDatabase = conexion.writableDatabase
             val values = ContentValues()

             //valores para agregar a la tabla de cultivos
             //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
             values.put(Utilidades.CAMPO_NOMBRE_CULTIVO, campoName.text.toString())
             values.put(Utilidades.CAMPO_CANT_PLANTAS, campoCant.text.toString())
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