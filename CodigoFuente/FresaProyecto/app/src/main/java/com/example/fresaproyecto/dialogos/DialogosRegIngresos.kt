package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogosRegIngresos.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogosRegIngresos : DialogFragment() {

    // TODO: Rename and change types of parameters


    lateinit var campoFecha: EditText
    lateinit var campoMonto: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnCancelar: Button
    lateinit var ingresosSpinner: Spinner
    var concepto: String = "" //Elemento seleccionado del Spinner (Concepto del ingreso)
    var dia: Int = 0
    var mes: Int = 0
    var año: Int = 0
    var listaIngresos: ArrayList<String>? = ArrayList<String>()

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

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //Prueba 1
        val formato1 = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val fechaActual1 = LocalDateTime.now().format(formato1) //comparar fecha actual con la de la base de datos
        println("Mes Actual 1")
        println(fechaActual1)
        //Prueba 2
        val formato2 = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val fechaActual2 = formato2.format(date) //comparar fecha actual con la de la base de datos
        println("Mes Actual 2")
        println(fechaActual2)
        //Prueba 3
        val calendar = Calendar.getInstance()
        val mesActual = Calendar.MONTH
        println("Mes Actual 3")
        println(mesActual)
        //Prueba 4
        val dateTime = LocalDateTime.now()
            .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a"))

        println(dateTime) // 01 de enero de 2017, 22:27:41

        // Prueba 4
        val dateFormat = SimpleDateFormat("MMM")
        val date1 = dateFormat.format(Date())

        println(date1) // 01 de enero de 2017, 22:27:41

        listaIngresos!!.add("Venta de Fresa")
        listaIngresos!!.add("Jornales")
        listaIngresos!!.add("Venta de Ganado")
        listaIngresos!!.add("Venta de Leche")
        listaIngresos!!.add("Apoyos del Gobierno")
        listaIngresos!!.add("Negocio Familiar")
        listaIngresos!!.add("Prestamos")
        listaIngresos!!.add("Otras Ventas")

        adp = ArrayAdapter(actividad, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaIngresos!!)
        vista = inflater.inflate(R.layout.fragment_dialogos_reg_ingresos, container,false)

        campoFecha = vista.findViewById(R.id.campoFechaIngreso)
        campoFecha.setOnClickListener{ showDatePickerDialog() }
        campoMonto = vista.findViewById(R.id.campoMonto)
        btnRegistrar = vista.findViewById(R.id.btnRegistrar)
        btnCancelar = vista.findViewById(R.id.btnCancelar)
        ingresosSpinner = vista.findViewById(R.id.spinnerIngresos)

        ingresosSpinner.adapter=adp
        ingresosSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                concepto = ingresosSpinner.adapter.getItem(position) as String
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
        //campoFecha.setText("$day-$month-$year")
        campoFecha.setText("$day-$month-$year")
        println("MES ANTES: $month")
        dia=day
        mes=month
        año=year
        println("MES DESPUES: $mes")
    }


    private fun eventosMenu(){
        btnRegistrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                registrarIngreso()
            }

        })

        btnCancelar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()



            }

        })
    }

    private fun registrarIngreso(){


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

            values.put(Utilidades.CAMPO_DIA_INGRESO, dia)
            values.put(Utilidades.CAMPO_MES_INGRESO, mes)
            values.put(Utilidades.CAMPO_AÑO_INGRESO, año)
            values.put(Utilidades.CAMPO_CONCEPTO_INGRESO, concepto)
            values.put(Utilidades.CAMPO_PRECIO_INGRESO, campoMonto.text.toString())
            values.put(Utilidades.CAMPO_PERSONA_INGRESO, DialogoGesPersona.personaSeleccionada.id)



            val idResultante:Number = db.insert(Utilidades.TABLA_INGRESO_PERSONAL, Utilidades.CAMPO_ID_INGRESO, values)

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
         * @return A new instance of fragment DialogosRegIngresos.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogosRegIngresos().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}