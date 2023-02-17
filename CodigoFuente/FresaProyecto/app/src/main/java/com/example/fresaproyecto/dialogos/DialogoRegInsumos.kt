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
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.floatingactionbutton.FloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogoRegInsumos.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoRegInsumos : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnGuardar: Button
    lateinit var fabAtras: ImageButton
    lateinit var campoNombre: EditText
    lateinit var campoCantidad: EditText
    lateinit var campoCantidadUsado: EditText
    lateinit var campoPrecio: EditText
    lateinit var campoFecha: EditText
    lateinit var unidadSpinner: Spinner
    lateinit var elemento: String
    var listaUnidad: ArrayList<String>? = ArrayList<String>()
    lateinit var adp: ArrayAdapter<*>
    var dia: Int = 0
    var mes: Int = 0
    var año: Int = 0

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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Llenando Spinner de Unidades
        listaUnidad!!.add("Kg (Kilogramos)")
        listaUnidad!!.add("g (Gramos)")
        listaUnidad!!.add("cm³ (Centimetros Cúbicos)")

        adp = ArrayAdapter(actividad, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaUnidad!!)

        vista = inflater.inflate(R.layout.fragment_dialogo_reg_insumos, container, false)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        campoNombre = vista.findViewById(R.id.campoNombreInsumo)
        campoCantidad = vista.findViewById(R.id.campoCantidad)
        campoCantidadUsado = vista.findViewById(R.id.campoCantidadUsada)
        campoPrecio = vista.findViewById(R.id.campoPrecio)
        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        campoFecha = vista.findViewById(R.id.campoFechaInsumo)
        campoFecha.setOnClickListener{ showDatePickerDialog() }

        unidadSpinner = vista.findViewById(R.id.spinnerUnidad)

        unidadSpinner.adapter=adp
        unidadSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                elemento= unidadSpinner.adapter.getItem(position) as String
                Toast.makeText(actividad, "Seleccionaste :\n" + elemento, Toast.LENGTH_LONG).show()

            }

        }


        eventosMenu()




        return vista
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(year, month+1, day)}
        datePicker.show(parentFragmentManager, "datePicker")
    }

    fun onDateSelected (day:Int, month:Int, year:Int){
        campoFecha.setText("$year-$month-$day")
        dia=day
        mes=month
        año=year
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
                registrarInsumo()
            }

        })



    }


    private fun registrarInsumo(){

        if (campoFecha.text.isEmpty() or campoNombre.text.trim().equals("") or campoPrecio.text.isEmpty() or campoCantidad.text.isEmpty() or campoCantidadUsado.text.isEmpty()){
            Toast.makeText(actividad, "Verifique que todos los campos esten registrados \n ", Toast.LENGTH_LONG).show()

            if (campoFecha.text.isEmpty()) {
                campoFecha.setError("Este campo no puede estar vacio")
            }

            if (campoNombre.text.toString().trim().equals("")) {
                campoNombre.setError("Este campo no puede estar vacio")
            }

            if (campoCantidad.text.isEmpty()) {
                campoCantidad.setError("Este campo no puede estar vacio")
            }

            if (campoPrecio.text.isEmpty()) {
                campoPrecio.setError("Este campo no puede estar vacio")
            }

            if (campoCantidadUsado.text.isEmpty()) {
                campoCantidadUsado.setError("Este campo no puede estar vacio")
            }

        }else{

            var registro= "Nombre: "+campoNombre.text.toString()+"\n"
            registro +="Cantidad: "+campoPrecio.text.toString()+"\n"
            registro +="Cantidad: "+campoCantidad.text.toString()+"\n"
            print("Registrar:  "+registro)
            Toast.makeText(actividad, "REGISTRAR:\n"+registro, Toast.LENGTH_LONG).show()
            //La linea sigueinte deberia ir dentro de un IF que verifique si la consulta SQL es correcta

            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null,1)
            val db: SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()

            //valores para agregar a la tabla de cultivos
            //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_NOMBRE_INSUMO, campoNombre.text.toString().trim())
            values.put(Utilidades.CAMPO_DIA_INSUMO, dia)
            values.put(Utilidades.CAMPO_MES_INSUMO, mes)
            values.put(Utilidades.CAMPO_AÑO_INSUMO, año)
            values.put(Utilidades.CAMPO_CANT_INSUMO, campoCantidad.text.toString())
            values.put(Utilidades.CAMPO_CANT_USADO_INSUMO, campoCantidadUsado.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_INSUMO, campoPrecio.text.toString())
            values.put(Utilidades.CAMPO_UNIDAD_INSUMO, elemento)
            values.put(Utilidades.CAMPO_CULTIVO_INSUMO, DialogoGesCultivo.cultivoSeleccionado.id)
            val idResultante:Number = db.insert(Utilidades.TABLA_INSUMOS, Utilidades.CAMPO_ID_CULTIVO, values)

            if(idResultante != -1){
                println("Registrar: " +registro)
                Toast.makeText(actividad, "¡Registro Éxitoso! " +registro, Toast.LENGTH_SHORT).show()
                //Utilidades.calcularBeneficioCultivo(actividad)

            }else{
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT).show()
            }
            db.close()

            dismiss()



        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DialogoRegInsumos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoRegInsumos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}