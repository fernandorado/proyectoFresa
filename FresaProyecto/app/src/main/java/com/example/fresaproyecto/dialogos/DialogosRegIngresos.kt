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
import com.example.fresaproyecto.interfaces.IComunicaFragments

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


    lateinit var campoConcepto: EditText
    lateinit var campoMonto: EditText
    lateinit var btnRegistrar: Button
    lateinit var btnCancelar: Button
    lateinit var ingresosSpinner: Spinner
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
        listaIngresos!!.add("Otras Ventas")


        adp = ArrayAdapter(actividad, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, listaIngresos!!)
        vista = inflater.inflate(R.layout.fragment_dialogos_reg_ingresos, container,false)


        campoMonto = vista.findViewById(R.id.campoMonto)
        btnRegistrar = vista.findViewById(R.id.btnRegistrar)
        btnCancelar = vista.findViewById(R.id.btnCancelar)
        ingresosSpinner = vista.findViewById(R.id.spinnerIngresos)

        ingresosSpinner.adapter=adp
        ingresosSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {


            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val elemento: String = ingresosSpinner.adapter.getItem(position) as String
                Toast.makeText(actividad, "Seleccionaste :\n" + elemento, Toast.LENGTH_LONG).show()

            }

        }

        eventosMenu()
        // Inflate the layout for this fragment
        return vista
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


        if((campoConcepto.text.toString()!=null && !campoConcepto.text.toString().trim().equals("")) and (campoMonto.text.toString()!=null && !campoMonto.text.toString().trim().equals(""))) {

            var id = DialogoGesPersona.identificacion
            var registro = "Concepto: " + campoConcepto.text.toString() + "\n"
            registro += "Nombre: " + campoMonto.text.toString() + "\n"
            registro += "ID: " + id + "\n"
            print("Registrar:  " + registro)
            Toast.makeText(actividad, "REGISTRAR:\n" + registro, Toast.LENGTH_LONG).show()




            dismiss()
        }else{
            if(campoConcepto.text.toString().isEmpty()){
                campoConcepto.setError("Este campo no puede quedar vacio")
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