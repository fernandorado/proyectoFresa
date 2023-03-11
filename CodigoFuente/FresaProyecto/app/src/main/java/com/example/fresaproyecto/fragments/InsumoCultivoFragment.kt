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
import com.example.fresaproyecto.adapters.AdaptadorInsumoMesCultivo
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.interfaces.IComunicaFragments

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
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.consultarInsumosMes(actividad,mes, año, idCultivo)

        var miAdaptadorInsumo = AdaptadorInsumoMesCultivo()
        miAdaptadorInsumo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerInformeMes.getChildAdapterPosition(view!!))
            }
        })

        recyclerInsumoMes.adapter=miAdaptadorInsumo
    }

}