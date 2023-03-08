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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InsumoCultivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InsumoCultivoFragment : Fragment() {
    lateinit var recyclerInsumoMes: RecyclerView
    lateinit var vista: View
    lateinit var actividad: Activity
    var idCultivo = DialogoGesCultivo.cultivoSeleccionado.id
    var mes = InformeCultivoFragment.fecha.mes
    var año = InformeCultivoFragment.fecha.año



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InsumoCultivoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InsumoCultivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}