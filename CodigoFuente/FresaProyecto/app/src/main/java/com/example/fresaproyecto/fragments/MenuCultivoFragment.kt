package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.addCallback
import androidx.cardview.widget.CardView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.interfaces.IComunicaFragments

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class MenuCultivoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cardMenuGastos: CardView
    lateinit var cardMenuIngresos: CardView
    lateinit var cardRegInsumos: CardView
    lateinit var cardGanancias: CardView
    lateinit var txtNombre: TextView

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        /*val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            interfaceComunicaFragments.inicio()
        }*/
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
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_menu_cultivo, container, false)
        cardMenuGastos=vista.findViewById(R.id.cardMenuGastos)
        cardMenuIngresos=vista.findViewById(R.id.cardMenuIngresos)
        cardGanancias=vista.findViewById(R.id.cardGanancias)
        txtNombre = vista.findViewById(R.id.textNombreCultivo)

        txtNombre.setText(DialogoGesCultivo.cultivoSeleccionado.nombre) //Nombre de mensaje de Bienvenida

        eventosMenu()

        return vista
    }

    private fun eventosMenu() {



        cardMenuGastos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.menuGastos()

            }
        })

        cardMenuIngresos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.menuIngresos()

            }
        })

        cardGanancias.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.calGanancias()

            }
        })
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InicioFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuCultivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}