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
import com.google.android.material.bottomnavigation.BottomNavigationView

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
    var fragmentRegistro: RegistroPersonaFragment = RegistroPersonaFragment()
    var fragmentInforme: InformePersonalFragment = InformePersonalFragment()
    lateinit var navigation: BottomNavigationView

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

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_menu_cultivo, container, false)
        reemplazarFragment(fragmentRegistro)

        navigation = vista.findViewById(R.id.bottom_navigation)
        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.informe -> reemplazarFragment(fragmentInforme)
                R.id.registro -> reemplazarFragment(fragmentRegistro)
                else -> {

                }
            }
            true
        }

        return vista
    }

    private fun reemplazarFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction!!.replace(R.id.frame_container, fragment)
        fragmentTransaction.commit()
    }
}