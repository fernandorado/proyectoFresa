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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogoRegGastos.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoRegGastos : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var campoFecha: EditText
    lateinit var campoMonto: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnCancelar: Button
    lateinit var gastosSpinner: Spinner
    var concepto: String = "" //Elemento seleccionado del Spinner (Concepto del ingreso)
    var dia: Int = 0
    var mes: Int = 0
    var año: Int = 0
    var listaGastos: ArrayList<String>? = ArrayList<String>()

    lateinit var adp: ArrayAdapter<*>

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        listaGastos!!.add("Arriendo")
        listaGastos!!.add("Energía")
        listaGastos!!.add("Agua")
        listaGastos!!.add("Gas")
        listaGastos!!.add("Celular")
        listaGastos!!.add("Transporte")
        listaGastos!!.add("Salud y Bienestar")
        listaGastos!!.add("Entretenimiento")
        listaGastos!!.add("Vestimenta")
        listaGastos!!.add("Creditos/Prestamos")
        listaGastos!!.add("Alimentación")
        listaGastos!!.add("Ciudado Personal")
        listaGastos!!.add("Gustos")
        listaGastos!!.add("Especiales")
        listaGastos!!.add("Pago de Jornales")
        listaGastos!!.add("Cultivo de Fresa")


        adp = ArrayAdapter(actividad, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaGastos!!)
        vista = inflater.inflate(R.layout.fragment_dialogo_reg_gastos, container,false)

        campoFecha = vista.findViewById(R.id.campoFechaGasto)
        campoFecha.setOnClickListener{ showDatePickerDialog() }
        campoMonto = vista.findViewById(R.id.campoMonto)
        gastosSpinner = vista.findViewById(R.id.spinnerGastos)
        btnRegistrar = vista.findViewById(R.id.btnRegistrar)
        btnCancelar = vista.findViewById(R.id.btnCancelar)

        gastosSpinner.adapter=adp
        gastosSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                concepto = gastosSpinner.adapter.getItem(position) as String
                Toast.makeText(actividad, "Seleccionaste :\n" + concepto, Toast.LENGTH_LONG).show()

            }

        }

        eventosMenu()
        // Inflate the layout for this fragment
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
        btnRegistrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                registrarGastos()
            }

        })

        btnCancelar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()



            }

        })
    }

    private fun registrarGastos(){


        if((campoFecha.text.toString()!=null && !campoFecha.text.toString().trim().equals("")) and (campoMonto.text.toString()!=null && !campoMonto.text.toString().trim().equals(""))) {

            var id = DialogoGesPersona.personaSeleccionada.id
            var registro = "Fecha: " + campoFecha.text.toString() + "\n"
            registro += "Concepto: " + concepto + "\n"
            registro += "Monto: " + campoMonto.text.toString() + "\n"
            registro += "ID: " + id + "\n"
            print("Registrar:  " + registro)
            Toast.makeText(actividad, "REGISTRAR:\n" + registro, Toast.LENGTH_LONG).show()

            //conexion con la base de datos
            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null,1)
            val db: SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()

            //valores para agregar a la tabla de cultivos
            //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_DIA_GASTO, dia)
            values.put(Utilidades.CAMPO_MES_GASTO, mes)
            values.put(Utilidades.CAMPO_AÑO_GASTO, año)
            values.put(Utilidades.CAMPO_CONCEPTO_GASTO, concepto)
            values.put(Utilidades.CAMPO_PRECIO_GASTO, campoMonto.text.toString())
            values.put(Utilidades.CAMPO_PERSONA_GASTO, DialogoGesPersona.personaSeleccionada.id)
            val idResultante:Number = db.insert(Utilidades.TABLA_GASTO_PERSONAL, Utilidades.CAMPO_ID_GASTO, values)

            if(idResultante != -1){
                println("Registrar: " +registro)
                Toast.makeText(actividad, "¡Registro Éxitoso! " +registro, Toast.LENGTH_SHORT).show()
                Utilidades.calcularBeneficioPersonal(actividad)

            }else{
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT).show()
            }
            db.close()

            dismiss()
        }else{
            if(campoFecha.text.toString().isEmpty()){
                campoFecha.setError("Este campo no puede quedar vacio")
            }else if (campoMonto.text.toString().isEmpty()){
                campoMonto.setError("Este campo no puede quedar vacio")
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
         * @return A new instance of fragment DialogoRegGastos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoRegGastos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}