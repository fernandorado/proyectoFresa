package com.misRegistros.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.misRegistros.R
import com.misRegistros.onboarding.screens.AutorizacionFragment
import com.misRegistros.onboarding.screens.PresentacionFragment

class PresentacionAplicacionFragment : Fragment() {
    lateinit var vista: View
    lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_presentacion_contenedor, container, false)
        viewPager = vista.findViewById(R.id.viewPager)

        val fragmmentList = arrayListOf<Fragment>(
            PresentacionFragment(),
            AutorizacionFragment()
        )
        val adapter = PresentacionAdapter(
            fragmmentList, getChildFragmentManager(), lifecycle
        )
        viewPager.adapter = adapter
        return vista
    }

}