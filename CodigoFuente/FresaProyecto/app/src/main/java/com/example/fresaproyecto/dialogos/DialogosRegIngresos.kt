package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorIngresoMesPersona
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.IngresoPersonalVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DialogosRegIngresos : DialogFragment() {

    // TODO: Rename and change types of parameters


    lateinit var campoFecha: EditText
    lateinit var campoMonto: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnActualizar: Button
    lateinit var btnCancelar: Button
    lateinit var txtTituloIngreso: TextView
    lateinit var idLayoutAct: LinearLayout
    lateinit var recyclerIngresoDia: RecyclerView
    var idPersona = DialogoGesPersona.personaSeleccionada.id
    lateinit var conceptoLinearLayout: LinearLayout
    lateinit var campoConcepto: EditText
    lateinit var ingresoSeleccionado: IngresoPersonalVo
    lateinit var btnCerrar: ImageButton
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

        listaIngresos!!.add("Venta de Fresa")
        listaIngresos!!.add("Jornales")
        listaIngresos!!.add("Venta de Ganado")
        listaIngresos!!.add("Venta de Leche")
        listaIngresos!!.add("Apoyos del Gobierno")
        listaIngresos!!.add("Negocio Familiar")
        listaIngresos!!.add("Prestamos")
        listaIngresos!!.add("OTRO")

        adp = ArrayAdapter(
            actividad,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listaIngresos!!
        )
        vista = inflater.inflate(R.layout.fragment_dialogos_reg_ingresos, container, false)

        campoFecha = vista.findViewById(R.id.campoFechaIngreso)
        campoFecha.setOnClickListener { showDatePickerDialog() }
        campoMonto = vista.findViewById(R.id.campoMonto)
        btnRegistrar = vista.findViewById(R.id.btnRegistrar)
        btnActualizar = vista.findViewById(R.id.idBtnActualizarIngreso)
        btnCancelar = vista.findViewById(R.id.idBtnCancelarActIngreso)
        idLayoutAct = vista.findViewById(R.id.idLayoutAct)
        recyclerIngresoDia = vista.findViewById(R.id.recyclerIngresoRegistros)
        recyclerIngresoDia.layoutManager = LinearLayoutManager(actividad)
        recyclerIngresoDia.setHasFixedSize(true)
        conceptoLinearLayout = vista.findViewById(R.id.conceptoLinearLayout)
        txtTituloIngreso = vista.findViewById(R.id.txtTituloIngreso)
        campoConcepto = vista.findViewById(R.id.campoConceptoOtro)
        btnCerrar = vista.findViewById(R.id.btnIcoCerrar)
        ingresosSpinner = vista.findViewById(R.id.spinnerIngresos)

        ingresosSpinner.adapter = adp
        ingresosSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                concepto = ingresosSpinner.adapter.getItem(position) as String
                if (concepto == "OTRO") {
                    conceptoLinearLayout.visibility = View.VISIBLE
                } else {
                    conceptoLinearLayout.visibility = View.GONE
                }

            }

        }

        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        val dateFormatD = SimpleDateFormat("dd")
        val diaActual = dateFormatD.format(Date())

        ingresoPorDia(añoActual.toInt(), mesActual.toInt(), diaActual.toInt())

        eventosMenu()
        // Inflate the layout for this fragment
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
        ingresoPorDia(año, mes, dia)
    }


    private fun eventosMenu() {
        btnRegistrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                registrarIngreso()
            }

        })

        btnCerrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()


            }

        })

        btnCancelar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                cancelarAct()

            }

        })
    }

    private fun ingresoPorDia(año: Int, mes: Int, dia: Int) {
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.consultaIngresoPersonalDia(actividad, dia, mes, año, idPersona)

        var miAdaptadorIngreso = AdaptadorIngresoMesPersona()
        miAdaptadorIngreso.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                ingresoSeleccionado = Utilidades.listaIngresoPersonal!!.get(
                    recyclerIngresoDia.getChildAdapterPosition(view!!)
                )
                println(ingresoSeleccionado.id)
                mostrarDialogOpciones()
            }
        })

        recyclerIngresoDia.adapter = miAdaptadorIngreso
    }

    private fun mostrarDialogOpciones() {
        val opciones = arrayOf<CharSequence>("Editar", "Eliminar", "Cancelar")
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Elige una Opción")
        builder.setItems(opciones, DialogInterface.OnClickListener { dialogInterface, i ->
            if (opciones[i] == "Editar") {
                editar()
            } else {
                if (opciones[i] == "Eliminar") {
                    dialogoEliminar().show()
                } else {
                    dialogInterface.dismiss()
                }
            }
        })
        builder.show()
    }

    fun dialogoEliminar(): android.app.AlertDialog {

        val builder = android.app.AlertDialog.Builder(vista.context)

        builder.setTitle("Eliminar")
            .setMessage("Esta seguro que quiere eliminar este Ingreso?")
            .setPositiveButton("Si") { dialog, _ ->

                eliminarIngreso()
                dialog.dismiss()

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    private fun eliminarIngreso() {


        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_INGRESO_PERSONAL,
                Utilidades.CAMPO_ID_INGRESO + "=" + ingresoSeleccionado.id,
                null
            )

        if (idResultante != -1) {
            Toast.makeText(context, "¡El Ingreso se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            //DialogoGesCultivo.llenarAdaptadorCultivos()
            ingresoPorDia(año, mes, dia)

        } else {
            Toast.makeText(context, "EL Ingreso no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }
        db.close()

    }

    fun editar() {
        dia = ingresoSeleccionado.dia
        mes = ingresoSeleccionado.mes
        año = ingresoSeleccionado.año
        campoFecha.setText("${año}-${mes}-${dia}")
        if (listaIngresos!!.contains(ingresoSeleccionado.concepto) == false) {
            concepto = "OTRO"
            val posicion = listaIngresos!!.indexOf(concepto)
            ingresosSpinner.setSelection(posicion)
            campoConcepto.setText(ingresoSeleccionado.concepto)
        } else {
            val posicion = listaIngresos!!.indexOf(ingresoSeleccionado.concepto)
            ingresosSpinner.setSelection(posicion)
        }
        campoMonto.setText("" + ingresoSeleccionado.precio)
        txtTituloIngreso.setText("ACTUALIZAR INGRESO")
        btnRegistrar.visibility = View.GONE
        idLayoutAct.visibility = View.VISIBLE
        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                actualizarIngreso()

            }

        })

    }

    private fun actualizarIngreso() {

        if (campoFecha.text.isEmpty() or campoMonto.text.isEmpty() or ((concepto == "OTRO") and (campoConcepto.text.toString()
                .trim().equals("")))
        ) {
            if (campoFecha.text.isEmpty()) {
                campoFecha.setError("Este campo no puede quedar vacio")
            }
            if (campoMonto.text.toString().isEmpty()) {
                campoMonto.setError("Este campo no puede quedar vacio")
            }
            if ((concepto == "OTRO") and (campoConcepto.text.toString().trim().equals(""))) {
                campoConcepto.setError("Este campo no puede quedar vacio")
            }
            Toast.makeText(
                actividad,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_LONG
            ).show()

        } else {

            //conexion con la base de datos
            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null, 1)
            val db: SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()

            //valores para agregar a la tabla de cultivos
            //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_DIA_INGRESO, dia)
            values.put(Utilidades.CAMPO_MES_INGRESO, mes)
            values.put(Utilidades.CAMPO_AÑO_INGRESO, año)
            if (concepto == "OTRO") {
                values.put(Utilidades.CAMPO_CONCEPTO_INGRESO, campoConcepto.text.toString().trim())
            } else {
                values.put(Utilidades.CAMPO_CONCEPTO_INGRESO, concepto)
            }
            values.put(Utilidades.CAMPO_PRECIO_INGRESO, campoMonto.text.toString())
            values.put(Utilidades.CAMPO_PERSONA_INGRESO, DialogoGesPersona.personaSeleccionada.id)
            val idResultante: Number = db.update(
                Utilidades.TABLA_INGRESO_PERSONAL,
                values,
                Utilidades.CAMPO_ID_INGRESO + "=" + ingresoSeleccionado.id,
                null
            )

            if (idResultante != -1) {
                ingresoPorDia(año, mes, dia)
                cancelarAct()
                Toast.makeText(context, "¡El jornal se Actualizó Exitosamente!", Toast.LENGTH_SHORT)
                    .show()
                //Utilidades.calcularBeneficioPersonal(actividad)

            } else {
                Toast.makeText(
                    context,
                    "EL jornal no se pudo Actualizar, intente nuevamente.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            db.close()
        }

    }

    fun cancelarAct() {
        campoFecha.setText("")
        if (listaIngresos!!.contains(ingresoSeleccionado.concepto) == false) {
            concepto = "OTRO"
            val posicion = listaIngresos!!.indexOf(concepto)
            ingresosSpinner.setSelection(posicion)
            campoConcepto.setText("")
        } else {
            val posicion = listaIngresos!!.indexOf(ingresoSeleccionado.concepto)
            ingresosSpinner.setSelection(posicion)
        }
        campoMonto.setText("")
        btnRegistrar.visibility = View.VISIBLE
        idLayoutAct.visibility = View.GONE
        txtTituloIngreso.setText("REGISTRO DE INGRESO")
    }

    private fun registrarIngreso() {


        if (campoFecha.text.isEmpty() or campoMonto.text.isEmpty() or ((concepto == "OTRO") and (campoConcepto.text.toString()
                .trim().equals("")))
        ) {
            if (campoFecha.text.isEmpty()) {
                campoFecha.setError("Este campo no puede quedar vacio")
            }
            if (campoMonto.text.toString().isEmpty()) {
                campoMonto.setError("Este campo no puede quedar vacio")
            }
            if ((concepto == "OTRO") and (campoConcepto.text.toString().trim().equals(""))) {
                campoConcepto.setError("Este campo no puede quedar vacio")
            }
            Toast.makeText(
                actividad,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_LONG
            ).show()

        } else {

            //conexion con la base de datos
            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null, 1)
            val db: SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()

            //valores para agregar a la tabla de cultivos
            //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_DIA_INGRESO, dia)
            values.put(Utilidades.CAMPO_MES_INGRESO, mes)
            values.put(Utilidades.CAMPO_AÑO_INGRESO, año)
            if (concepto == "OTRO") {
                values.put(Utilidades.CAMPO_CONCEPTO_INGRESO, campoConcepto.text.toString().trim())
            } else {
                values.put(Utilidades.CAMPO_CONCEPTO_INGRESO, concepto)
            }
            values.put(Utilidades.CAMPO_PRECIO_INGRESO, campoMonto.text.toString())
            values.put(Utilidades.CAMPO_PERSONA_INGRESO, DialogoGesPersona.personaSeleccionada.id)
            val idResultante: Number =
                db.insert(Utilidades.TABLA_INGRESO_PERSONAL, Utilidades.CAMPO_ID_INGRESO, values)

            if (idResultante != -1) {
                Toast.makeText(actividad, "¡Registro Éxitoso! ", Toast.LENGTH_SHORT)
                    .show()
                //Utilidades.calcularBeneficioPersonal(actividad)

            } else {
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT)
                    .show()
            }
            db.close()

            dismiss()
        }


    }

}