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
 * Use the [DialogoRegCosecha.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoRegCosecha : DialogFragment() {
    // TODO: Rename and change types of parameters

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnGuardar: Button
    lateinit var fabAtras: ImageButton
    lateinit var btnExtra: Button
    lateinit var btnPrimera: Button
    lateinit var btnSegunda: Button
    lateinit var btnTercera: Button
    lateinit var btnCuarta: Button
    lateinit var btnQuinta: Button
    lateinit var btnMadura: Button
    lateinit var layoutExtra: LinearLayout
    lateinit var layoutPrimera: LinearLayout
    lateinit var layoutSegunda: LinearLayout
    lateinit var layoutTercera: LinearLayout
    lateinit var layoutCuarta: LinearLayout
    lateinit var layoutQuinta: LinearLayout
    lateinit var layoutMadura: LinearLayout
    lateinit var campoLibrasExtra: EditText
    lateinit var campoPrecioExtra: EditText
    lateinit var campoLibrasPrimera: EditText
    lateinit var campoPrecioPrimera: EditText
    lateinit var campoLibrasSegunda: EditText
    lateinit var campoPrecioSegunda: EditText
    lateinit var campoLibrasTercera: EditText
    lateinit var campoPrecioTercera: EditText
    lateinit var campoLibrasCuarta: EditText
    lateinit var campoPrecioCuarta: EditText
    lateinit var campoLibrasQuinta: EditText
    lateinit var campoPrecioQuinta: EditText
    lateinit var campoLibrasMadura: EditText
    lateinit var campoPrecioMadura: EditText
    lateinit var campoFecha: EditText
    lateinit var campoObservacion: EditText
    var dia: Int = 0
    var mes: Int = 0
    var año: Int = 0

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



        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_dialogo_reg_cosecha, container, false)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        btnExtra = vista.findViewById(R.id.btnExtra)
        btnPrimera = vista.findViewById(R.id.btnPrimera)
        btnSegunda = vista.findViewById(R.id.btnSegunda)
        btnTercera = vista.findViewById(R.id.btnTercera)
        btnCuarta = vista.findViewById(R.id.btnCuarta)
        btnQuinta = vista.findViewById(R.id.btnQuinta)
        btnMadura = vista.findViewById(R.id.btnMadura)
        layoutExtra = vista.findViewById(R.id.layoutExtra)
        layoutPrimera = vista.findViewById(R.id.layoutPrimera)
        layoutSegunda = vista.findViewById(R.id.layoutSegunda)
        layoutTercera = vista.findViewById(R.id.layoutTercera)
        layoutCuarta = vista.findViewById(R.id.layoutCuarta)
        layoutQuinta = vista.findViewById(R.id.layoutQuinta)
        layoutMadura = vista.findViewById(R.id.layoutMadura)
        campoLibrasExtra = vista.findViewById(R.id.campoCantExtra)
        campoPrecioExtra= vista.findViewById(R.id.campoPrecioExtra)
        campoLibrasPrimera = vista.findViewById(R.id.campoCantPrimera)
        campoPrecioPrimera= vista.findViewById(R.id.campoPrecioPrimera)
        campoLibrasSegunda = vista.findViewById(R.id.campoCantSegunda)
        campoPrecioSegunda= vista.findViewById(R.id.campoPrecioSegunda)
        campoLibrasTercera = vista.findViewById(R.id.campoCantTercera)
        campoPrecioTercera= vista.findViewById(R.id.campoPrecioTercera)
        campoLibrasCuarta = vista.findViewById(R.id.campoCantCuarta)
        campoPrecioCuarta= vista.findViewById(R.id.campoPrecioCuarta)
        campoLibrasQuinta = vista.findViewById(R.id.campoCantQuinta)
        campoPrecioQuinta= vista.findViewById(R.id.campoPrecioQuinta)
        campoLibrasMadura = vista.findViewById(R.id.campoCantMaduraFF)
        campoPrecioMadura= vista.findViewById(R.id.campoPrecioMaduraFF)
        campoObservacion = vista.findViewById(R.id.campoObservacion)
        campoFecha = vista.findViewById(R.id.campoFechaCosecha)
        campoFecha.setOnClickListener{ showDatePickerDialog() }

        fabAtras = vista.findViewById(R.id.btnIcoAtras)
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
                //registrarCosecha()
            }
        })
        btnExtra.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                btnExtra.setBackgroundColor(resources.getColor(R.color.colorSplash))
                btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                layoutExtra.visibility = View.VISIBLE
                layoutPrimera.visibility = View.GONE
                layoutSegunda.visibility = View.GONE
                layoutTercera.visibility = View.GONE
                layoutCuarta.visibility = View.GONE
                layoutQuinta.visibility = View.GONE
                layoutMadura.visibility = View.GONE
            }
        })
        btnPrimera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnPrimera.setBackgroundColor(resources.getColor(R.color.colorSplash))
                btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                layoutExtra.visibility = View.GONE
                layoutPrimera.visibility = View.VISIBLE
                layoutSegunda.visibility = View.GONE
                layoutTercera.visibility = View.GONE
                layoutCuarta.visibility = View.GONE
                layoutQuinta.visibility = View.GONE
                layoutMadura.visibility = View.GONE
            }
        })
        btnSegunda.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnSegunda.setBackgroundColor(resources.getColor(R.color.colorSplash))
                btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                layoutExtra.visibility = View.GONE
                layoutPrimera.visibility = View.GONE
                layoutSegunda.visibility = View.VISIBLE
                layoutTercera.visibility = View.GONE
                layoutCuarta.visibility = View.GONE
                layoutQuinta.visibility = View.GONE
                layoutMadura.visibility = View.GONE
            }
        })
        btnTercera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnTercera.setBackgroundColor(resources.getColor(R.color.colorSplash))
                btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                layoutExtra.visibility = View.GONE
                layoutPrimera.visibility = View.GONE
                layoutSegunda.visibility = View.GONE
                layoutTercera.visibility = View.VISIBLE
                layoutCuarta.visibility = View.GONE
                layoutQuinta.visibility = View.GONE
                layoutMadura.visibility = View.GONE
            }
        })
        btnCuarta.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnCuarta.setBackgroundColor(resources.getColor(R.color.colorSplash))
                btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                layoutExtra.visibility = View.GONE
                layoutPrimera.visibility = View.GONE
                layoutSegunda.visibility = View.GONE
                layoutTercera.visibility = View.GONE
                layoutCuarta.visibility = View.VISIBLE
                layoutQuinta.visibility = View.GONE
                layoutMadura.visibility = View.GONE
            }
        })
        btnQuinta.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnQuinta.setBackgroundColor(resources.getColor(R.color.colorSplash))
                btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                layoutExtra.visibility = View.GONE
                layoutPrimera.visibility = View.GONE
                layoutSegunda.visibility = View.GONE
                layoutTercera.visibility = View.GONE
                layoutCuarta.visibility = View.GONE
                layoutQuinta.visibility = View.VISIBLE
                layoutMadura.visibility = View.GONE
            }
        })
        btnMadura.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                btnMadura.setBackgroundColor(resources.getColor(R.color.colorSplash))
                layoutExtra.visibility = View.GONE
                layoutPrimera.visibility = View.GONE
                layoutSegunda.visibility = View.GONE
                layoutTercera.visibility = View.GONE
                layoutCuarta.visibility = View.GONE
                layoutQuinta.visibility = View.GONE
                layoutMadura.visibility = View.VISIBLE
            }
        })
    }


    /*private fun registrarCosecha(){

        if((campoCantidad.text.toString()!=null && !campoCantidad.text.toString().trim().equals("")) and (campoPrecio.text.toString()!=null && !campoPrecio.text.toString().trim().equals(""))){
            var registro= "Nombre: "+campoCantidad.text.toString()+"\n"
            registro +="Cantidad: "+campoPrecio.text.toString()+"\n"
            print("Registrar:  "+registro)
            Toast.makeText(actividad, "REGISTRAR:\n"+registro, Toast.LENGTH_LONG).show()
            //La linea sigueinte deberia ir dentro de un IF que verifique si la consulta SQL es correcta

            //conexion con la base de datos
            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null,1)
            val db: SQLiteDatabase = conexion.writableDatabase
            val values = ContentValues()

            //valores para agregar a la tabla de cultivos
            //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_DIA_COSECHA, dia)
            values.put(Utilidades.CAMPO_MES_COSECHA, mes)
            values.put(Utilidades.CAMPO_AÑO_COSECHA, año)
            values.put(Utilidades.CAMPO_LIBRAS_EXTRA, campoLibrasExtra.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_EXTRA, campoPrecioExtra.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_PRIMERA, campoLibrasPrimera.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_PRIMERA, campoPrecioPrimera.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_SEGUNDA, campoLibrasSegunda.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_SEGUNDA, campoPrecioSegunda.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_TERCERA, campoLibrasTercera.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_TERCERA, campoPrecioTercera.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_CUARTA, campoLibrasCuarta.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_CUARTA, campoPrecioCuarta.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_QUINTA, campoLibrasQuinta.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_QUINTA, campoPrecioQuinta.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_MADURA, campoLibrasMadura.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_MADURA, campoPrecioMadura.text.toString())
            values.put(Utilidades.CAMPO_OBSERVACION_COSECHA, campoObservacion.text.toString())
            values.put(Utilidades.CAMPO_CULTIVO_COSECHA, DialogoGesCultivo.cultivoSeleccionado.id)
            val idResultante:Number = db.insert(Utilidades.TABLA_COSECHA, Utilidades.CAMPO_ID_CULTIVO, values)

            if(idResultante != -1){
                println("Registrar: " +registro)
                Toast.makeText(actividad, "¡Registro Éxitoso! " +registro, Toast.LENGTH_SHORT).show()
                Utilidades.calcularBeneficioCultivo(actividad)

            }else{
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT).show()
            }
            db.close()

            dismiss()
        }else{
            if(campoCantidad.text.toString().isEmpty()){
                campoCantidad.setError("Este campo no puede quedar vacio")
            }else if (campoPrecio.text.toString().isEmpty()){
                campoPrecio.setError("Este campo no puede quedar vacio")
            }
            Toast.makeText(actividad, "Verifique que todos los campos esten registrados \n ", Toast.LENGTH_LONG).show()
        }
    }*/

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DialogoRegCosecha.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoRegCosecha().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}