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
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.findFragment
import com.example.fresaproyecto.R
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.bottomnavigation.BottomNavigationView

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

    var fragmentRegistro: RegistroCultivoFragment = RegistroCultivoFragment()
    var fragmentInforme: InformeCultivoFragment = InformeCultivoFragment()
    lateinit var navigation: BottomNavigationView

    lateinit var vista: View
    lateinit var vista2: View
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

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_menu_cultivo, container, false)
        reemplazarFragment(fragmentRegistro)

        navigation = vista.findViewById(R.id.bottom_navigation)
        navigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.informe -> reemplazarFragment(fragmentInforme)
                R.id.registro -> reemplazarFragment(fragmentRegistro)
                else ->{

                }
            }
            true
        }

        eventosMenu()

        return vista
    }

    private fun reemplazarFragment(fragment: Fragment){
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction!!.replace(R.id.frame_container, fragment)
        fragmentTransaction.commit()
    }

    private fun eventosMenu() {


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