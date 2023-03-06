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
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.ByteArrayInputStream

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
    lateinit var blob: ByteArray
    lateinit var bitmap: Bitmap


    lateinit var cardRegJornal: CardView
    lateinit var cardRegCosecha: CardView
    lateinit var cardRegInsumos: CardView
    lateinit var txtNombre: TextView
    lateinit var imgCultivo: ImageView

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var navigation: BottomNavigationView

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
        blob = DialogoGesCultivo.cultivoSeleccionado.imgCultivo.inputStream().readBytes()
        val bais = ByteArrayInputStream(blob)
        bitmap = BitmapFactory.decodeStream(bais)
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_registro_cultivo, container, false)
        cardRegJornal = vista.findViewById(R.id.cardRegJornal)
        cardRegInsumos = vista.findViewById(R.id.cardRegInsumos)
        cardRegCosecha = vista.findViewById(R.id.cardRegCosecha)
        txtNombre = vista.findViewById(R.id.textCal)
        imgCultivo = vista.findViewById(R.id.imgCultivo)



        imgCultivo.setImageBitmap(bitmap)
        txtNombre.setText(DialogoGesCultivo.cultivoSeleccionado.nombre) //Nombre de mensaje de Bienvenida

        eventosMenu()

        return vista
    }

    private fun eventosMenu() {


        cardRegJornal.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                interfaceComunicaFragments.regJornal()

            }
        })

        cardRegInsumos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                interfaceComunicaFragments.regInsumos()

            }
        })

        cardRegCosecha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View) {
                interfaceComunicaFragments.regCosecha()

            }
        })

    }

}