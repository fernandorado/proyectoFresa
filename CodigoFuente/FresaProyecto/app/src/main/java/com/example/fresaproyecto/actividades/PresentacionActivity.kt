package com.example.fresaproyecto.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.fragments.InicioFragment
import com.example.fresaproyecto.onboarding.PresentacionAplicacionFragment

class PresentacionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentacion)
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        val fragmentInicio =  PresentacionAplicacionFragment();

        SplashActivity().finish()
        fragmentTransaction.replace(R.id.contenedorFragmentsPre, fragmentInicio).commit()
    }
}