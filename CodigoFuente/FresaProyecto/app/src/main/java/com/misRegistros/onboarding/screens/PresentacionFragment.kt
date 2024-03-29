package com.misRegistros.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.viewpager2.widget.ViewPager2
import com.misRegistros.R
class PresentacionFragment : Fragment() {
    lateinit var btnSiguiente : Button
    lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_presentacion_permisos, container, false)
        btnSiguiente = vista.findViewById(R.id.btnSiguiente)
        viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)!!
        eventosClick()
        return vista
    }

    private fun eventosClick() {
        btnSiguiente.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                viewPager.currentItem = 1
            }
        })
    }
}