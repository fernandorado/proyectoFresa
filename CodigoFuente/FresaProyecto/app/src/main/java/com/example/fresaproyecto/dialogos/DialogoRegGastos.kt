package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorGastoMesPersona
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.GastoPersonalVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DialogoRegGastos : DialogFragment() {
    lateinit var campoFecha: EditText
    lateinit var campoMonto: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnActualizar: Button
    lateinit var btnCancelar: Button
    lateinit var txtTituloGasto: TextView
    lateinit var idLayoutAct: LinearLayout
    lateinit var recyclerGastoDia: RecyclerView
    var idPersona = DialogoGesPersona.personaSeleccionada.id
    lateinit var conceptoLinearLayout: LinearLayout
    lateinit var campoConcepto: EditText
    lateinit var btnCerrar: ImageButton
    lateinit var gastosSpinner: Spinner
    lateinit var gastoSeleccionado: GastoPersonalVo
    var concepto: String = "" //Elemento seleccionado del Spinner (Concepto del ingreso)
    var dia: Int = 0
    var mes: Int = 0
    var año: Int = 0
    var listaGastos: ArrayList<String>? = ArrayList<String>()

    lateinit var adp: ArrayAdapter<*>

    lateinit var vista: View
    lateinit var actividad: Activity
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
        listaGastos!!.add("OTRO")

        adp = ArrayAdapter(
            actividad,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listaGastos!!
        )
        vista = inflater.inflate(R.layout.fragment_dialogo_reg_gastos, container, false)

        campoFecha = vista.findViewById(R.id.campoFechaGasto)
        campoFecha.setOnClickListener { showDatePickerDialog() }
        campoMonto = vista.findViewById(R.id.campoMonto)
        gastosSpinner = vista.findViewById(R.id.spinnerGastos)
        btnRegistrar = vista.findViewById(R.id.btnRegistrar)
        btnActualizar = vista.findViewById(R.id.idBtnActualizarGasto)
        btnCancelar = vista.findViewById(R.id.idBtnCancelarActGasto)
        idLayoutAct = vista.findViewById(R.id.idLayoutAct)
        recyclerGastoDia = vista.findViewById(R.id.recyclerGastoRegistros)
        recyclerGastoDia.layoutManager = LinearLayoutManager(actividad)
        recyclerGastoDia.setHasFixedSize(true)
        conceptoLinearLayout = vista.findViewById(R.id.conceptoLinearLayout)
        campoConcepto = vista.findViewById(R.id.campoConceptoOtro)
        txtTituloGasto = vista.findViewById(R.id.txtTituloGasto)
        btnCerrar = vista.findViewById(R.id.btnIcoCerrar)

        gastosSpinner.adapter = adp
        gastosSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                concepto = gastosSpinner.adapter.getItem(position) as String

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

        gastoPorDia(añoActual.toInt(), mesActual.toInt(), diaActual.toInt())

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
        gastoPorDia(año, mes, dia)
    }

    private fun eventosMenu() {
        btnRegistrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                registrarGastos()
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

    private fun gastoPorDia(año: Int, mes: Int, dia: Int) {
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.consultarGastoPersonalDia(actividad, dia, mes, año, idPersona)

        var miAdaptadorGasto = AdaptadorGastoMesPersona()
        miAdaptadorGasto.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                gastoSeleccionado = Utilidades.listaGastoPersonal!!.get(
                    recyclerGastoDia.getChildAdapterPosition(view!!)
                )
                mostrarDialogOpciones()
            }
        })

        recyclerGastoDia.adapter = miAdaptadorGasto
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
            .setMessage("Esta seguro que quiere eliminar este Gasto?")
            .setPositiveButton("Si") { dialog, _ ->

                eliminarGasto()
                dialog.dismiss()

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    private fun eliminarGasto() {


        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_GASTO_PERSONAL,
                Utilidades.CAMPO_ID_GASTO + "=" + gastoSeleccionado.id,
                null
            )

        if (idResultante != -1) {
            Toast.makeText(context, "¡El Gasto se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            //DialogoGesCultivo.llenarAdaptadorCultivos()
            gastoPorDia(año, mes, dia)

        } else {
            Toast.makeText(context, "EL Gasto no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }
        db.close()

    }

    fun editar() {
        dia = gastoSeleccionado.dia
        mes = gastoSeleccionado.mes
        año = gastoSeleccionado.año
        campoFecha.setText("${año}-${mes}-${dia}")
        if (listaGastos!!.contains(gastoSeleccionado.concepto) == false) {
            concepto = "OTRO"
            val posicion = listaGastos!!.indexOf(concepto)
            gastosSpinner.setSelection(posicion)
            campoConcepto.setText(gastoSeleccionado.concepto)
        } else {
            val posicion = listaGastos!!.indexOf(gastoSeleccionado.concepto)
            gastosSpinner.setSelection(posicion)
        }
        campoMonto.setText("" + gastoSeleccionado.precio)
        txtTituloGasto.setText("ACTUALIZAR GASTO")
        btnRegistrar.visibility = View.GONE
        idLayoutAct.visibility = View.VISIBLE
        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                actualizarGasto()

            }

        })

    }

    private fun actualizarGasto() {

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
            values.put(Utilidades.CAMPO_DIA_GASTO, dia)
            values.put(Utilidades.CAMPO_MES_GASTO, mes)
            values.put(Utilidades.CAMPO_AÑO_GASTO, año)
            if (concepto == "OTRO") {
                values.put(Utilidades.CAMPO_CONCEPTO_GASTO, campoConcepto.text.toString().trim())
            } else {
                values.put(Utilidades.CAMPO_CONCEPTO_GASTO, concepto)
            }
            values.put(Utilidades.CAMPO_PRECIO_GASTO, campoMonto.text.toString())
            values.put(Utilidades.CAMPO_PERSONA_GASTO, DialogoGesPersona.personaSeleccionada.id)
            val idResultante: Number = db.update(
                Utilidades.TABLA_GASTO_PERSONAL,
                values,
                Utilidades.CAMPO_ID_GASTO + "=" + gastoSeleccionado.id,
                null
            )

            if (idResultante != -1) {
                gastoPorDia(año, mes, dia)
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
        if (listaGastos!!.contains(gastoSeleccionado.concepto) == false) {
            concepto = "OTRO"
            val posicion = listaGastos!!.indexOf(concepto)
            gastosSpinner.setSelection(posicion)
            campoConcepto.setText("")
        } else {
            val posicion = listaGastos!!.indexOf(gastoSeleccionado.concepto)
            gastosSpinner.setSelection(posicion)
        }
        campoMonto.setText("")
        btnRegistrar.visibility = View.VISIBLE
        idLayoutAct.visibility = View.GONE
        txtTituloGasto.setText("REGISTRO DE GASTO")
    }

    private fun registrarGastos() {


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
            values.put(Utilidades.CAMPO_DIA_GASTO, dia)
            values.put(Utilidades.CAMPO_MES_GASTO, mes)
            values.put(Utilidades.CAMPO_AÑO_GASTO, año)
            if (concepto == "OTRO") {
                values.put(Utilidades.CAMPO_CONCEPTO_GASTO, campoConcepto.text.toString().trim())
            } else {
                values.put(Utilidades.CAMPO_CONCEPTO_GASTO, concepto)
            }
            values.put(Utilidades.CAMPO_PRECIO_GASTO, campoMonto.text.toString())
            values.put(Utilidades.CAMPO_PERSONA_GASTO, DialogoGesPersona.personaSeleccionada.id)
            val idResultante: Number =
                db.insert(Utilidades.TABLA_GASTO_PERSONAL, Utilidades.CAMPO_ID_GASTO, values)

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

    companion object {
    }
}