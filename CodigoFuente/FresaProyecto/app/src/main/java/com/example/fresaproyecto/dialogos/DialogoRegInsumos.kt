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
import com.example.fresaproyecto.adapters.AdaptadorInsumoMesCultivo
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.InsumoCultivoVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DialogoRegInsumos : DialogFragment() {
    var idCultivo = DialogoGesCultivo.cultivoSeleccionado.id

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnGuardar: Button
    lateinit var btnActualizar: Button
    lateinit var btnCancelar: Button
    lateinit var fabAtras: ImageButton
    lateinit var campoNombre: EditText
    lateinit var campoCantidad: EditText
    lateinit var campoCantidadUsado: EditText
    lateinit var campoPrecio: EditText
    lateinit var campoFecha: EditText
    lateinit var unidadSpinner: Spinner
    lateinit var elemento: String
    lateinit var insumoSeleccionado: InsumoCultivoVo
    lateinit var recyclerInsumoDia: RecyclerView
    lateinit var txtTituloInsumo: TextView
    lateinit var idLayoutAct: LinearLayout
    var listaUnidad: ArrayList<String>? = ArrayList<String>()
    lateinit var adp: ArrayAdapter<*>
    var dia: Int = 0
    var mes: Int = 0
    var año: Int = 0

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
        //Llenando Spinner de Unidades
        listaUnidad!!.add("Kg (Kilogramos)")
        listaUnidad!!.add("g (Gramos)")
        listaUnidad!!.add("cm³ (Centimetros Cúbicos)")


        adp = ArrayAdapter(
            actividad,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            listaUnidad!!
        )

        vista = inflater.inflate(R.layout.fragment_dialogo_reg_insumos, container, false)
        txtTituloInsumo = vista.findViewById(R.id.txtTituloInsumo)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        campoNombre = vista.findViewById(R.id.campoNombreInsumo)
        campoCantidad = vista.findViewById(R.id.campoCantidad)
        campoCantidadUsado = vista.findViewById(R.id.campoCantidadUsada)
        campoPrecio = vista.findViewById(R.id.campoPrecio)
        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        campoFecha = vista.findViewById(R.id.campoFechaInsumo)
        campoFecha.setOnClickListener { showDatePickerDialog() }
        btnActualizar = vista.findViewById(R.id.idBtnActualizarInsumo)
        btnCancelar = vista.findViewById(R.id.idBtnCancelarActInsumo)
        unidadSpinner = vista.findViewById(R.id.spinnerUnidad)

        unidadSpinner.adapter = adp
        unidadSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                elemento = unidadSpinner.adapter.getItem(position) as String
                Toast.makeText(actividad, "Seleccionaste :\n" + elemento, Toast.LENGTH_LONG).show()

            }

        }

        recyclerInsumoDia = vista.findViewById(R.id.recyclerInsumoRegistros)
        recyclerInsumoDia.layoutManager = LinearLayoutManager(actividad)
        recyclerInsumoDia.setHasFixedSize(true)
        idLayoutAct = vista.findViewById(R.id.idLayoutAct)

        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        val dateFormatD = SimpleDateFormat("dd")
        val diaActual = dateFormatD.format(Date())

        insumoPorDia(añoActual.toInt(), mesActual.toInt(), diaActual.toInt())

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
        insumoPorDia(año, mes, dia)
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
                registrarInsumo()
            }

        })

        btnCancelar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                cancelarAct()

            }

        })


    }

    private fun insumoPorDia(año: Int, mes: Int, dia: Int) {
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.consultarInsumosDia(actividad, mes, año, dia, idCultivo)

        var miAdaptadorInsumo = AdaptadorInsumoMesCultivo()
        miAdaptadorInsumo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                insumoSeleccionado = Utilidades.listaInsumoCultivo!!.get(
                    recyclerInsumoDia.getChildAdapterPosition(view!!)
                )
                mostrarDialogOpciones()
            }
        })

        recyclerInsumoDia.adapter = miAdaptadorInsumo
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

    fun cancelarAct() {
        campoFecha.setText("")
        campoNombre.setText("")
        campoPrecio.setText("")
        campoCantidad.setText("")
        campoCantidadUsado.setText("")
        txtTituloInsumo.setText("REGISTRO DE INSUMO")
        btnGuardar.visibility = View.VISIBLE
        idLayoutAct.visibility = View.GONE
    }

    fun editar() {
        dia = insumoSeleccionado.dia
        mes = insumoSeleccionado.mes
        año = insumoSeleccionado.año
        campoFecha.setText("${año}-${mes}-${dia}")
        campoNombre.setText(insumoSeleccionado.nombreInsumo)
        campoPrecio.setText("" + insumoSeleccionado.precioInsumo)
        campoCantidad.setText("" + insumoSeleccionado.cantidadInsumo)
        campoCantidadUsado.setText("" + insumoSeleccionado.cantidadUsado)
        txtTituloInsumo.setText("ACTUALIZAR INSUMO")
        btnGuardar.visibility = View.GONE
        idLayoutAct.visibility = View.VISIBLE
        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                actualizarInsumo()

            }

        })

    }

    fun dialogoEliminar(): android.app.AlertDialog {

        val builder = android.app.AlertDialog.Builder(vista.context)

        builder.setTitle("Eliminar")
            .setMessage("Esta seguro que quiere eliminar este Insumo?")
            .setPositiveButton("Si") { dialog, _ ->

                eliminarInsumo()
                dialog.dismiss()

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    private fun actualizarInsumo() {

        if (campoFecha.text.isEmpty() or campoNombre.text.trim()
                .equals("") or campoPrecio.text.isEmpty() or campoCantidad.text.isEmpty() or campoCantidadUsado.text.isEmpty()
        ) {
            Toast.makeText(
                actividad,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_LONG
            ).show()

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

        } else {
            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null, 1)
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

            val idResultante: Number = db.update(
                Utilidades.TABLA_INSUMOS,
                values,
                Utilidades.CAMPO_ID_INSUMO + "=" + insumoSeleccionado.id,
                null
            )

            if (idResultante != -1) {
                insumoPorDia(año, mes, dia)
                cancelarAct()
                Toast.makeText(
                    context,
                    "¡El insumo se Actualizó Exitosamente!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    context,
                    "El insumo no se pudo Actualizar, intente nuevamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
            db.close()

        }
    }

    private fun eliminarInsumo() {

        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_INSUMOS,
                Utilidades.CAMPO_ID_INSUMO + "=" + insumoSeleccionado.id,
                null
            )

        if (idResultante != -1) {
            Toast.makeText(context, "¡El inusmo se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            insumoPorDia(año, mes, dia)

        } else {
            Toast.makeText(context, "EL insumo no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }
        db.close()

    }


    private fun registrarInsumo() {

        if (campoFecha.text.isEmpty() or campoNombre.text.trim()
                .equals("") or campoPrecio.text.isEmpty() or campoCantidad.text.isEmpty() or campoCantidadUsado.text.isEmpty()
        ) {
            Toast.makeText(
                actividad,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_LONG
            ).show()

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

        } else {

            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null, 1)
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
            val idResultante: Number =
                db.insert(Utilidades.TABLA_INSUMOS, Utilidades.CAMPO_ID_CULTIVO, values)

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

}