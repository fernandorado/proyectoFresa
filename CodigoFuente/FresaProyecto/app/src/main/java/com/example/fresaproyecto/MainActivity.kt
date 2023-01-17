package com.example.fresaproyecto

import android.content.DialogInterface
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.dialogos.*
import com.example.fresaproyecto.fragments.*
import com.example.fresaproyecto.interfaces.IComunicaFragments

class MainActivity : AppCompatActivity(), IComunicaFragments{
    //val fragmentManager = supportFragmentManager
    //val fragmentTransaction = fragmentManager.beginTransaction()


/*
    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setMessage("¿Salir de la aplicación?")
            .setCancelable(false)
            .setPositiveButton("Si") { dialog, whichButton ->
                finishAffinity() //Sale de la aplicación.
            }
            .setNegativeButton("Cancelar") { dialog, whichButton ->

            }
            .show()
    }*/

    /*override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("¿Desea salir de Stroopers?")
                .setPositiveButton("Si") { dialog, id ->
                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
                .setNegativeButton(
                    "Cancelar"
                ) { dialog, id -> dialog.dismiss() }
            builder.show()
        }
        return super.onKeyDown(keyCode, event)
    }*/








    override fun onCreate(savedInstanceState: Bundle?) {

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        val fragmentInicio =  InicioFragment();

        Utilidades.consultarListaPersonas(this)

        val conexion = ConexionSQLiteHelper(this, Utilidades.NOMBRE_BD, null,1)





        fragmentTransaction.replace(R.id.contenedorFragments, fragmentInicio).commit()


    }

    //Alerta de Dialogo para Registrar o Seleccionar un cultivo
    /*fun createSimpleDialog(): AlertDialog? {
        val builder = AlertDialog.Builder(this@MainActivity)
        val fragmentMenuCultivo = MenuCultivoFragment()
        val fragmentRegCultivo = DialogoRegCultivo()
        builder.setTitle("GESTIONAR CULTIVO")
            .setMessage(
                """
            Indique Si desea registrar un nuevo cultivo o si desea seleccionar uno ya existente.
            
            También podrá modificar un Cultivo desde la opción SELECCIONAR
            """.trimIndent()
            )
            .setNegativeButton("REGISTRAR",
                DialogInterface.OnClickListener { dialog, which ->



                    regCultivo()
                })
            .setPositiveButton("SELECCIONAR",
                DialogInterface.OnClickListener { dialog, which ->


                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.contenedorFragments, fragmentMenuCultivo)
                    transaction.addToBackStack(null)
                    transaction.commit()
                })
        return builder.create()
    }

    fun dialogoGestionUsuarios(): AlertDialog? {
        val builder = AlertDialog.Builder(this@MainActivity)
        val fragmentMenuCultivo = MenuCultivoFragment()
        val fragmentRegCultivo = DialogoRegCultivo()
        builder.setTitle("GESTIONAR CULTIVO")
            .setMessage(
                """
            Indique Si desea registrar un nuevo usuario o si desea seleccionar uno ya existente.
            
            También podrá modificar un Usuario desde la opción SELECCIONAR
            """.trimIndent()
            )
            .setNegativeButton("REGISTRAR",
                DialogInterface.OnClickListener { dialog, which ->



                    val dialogRegistro = DialogoRegPersonas()
                    dialogRegistro.show(supportFragmentManager, "DialogoRegPersonas")


                })
            .setPositiveButton("SELECCIONAR",
                DialogInterface.OnClickListener { dialog, which ->

                    Utilidades.consultarListaPersonas(this)

                    val dialogSeleccion = DialogoGesPersona()
                    dialogSeleccion.show(supportFragmentManager, "DialogoGesPersona")

                })
        return builder.create()
    }*/

    override fun menuCultivo() {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()

        val fragmentMenuCultivo= MenuCultivoFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentMenuCultivo)
        transaction.addToBackStack(null)
        transaction.commit()

        val text ="Mi Cultivo"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun menuGastos() {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()

        val fragmentMenuCultivoGastos= MenuCultivoGastosFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentMenuCultivoGastos)
        transaction.addToBackStack(null)
        transaction.commit()

        val text ="Gastos"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }


    override fun menuIngresos() {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()

        val fragmentMenuCultivoIngresos= MenuCultivoIngresosFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentMenuCultivoIngresos)
        transaction.addToBackStack(null)
        transaction.commit()

        val text ="Ingresos"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun menuPersonal() {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()

        val fragmentMenuPersonaFragment= MenuPersonaFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentMenuPersonaFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        val text ="Registro Personal"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun regCultivo() {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()


        val dialog = DialogoRegCultivo()
        dialog.show(supportFragmentManager, "DialogoRegGastos")
    }

    override fun regPersona() {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()


        val dialog = DialogoRegPersonas()
        dialog.show(supportFragmentManager, "DialogoRegPersonas")
    }

    override fun regGastos() {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()

        val dialog = DialogoRegGastos()
        dialog.show(supportFragmentManager, "DialogoRegGastos")

    }

    override fun regIngresos() {
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()

        val dialog = DialogosRegIngresos()
        dialog.show(supportFragmentManager, "DialogoRegIngresos")
    }

    override fun resultadoMensual() {

        val fragmentResultadoMensual = ResMensualPersonalFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentResultadoMensual)
        transaction.addToBackStack(null)
        transaction.commit()

        val text ="Resultado Mensual"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun resultadoMensualCultivo() {

        val fragmentResMensualCultivo = DetalleGanCultivoFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentResMensualCultivo)
        transaction.addToBackStack(null)
        transaction.commit()

        val text ="Resultado Mensual"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun regJornal() {
        val dialog = DialogoRegJornal()
        dialog.show(supportFragmentManager, "DialogoRegJornal")


    }

    override fun regCosecha() {
        val dialog = DialogoRegCosecha()
        dialog.show(supportFragmentManager, "DialogoRegCosecha")

        val text ="Registro de Cosecha"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun regInsumos() {
        val dialog = DialogoRegInsumos()
        dialog.show(supportFragmentManager, "DialogoRegInsumos")

        val text ="Registro de Insumos"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }



    override fun calGanancias() {
        val fragmentCalcularGanancias = CalGananciasCultivoFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentCalcularGanancias)
        transaction.addToBackStack(null)
        transaction.commit()

        val text ="Calcular Ganancias"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    override fun inicio() {
        val fragmentInicioFragment = InicioFragment()

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.contenedorFragments, fragmentInicioFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        val text ="Inicio"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
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