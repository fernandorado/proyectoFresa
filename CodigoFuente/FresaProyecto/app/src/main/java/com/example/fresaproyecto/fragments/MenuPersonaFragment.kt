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
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.interfaces.IComunicaFragments

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuPersonaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuPersonaFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cardRegGastos: CardView
    lateinit var cardRegIngresos: CardView
    lateinit var cardResultadoMensual: CardView
    lateinit var txtNombre: TextView

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments

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
        vista = inflater.inflate(R.layout.fragment_menu_persona, container, false)
        cardRegIngresos=vista.findViewById(R.id.cardRegIngresos)
        cardRegGastos=vista.findViewById(R.id.cardRegGastos)
        cardResultadoMensual=vista.findViewById(R.id.cardResultadoMensual)
        txtNombre = vista.findViewById(R.id.textNombre)

        txtNombre.setText(DialogoGesPersona.personaSeleccionada.nombre) //Nombre de mensaje de Bienvenida

        eventosMenu()

        return vista
    }

    private fun eventosMenu() {



        cardRegIngresos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.regIngresos()

            }
        })

        cardRegGastos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.regGastos()

            }
        })

        cardResultadoMensual.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.resultadoMensual()

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
         * @return A new instance of fragment MenuPersonaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuPersonaFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}