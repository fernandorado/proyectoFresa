package com.misRegistros.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.misRegistros.R
import com.misRegistros.dialogos.DialogoGesPersona
import com.misRegistros.interfaces.IComunicaFragments

class RegistroPersonaFragment : Fragment() {
    lateinit var cardRegGastos: CardView
    lateinit var cardRegIngresos: CardView
    lateinit var txtNombre: TextView

    lateinit var btnAtras : ImageButton
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vista = inflater.inflate(R.layout.fragment_menu_registro_persona, container, false)
        btnAtras = vista.findViewById(R.id.btnIcoAtras)
        cardRegIngresos = vista.findViewById(R.id.cardRegIngresos)
        cardRegGastos = vista.findViewById(R.id.cardRegGastos)
        txtNombre = vista.findViewById(R.id.textNombre)

        txtNombre.setText(DialogoGesPersona.personaSeleccionada.nombre) //Nombre de mensaje de Bienvenida

        eventosMenu()

        return vista
    }

    private fun eventosMenu() {


        cardRegIngresos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                interfaceComunicaFragments.regIngresos()

            }
        })

        cardRegGastos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                interfaceComunicaFragments.regGastos()

            }
        })

        btnAtras.setOnClickListener { requireActivity().onBackPressed() }

    }
}