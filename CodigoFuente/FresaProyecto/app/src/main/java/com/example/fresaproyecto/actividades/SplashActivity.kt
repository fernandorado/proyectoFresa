package com.example.fresaproyecto.actividades

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.fragment.findNavController
import com.example.fresaproyecto.MainActivity
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.fragments.InicioFragment
import com.example.fresaproyecto.fragments.MenuPersonaFragment
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.example.fresaproyecto.onboarding.PresentacionAplicacionFragment
import com.example.fresaproyecto.onboarding.screens.PresentacionFragment

class SplashActivity : AppCompatActivity() {
    lateinit var interfaceComunicaFragments: IComunicaFragments

    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var interfaceComunicaFragments: IComunicaFragments
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        val fragmentInicio = PresentacionAplicacionFragment();

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            if (onBoardingFinished()) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, PresentacionActivity::class.java)
                startActivity(intent)
            }
        }, 3000)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}