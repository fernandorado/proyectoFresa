package com.misRegistros

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.misRegistros.dialogos.*
import com.misRegistros.fragments.*
import com.misRegistros.interfaces.IComunicaFragments
import com.misRegistros.onboarding.PresentacionAplicacionFragment

class MainActivity : AppCompatActivity(), IComunicaFragments{
        override fun onCreate(savedInstanceState: Bundle?) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentInicio =  InicioFragment()

        fragmentTransaction.replace(R.id.contenedorFragments, fragmentInicio).commit()
    }

    override fun presentacionApp() {
        val presentacionFragment= PresentacionAplicacionFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, presentacionFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun menuCultivo() {
        val fragmentMenuCultivo= MenuCultivoFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentMenuCultivo)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    override fun menuPersonal() {
        val fragmentMenuPersonaFragment= MenuPersonaFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentMenuPersonaFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun regCultivo() {
        val dialog = DialogoRegCultivo()
        dialog.show(supportFragmentManager, "DialogoRegGastos")
    }

    override fun regPersona() {
        val dialog = DialogoRegPersonas()
        dialog.show(supportFragmentManager, "DialogoRegPersonas")
    }

    override fun regGastos() {
        val dialog = DialogoRegGastos()
        dialog.show(supportFragmentManager, "DialogoRegGastos")

    }

    override fun regIngresos() {
        val dialog = DialogosRegIngresos()
        dialog.show(supportFragmentManager, "DialogoRegIngresos")
    }

    override fun resultadoMensual() {

        val fragmentResultadoMensual = ResMensualPersonalFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentResultadoMensual)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun resultadoMensualCultivo() {

        val fragmentResMensualCultivo = DetalleGanCultivoFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentResMensualCultivo)
        transaction.addToBackStack(null)
        transaction.commit()

    }

    override fun regJornal() {
        val dialog = DialogoRegJornal()
        dialog.show(supportFragmentManager, "DialogoRegJornal")


    }

    override fun regCosecha() {
        val dialog = DialogoRegCosecha()
        dialog.show(supportFragmentManager, "DialogoRegCosecha")
    }

    override fun regInsumos() {
        val dialog = DialogoRegInsumos()
        dialog.show(supportFragmentManager, "DialogoRegInsumos")
    }

    override fun inicio() {
        val fragmentInicioFragment = InicioFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentInicioFragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun gestionarCultivo(){
        val dialogSeleccion = DialogoGesCultivo()
        dialogSeleccion.show(supportFragmentManager, "DialogoGesCultivo")
    }

    override fun gestionarPersona(){
        val dialogSeleccion = DialogoGesPersona()
        dialogSeleccion.show(supportFragmentManager, "DialogoGesPersona")
    }


    override fun actPersona(){
        val dialog = DialogoActPersona()
        dialog.show(supportFragmentManager, "DialogoActPersona")
    }

    override fun actCultivo(){
        val dialog = DialogoActCultivo()
        dialog.show(supportFragmentManager, "DialogoActCultivo")
    }


}