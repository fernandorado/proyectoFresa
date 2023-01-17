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
import com.example.fresaproyecto.adapters.AdaptadorJornalMesCultivo
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [JornalCultivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JornalCultivoFragment : Fragment() {
    lateinit var recyclerJornalMes: RecyclerView
    lateinit var vista: View
    lateinit var actividad: Activity

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
        vista = inflater.inflate(R.layout.fragment_jornal_cultivo, container, false)
        recyclerJornalMes = vista.findViewById(R.id.recyclerJornal)
        recyclerJornalMes.layoutManager = LinearLayoutManager(actividad)
        recyclerJornalMes.setHasFixedSize(true)
        jornalPorFecha(1,2023)
        return vista
    }

    private fun jornalPorFecha(año : Int, mes : Int){
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.consultarJornalesMes(actividad,1, 2023)

        var miAdaptadorJornal = AdaptadorJornalMesCultivo(Utilidades.listaJornalCultivo!!)
        miAdaptadorJornal.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerInformeMes.getChildAdapterPosition(view!!))
            }
        })

        recyclerJornalMes.adapter=miAdaptadorJornal
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment JornalCultivoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            JornalCultivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}