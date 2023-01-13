package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.bottomnavigation.BottomNavigationView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegistroCultivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegistroCultivoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cardRegJornal: CardView
    lateinit var cardRegCosecha: CardView
    lateinit var cardRegInsumos: CardView
    lateinit var cardGanancias: CardView
    lateinit var txtNombre: TextView

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var navigation: BottomNavigationView

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
        vista = inflater.inflate(R.layout.fragment_registro_cultivo, container, false)
        cardRegJornal=vista.findViewById(R.id.cardRegJornal)
        cardRegInsumos=vista.findViewById(R.id.cardRegInsumos)
        cardRegCosecha=vista.findViewById(R.id.cardRegCosecha)
        txtNombre = vista.findViewById(R.id.textCal)


        txtNombre.setText(DialogoGesCultivo.cultivoSeleccionado.nombre) //Nombre de mensaje de Bienvenida

        eventosMenu()

        return vista
    }

    private fun eventosMenu() {



        cardRegJornal.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.regJornal()

            }
        })

        cardRegInsumos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.regInsumos()

            }
        })

        cardRegCosecha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.regCosecha()

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
         * @return A new instance of fragment RegistroCultivoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistroCultivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}