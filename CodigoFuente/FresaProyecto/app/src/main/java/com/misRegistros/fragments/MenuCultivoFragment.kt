package com.misRegistros.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.misRegistros.R
import com.misRegistros.interfaces.IComunicaFragments
import com.google.android.material.bottomnavigation.BottomNavigationView

open class MenuCultivoFragment : Fragment() {

    var fragmentRegistro: RegistroCultivoFragment = RegistroCultivoFragment()
    var fragmentInforme: InformeCultivoFragment = InformeCultivoFragment()
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
        vista = inflater.inflate(R.layout.fragment_menu_navigation, container, false)

        navigation = vista.findViewById(R.id.bottom_navigation)
        navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.informe -> reemplazarFragment(fragmentInforme)
                R.id.registro -> reemplazarFragment(fragmentRegistro)

            }
            true
        }
        navigation.selectedItemId = R.id.registro


        this.onPause()
        return vista
    }

    private fun reemplazarFragment(fragment: Fragment) {
        val fragmentManager = activity?.supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction!!.replace(R.id.frame_container, fragment)
        fragmentTransaction.commit()
    }
}