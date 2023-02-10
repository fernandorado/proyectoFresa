package com.example.fresaproyecto.fragments

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
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.net.URI

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InicioFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
open class InicioFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var cardRegPersonal: CardView
    lateinit var cardRegCultivo: CardView

    lateinit var imgSembrarFuturo: ImageView
    lateinit var imgMundoMujer: ImageView

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments

    private var urlSembrarFuturo : String = "https://www.facebook.com/people/Fundaci%C3%B3n-Sembrar-Futuro/100063007820541/"
    private var urlMundoMujer : String = "https://www.fmm.org.co/"


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
            InicioFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}