package com.misRegistros.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.misRegistros.R
import com.misRegistros.interfaces.IComunicaFragments

open class InicioFragment : Fragment() {

    lateinit var cardRegPersonal: CardView
    lateinit var cardRegCultivo: CardView

    lateinit var imgSembrarFuturo: ImageView
    lateinit var imgMundoMujer: ImageView

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments

    private var urlSembrarFuturo : String = "https://www.facebook.com/profile.php?id=100063007820541"
    private var urlMundoMujer : String = "https://www.fmm.org.co/"

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
        vista = inflater.inflate(R.layout.fragment_inicio, container, false)
        cardRegCultivo=vista.findViewById(R.id.cardRegCultivo)
        cardRegPersonal=vista.findViewById(R.id.cardRegPersonal)

        imgSembrarFuturo= vista.findViewById(R.id.imgSembrarFuturo)
        imgMundoMujer= vista.findViewById(R.id.imgMundoMujer)

        eventosMenu()

        return vista
    }



    private fun eventosMenu() {


        cardRegCultivo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.gestionarCultivo()
            }
        })


        cardRegPersonal.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                interfaceComunicaFragments.gestionarPersona()

            }
        })

        imgSembrarFuturo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                var link :Uri = Uri.parse(urlSembrarFuturo)
                var i: Intent = Intent(Intent.ACTION_VIEW, link)
                startActivity(i)
            }
        })

        imgMundoMujer.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                var link :Uri = Uri.parse(urlMundoMujer)
                var i: Intent = Intent(Intent.ACTION_VIEW, link)
                startActivity(i)
            }
        })
    }
}