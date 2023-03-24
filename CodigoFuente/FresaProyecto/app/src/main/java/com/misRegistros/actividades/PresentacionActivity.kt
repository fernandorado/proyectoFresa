package com.misRegistros.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.misRegistros.R
import com.misRegistros.onboarding.PresentacionAplicacionFragment

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