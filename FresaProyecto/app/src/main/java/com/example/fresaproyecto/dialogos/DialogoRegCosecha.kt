package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.DialogFragment
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.DatePickerFragment
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
    lateinit var fabRegistro: FloatingActionButton
    lateinit var fabAtras: ImageButton
    lateinit var campoCantidad: EditText
    lateinit var campoPrecio: EditText
    lateinit var campoFecha: EditText
    lateinit var calidadSpinner: Spinner
    var listaCalidad: ArrayList<String>? = ArrayList<String>()
    lateinit var adp: ArrayAdapter<*>

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

        listaCalidad!!.add("Extra")
        listaCalidad!!.add("Primera")
        listaCalidad!!.add("Segunda")
        listaCalidad!!.add("Tercera")
        listaCalidad!!.add("Cuarta")
        listaCalidad!!.add("Quinta")
        listaCalidad!!.add("Madura FF")

        adp = ArrayAdapter(actividad, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaCalidad!!)

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_dialogo_reg_cosecha, container, false)
        fabRegistro = vista.findViewById(R.id.idFabRegistro)
        campoCantidad = vista.findViewById(R.id.campoCantidadLib)
        campoPrecio = vista.findViewById(R.id.campoPrecio)
        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        campoFecha = vista.findViewById(R.id.campoFechaCosecha)
        campoFecha.setOnClickListener{ showDatePickerDialog() }

        calidadSpinner = vista.findViewById(R.id.spinnerCalidad)

        calidadSpinner.adapter=adp
        calidadSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val elemento: String = calidadSpinner.adapter.getItem(position) as String
                Toast.makeText(actividad, "Seleccionaste :\n" + elemento, Toast.LENGTH_LONG).show()

            }

        }
        eventosMenu()
        return vista
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(year, month, day)}
        datePicker.show(parentFragmentManager, "datePicker")
    }

    private fun onDateSelected (day:Int, month:Int, year:Int){
        campoFecha.setText("$day/$month/$year")
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
                registrarCosecha()
            }

        })
    }


    private fun registrarCosecha(){
        if((campoCantidad.text.toString()!=null && !campoCantidad.text.toString().trim().equals("")) and (campoPrecio.text.toString()!=null && !campoPrecio.text.toString().trim().equals(""))){
            var registro= "Nombre: "+campoCantidad.text.toString()+"\n"
            registro +="Cantidad: "+campoPrecio.text.toString()+"\n"
            print("Registrar:  "+registro)
            Toast.makeText(actividad, "REGISTRAR:\n"+registro, Toast.LENGTH_LONG).show()
            //La linea sigueinte deberia ir dentro de un IF que verifique si la consulta SQL es correcta


            dismiss()
        }else{
            if(campoCantidad.text.toString().isEmpty()){
                campoCantidad.setError("Este campo no puede quedar vacio")
            }else if (campoPrecio.text.toString().isEmpty()){
                campoPrecio.setError("Este campo no puede quedar vacio")
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