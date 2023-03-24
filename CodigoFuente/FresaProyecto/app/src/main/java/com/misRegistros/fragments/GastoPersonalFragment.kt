package com.misRegistros.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misRegistros.R
import com.misRegistros.adapters.AdaptadorGastoMesPersona
import com.misRegistros.clases.Utilidades
import com.misRegistros.dialogos.DialogoGesPersona
import com.misRegistros.interfaces.IComunicaFragments

class GastoPersonalFragment : Fragment() {
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
        gastoPorMes()
        return vista
    }

    private fun gastoPorMes(){
        Utilidades.consultarListaGastoPersonal(actividad,mes, año, idPersona)

        var miAdaptadorGastos = AdaptadorGastoMesPersona()

        recyclerJornalMes.adapter=miAdaptadorGastos
    }
    
}