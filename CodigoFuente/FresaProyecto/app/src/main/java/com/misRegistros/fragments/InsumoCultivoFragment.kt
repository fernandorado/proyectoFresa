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
import com.misRegistros.adapters.AdaptadorInsumoMesCultivo
import com.misRegistros.clases.Utilidades
import com.misRegistros.dialogos.DialogoGesCultivo
import com.misRegistros.interfaces.IComunicaFragments

class InsumoCultivoFragment : Fragment() {
    lateinit var recyclerInsumoMes: RecyclerView
    lateinit var vista: View
    lateinit var actividad: Activity
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
        vista = inflater.inflate(R.layout.fragment_insumo_cultivo, container, false)
        recyclerInsumoMes = vista.findViewById(R.id.recyclerInsumo)
        recyclerInsumoMes.layoutManager = LinearLayoutManager(actividad)
        recyclerInsumoMes.setHasFixedSize(true)

        insumoPorFecha()
        return vista
    }

    private fun insumoPorFecha(){
        Utilidades.consultarInsumosMes(actividad,mes, año, idCultivo)
        var miAdaptadorInsumo = AdaptadorInsumoMesCultivo()
        recyclerInsumoMes.adapter=miAdaptadorInsumo
    }

}