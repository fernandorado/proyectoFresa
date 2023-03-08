package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
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
import com.example.fresaproyecto.adapters.AdaptadorJornalMesCultivo
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.JornalCultivoVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class DialogoRegJornal : DialogFragment() {
    var idCultivo = DialogoGesCultivo.cultivoSeleccionado.id

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnGuardar: Button
    lateinit var btnActualizar: Button
    lateinit var btnCancelar: Button
    lateinit var fabAtras: ImageButton
    lateinit var txtTituloJornal: TextView
    lateinit var campoCantidad: EditText
    lateinit var campoActividad: EditText
    lateinit var campoPrecio: EditText
    lateinit var campoFecha: EditText
    lateinit var actividadLinearLayout: LinearLayout
    lateinit var idLayoutAct: LinearLayout
    lateinit var actividadSpinner: Spinner
    lateinit var recyclerJornalDia: RecyclerView
    lateinit var jornalSeleccionado : JornalCultivoVo
    var listaActividad: ArrayList<String>? = ArrayList<String>()
    lateinit var adp: ArrayAdapter<*>
    lateinit var actividadSelec: String

    var dia: Int = 0
    var mes: Int = 0
    var año: Int = 0

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
        txtTituloJornal = vista.findViewById(R.id.txtTituloJornal)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        btnActualizar = vista.findViewById(R.id.idBtnActualizarJornal)
        btnCancelar = vista.findViewById(R.id.idBtnCancelarActJornal)
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

                if(actividadSelec == "OTRO"){
                    actividadLinearLayout.visibility = View.VISIBLE
                }else{
                    actividadLinearLayout.visibility = View.GONE
                }
            }
        }
        actividadLinearLayout = vista.findViewById(R.id.actividadLinearLayout)
        idLayoutAct = vista.findViewById(R.id.idLayoutAct)
        recyclerJornalDia = vista.findViewById(R.id.recyclerJornalRegistros)
        recyclerJornalDia.layoutManager = LinearLayoutManager(actividad)
        recyclerJornalDia.setHasFixedSize(true)

        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        val dateFormatD = SimpleDateFormat("dd")
        val diaActual = dateFormatD.format(Date())

        jornalPorDia(añoActual.toInt(), mesActual.toInt(), diaActual.toInt())
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
        jornalPorDia(año, mes, dia)
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

        btnCancelar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                cancelarAct()

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

    private fun jornalPorDia(año: Int, mes: Int, dia:Int) {
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.consultarJornalDia(actividad, mes, año, dia, idCultivo)

        var miAdaptadorJornal = AdaptadorJornalMesCultivo(Utilidades.listaJornalCultivo!!)
        miAdaptadorJornal.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                jornalSeleccionado = Utilidades.listaJornalCultivo!!.get(recyclerJornalDia.getChildAdapterPosition(view!!))
                println(jornalSeleccionado.id)
                mostrarDialogOpciones()
            }
        })

        recyclerJornalDia.adapter = miAdaptadorJornal
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

    fun editar() {
        campoFecha.setText("${jornalSeleccionado.año}-${jornalSeleccionado.mes}-${jornalSeleccionado.dia}")
        if (listaActividad!!.contains(jornalSeleccionado.actividad) == false) {
            actividadSelec = "OTRO"
            val posicion = listaActividad!!.indexOf(actividadSelec)
            actividadSpinner.setSelection(posicion)
            campoActividad.setText(jornalSeleccionado.actividad)
        } else {
            val posicion = listaActividad!!.indexOf(jornalSeleccionado.actividad)
            actividadSpinner.setSelection(posicion)
        }
        campoCantidad.setText(""+jornalSeleccionado.cantidadJornal)
        campoPrecio.setText(""+jornalSeleccionado.precioJornal)
        txtTituloJornal.setText("ACTUALIZAR JORNAL")
        btnGuardar.visibility = View.GONE
        idLayoutAct.visibility = View.VISIBLE
        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                actualizarJornal()

            }

        })

    }

    private fun actualizarJornal() {
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

            val idResultante: Number = db.update(
                Utilidades.TABLA_JORNAL,
                values,
                Utilidades.CAMPO_ID_JORNAL + "=" + jornalSeleccionado.id,
                null
            )

            if (idResultante != -1) {
                jornalPorDia(año, mes, dia)
                cancelarAct()
                Toast.makeText(
                    context,
                    "¡El jornal se Actualizó Exitosamente!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "EL jornal no se pudo Actualizar, intente nuevamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            db.close()

        }
    }

    fun cancelarAct(){
        campoFecha.setText("")
        btnGuardar.visibility = View.VISIBLE
        idLayoutAct.visibility = View.GONE
        if (listaActividad!!.contains(jornalSeleccionado.actividad) == false) {
            actividadSelec = "OTRO"
            val posicion = listaActividad!!.indexOf(actividadSelec)
            actividadSpinner.adapter.getItem(posicion)
            campoActividad.setText("")
        } else {
            val posicion = listaActividad!!.indexOf(jornalSeleccionado.actividad)
            actividadSpinner.adapter.getItem(posicion)
        }
        campoCantidad.setText("")
        campoPrecio.setText("")
        txtTituloJornal.setText("REGISTRO DE JORNAL")
    }

    fun dialogoEliminar(): android.app.AlertDialog {

        val builder = android.app.AlertDialog.Builder(vista.context)

        builder.setTitle("Eliminar")
            .setMessage("Esta seguro que quiere eliminar este Jornal?")
            .setPositiveButton("Si") { dialog, _ ->

                eliminarCultivo()
                dialog.dismiss()

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    private fun eliminarCultivo() {


        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_JORNAL,
                Utilidades.CAMPO_ID_JORNAL + "=" + jornalSeleccionado.id,
                null
            )

        if (idResultante != -1) {
            println("El jornal se eliminó Exitosamente")
            Toast.makeText(context, "¡El jornal se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            //DialogoGesCultivo.llenarAdaptadorCultivos()
            jornalPorDia(año, mes, dia)

        } else {
            Toast.makeText(context, "EL jornal no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }
        db.close()

    }


}