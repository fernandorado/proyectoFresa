package com.misRegistros.actividades

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.misRegistros.MainActivity
import com.misRegistros.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            if (onBoardingFinished()) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                finish()
                startActivity(intent)
            } else {
                val intent = Intent(this@SplashActivity, PresentacionActivity::class.java)
                finish()
                startActivity(intent)
            }
        }, 3000)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }
}