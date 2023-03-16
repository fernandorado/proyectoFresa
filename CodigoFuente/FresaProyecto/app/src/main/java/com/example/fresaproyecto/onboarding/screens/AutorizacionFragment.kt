package com.example.fresaproyecto.onboarding.screens

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.fresaproyecto.MainActivity
import com.example.fresaproyecto.R

class AutorizacionFragment : Fragment() {
    lateinit var btnAceptar : Button
    lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val vista = inflater.inflate(R.layout.fragment_autorizacion, container, false)
        btnAceptar = vista.findViewById(R.id.btnAceptar)
        viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)!!
        eventosClick()
        return vista
    }

    private fun eventosClick() {
        btnAceptar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View){
                val intent = Intent(context, MainActivity::class.java)
                startActivity(intent)
                onBoardingFinished()
                activity!!.finish()
            }
        })
    }

    private fun onBoardingFinished() {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}