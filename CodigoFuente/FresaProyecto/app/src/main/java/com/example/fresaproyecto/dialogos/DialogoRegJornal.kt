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
 * Use the [DialogoRegJornal.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoRegJornal : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnGuardar: Button
    lateinit var fabAtras: ImageButton
    lateinit var campoCantidad: EditText
    lateinit var campoActividad: EditText
    lateinit var campoPrecio: EditText
    lateinit var campoFecha: EditText
    lateinit var actividadLinearLayout: LinearLayout
    lateinit var actividadSpinner: Spinner
    var listaActividad: ArrayList<String>? = ArrayList<String>()
    lateinit var adp: ArrayAdapter<*>
    lateinit var actividadSelec: String

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
        // Lista de Actividades
        listaActividad!!.add("Limpieza del lote")
        listaActividad!!.add("Preparación del terreno y encalado")
        listaActividad!!.add("Desinfección y elaboración de camas")
        listaActividad!!.add("Puesta de plástico")
        listaActividad!!.add("Montaje fertirriego")
        listaActividad!!.add("Construcción reservorio")
        listaActividad!!.add("Ahoyado del plástico")
        listaActividad!!.add("Siembra de plantas")
        listaActividad!!.add("Resiembra")
        listaActividad!!.add("Deshierbe/desestolone/desflore/deshoje")
        listaActividad!!.add("Fumigación plagas y/o enfermedades")
        listaActividad!!.add("Fertilización foliar")
        listaActividad!!.add("Fertilización al suelo")
        listaActividad!!.add("Cosecha")
        listaActividad!!.add("OTRO")

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_dialogo_reg_jornal, container, false)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        campoFecha = vista.findViewById(R.id.campoFechaJornal)
        campoFecha.setOnClickListener { showDatePickerDialog() }
        campoCantidad = vista.findViewById(R.id.campoCantidadJor)
        campoPrecio = vista.findViewById(R.id.campoPrecio)
        campoActividad = vista.findViewById(R.id.campoActividadOtro)
        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        // Spinner Actividad
        adp = ArrayAdapter(
            actividad,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listaActividad!!
        )

        actividadSpinner = vista.findViewById(R.id.spinnerActividad)
        actividadSpinner.adapter = adp
        actividadSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                actividadSelec = actividadSpinner.adapter.getItem(position) as String
                Toast.makeText(actividad, "Seleccionaste :\n" + actividadSelec, Toast.LENGTH_LONG)
                    .show()
                if(actividadSelec == "OTRO"){
                    actividadLinearLayout.visibility = View.VISIBLE
                }else{
                    actividadLinearLayout.visibility = View.GONE
                }
            }
        }
        actividadLinearLayout = vista.findViewById(R.id.actividadLinearLayout)
        eventosMenu()
        return vista
    }

    private fun showDatePickerDialog() {
        val datePicker =
            DatePickerFragment { day, month, year -> onDateSelected(year, month + 1, day) }
        datePicker.show(parentFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        campoFecha.setText("$year-$month-$day")
        dia = day
        mes = month
        año = year
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
                registrarJornal()
            }

        })
    }


    private fun registrarJornal() {
        if (campoFecha.text.isEmpty() or campoCantidad.text.isEmpty() or campoPrecio.text.isEmpty() or ((actividadSelec == "OTRO") and (campoActividad.text.toString().trim().equals("")))
        ) {
            Toast.makeText(
                actividad,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_SHORT
            ).show()
            if (campoFecha.text.isEmpty()) {
                campoFecha.setError("Este campo no puede estar vacio")
            }

            if (campoCantidad.text.isEmpty()) {
                campoCantidad.setError("Este campo no puede estar vacio")
            }

            if (campoPrecio.text.isEmpty()) {
                campoPrecio.setError("Este campo no puede estar vacio")
            }

            if ((actividadSelec == "OTRO") and (campoActividad.text.toString().trim().equals(""))) {
                campoActividad.setError("Este campo no puede quedar vacio")
            }
        } else {

            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null, 1)
            val db: SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()

            //valores para agregar a la tabla de cultivos
            //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_DIA_JORNAL, dia)
            values.put(Utilidades.CAMPO_MES_JORNAL, mes)
            values.put(Utilidades.CAMPO_AÑO_JORNAL, año)
            values.put(Utilidades.CAMPO_CANT_JORNAL, campoCantidad.text.toString())
            if (actividadSelec == "OTRO") {
                values.put(Utilidades.CAMPO_ACTV_JORNAL, campoActividad.text.toString().trim())
            } else {
                values.put(Utilidades.CAMPO_ACTV_JORNAL, actividadSelec)
            }
            values.put(Utilidades.CAMPO_PRECIO_JORNAL, campoPrecio.text.toString())
            values.put(Utilidades.CAMPO_CULTIVO_JORNAL, DialogoGesCultivo.cultivoSeleccionado.id)
            val idResultante: Number =
                db.insert(Utilidades.TABLA_JORNAL, Utilidades.CAMPO_ID_JORNAL, values)

            if (idResultante != -1) {
                Toast.makeText(actividad, "¡Registro Éxitoso! ", Toast.LENGTH_SHORT)
                    .show()
                //Utilidades.calcularBeneficioCultivo(actividad)

            } else {
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT)
                    .show()
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
         * @return A new instance of fragment DialogoRegJornal.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoRegJornal().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}