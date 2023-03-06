package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorGastoMesPersona
import com.example.fresaproyecto.adapters.AdaptadorIngresoMesPersona
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.interfaces.IComunicaFragments

class IngresoPersonalFragment : Fragment() {
    lateinit var recyclerJornalMes: RecyclerView
    lateinit var vista: View
    lateinit var actividad: Activity
    var idPersona = DialogoGesPersona.personaSeleccionada.id

    var mes = InformePersonalFragment.fecha.mes
    var año = InformePersonalFragment.fecha.año

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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
        vista = inflater.inflate(R.layout.fragment_gasto_personal, container, false)
        recyclerJornalMes = vista.findViewById(R.id.recyclerGastos)
        recyclerJornalMes.layoutManager = LinearLayoutManager(actividad)
        recyclerJornalMes.setHasFixedSize(true)
        jornalPorFecha()
        return vista
    }

    private fun jornalPorFecha(){
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.consultarListaIngresoPersonal(actividad,mes, año, idPersona)

        var miAdaptadorIngresos = AdaptadorIngresoMesPersona()
        miAdaptadorIngresos.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerInformeMes.getChildAdapterPosition(view!!))
            }
        })

        recyclerJornalMes.adapter=miAdaptadorIngresos
    }
    
}