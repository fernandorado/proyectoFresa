package com.misRegistros.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.misRegistros.R
import com.misRegistros.adapters.*
import com.misRegistros.clases.Utilidades
import com.misRegistros.dialogos.DialogoGesCultivo
import com.misRegistros.interfaces.IComunicaFragments
import com.google.android.material.tabs.TabLayout

class DetalleGanCultivoFragment : Fragment() {
    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var tab_layout: TabLayout
    lateinit var view_pager: ViewPager2
    lateinit var myViewPagerAdapter : MyViewPagerAdapter
    //TextView de Informe General
    lateinit var txtIngreso : TextView
    lateinit var txtFecha : TextView
    lateinit var txtFechaSelec : TextView
    lateinit var txtGasto : TextView
    lateinit var txtBeneficio : TextView
    lateinit var btnAtras : ImageButton

    var idCultivo = DialogoGesCultivo.cultivoSeleccionado.id

    var mes = InformeCultivoFragment.fecha.mes
    var año = InformeCultivoFragment.fecha.año


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            InformeCultivoFragment.interfaceComunicaFragments = actividad as IComunicaFragments
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_resultado_mensual_cultivo, container, false)

        txtIngreso = vista.findViewById(R.id.txtValorIngreso)
        txtFecha = vista.findViewById(R.id.txtFechaInf)
        txtGasto = vista.findViewById(R.id.txtValorGasto)
        txtBeneficio = vista.findViewById(R.id.txtBeneficio)

        btnAtras = vista.findViewById(R.id.btnIcoAtras)
        view_pager = vista.findViewById(R.id.view_pager)
        tab_layout = vista.findViewById(R.id.tab_layout)
        myViewPagerAdapter = MyViewPagerAdapter(this)
        view_pager.setAdapter(myViewPagerAdapter)
        informePorFecha(año)


        tab_layout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                view_pager.setCurrentItem(tab!!.position)
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
        })

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tab_layout.getTabAt(position)?.select()
            }
        })
        eventosClick()
        return vista
    }

    fun eventosClick(){
        btnAtras.setOnClickListener { requireActivity().onBackPressed() }
    }

    private fun informePorFecha(año:Int){
        Utilidades.calcularBeneficioCultivo(actividad, año, idCultivo)
        mes = mes-1

        var mesLetras = when (mes){
            0 -> "Enero"
            1 -> "Febrero"
            2 -> "Marzo"
            3 -> "Abril"
            4 -> "Mayo"
            5 -> "Junio"
            6 -> "Julio"
            7 -> "Agosto"
            8 -> "Septiembre"
            9 -> "Octubre"
            10 -> "Noviembre"
            11 -> "Diciembre"
            else -> "Null"
        }

        txtFecha.setText(""+mesLetras+"."+año)
        txtBeneficio.setText(Utilidades.listaBeneficioCultivo!![mes].beneficio.toString())
        txtIngreso.setText(Utilidades.listaBeneficioCultivo!![mes].ingresos.toString())
        txtGasto.setText(Utilidades.listaBeneficioCultivo!![mes].gastos.toString())

        var miAdaptadorInforme = AdaptadorMesCultivo()

        InformeCultivoFragment.recyclerInformeMes.adapter=miAdaptadorInforme
    }

    companion object {
        lateinit var interfaceComunicaFragments: IComunicaFragments
    }
}