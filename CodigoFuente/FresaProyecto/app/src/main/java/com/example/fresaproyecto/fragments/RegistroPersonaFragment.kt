package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.ByteArrayInputStream

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
        vista = inflater.inflate(R.layout.fragment_menu_persona, container, false)
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