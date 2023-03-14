package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorMesPersona
import com.example.fresaproyecto.adapters.MyViewPagerAdapter
import com.example.fresaproyecto.adapters.MyViewPagerAdapterPersona
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.BeneficioPersonalVo
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.tabs.TabLayout

class ResMensualPersonalFragment : Fragment() {
    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var tab_layout: TabLayout
    lateinit var view_pager: ViewPager2
    lateinit var myViewPagerAdapter : MyViewPagerAdapterPersona
    lateinit var btnAtras : ImageButton
    //TextView de Informe General
    lateinit var txtIngreso : TextView
    lateinit var txtFecha : TextView
    lateinit var txtGasto : TextView
    lateinit var txtBeneficio : TextView

    var idPersona = DialogoGesPersona.personaSeleccionada.id

    var mes = InformePersonalFragment.fecha.mes
    var año = InformePersonalFragment.fecha.año

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            InformePersonalFragment.interfaceComunicaFragments = actividad as IComunicaFragments
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_resultado_mensual_personal, container, false)

        btnAtras = vista.findViewById(R.id.btnIcoAtras)
        txtIngreso = vista.findViewById(R.id.txtValorIngreso)
        txtFecha = vista.findViewById(R.id.txtFechaInf)
        txtGasto = vista.findViewById(R.id.txtValorGasto)
        txtBeneficio = vista.findViewById(R.id.txtBeneficio)

        view_pager = vista.findViewById(R.id.view_pager)
        tab_layout = vista.findViewById(R.id.tab_layout)
        myViewPagerAdapter = MyViewPagerAdapterPersona(this)
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
        Utilidades.calcularBeneficioPersonal(actividad, año, idPersona)
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
        txtBeneficio.setText(Utilidades.listaBeneficioPersonal!![mes].beneficio.toString())
        txtIngreso.setText(Utilidades.listaBeneficioPersonal!![mes].ingresos.toString())
        txtGasto.setText(Utilidades.listaBeneficioPersonal!![mes].gastos.toString())

        var miAdaptadorInforme = AdaptadorMesPersona()
        miAdaptadorInforme.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //mesSeleccionado = Utilidades.listaBeneficioPersonal!!.get(recyclerInformeMes.getChildAdapterPosition(view!!))
            }
        })

        InformePersonalFragment.recyclerInformeMes.adapter=miAdaptadorInforme
    }


    companion object {
        lateinit var mesSeleccionado: BeneficioPersonalVo
    }
}