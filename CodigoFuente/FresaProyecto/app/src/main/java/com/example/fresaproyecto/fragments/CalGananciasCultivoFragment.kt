package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorMesCultivo
import com.example.fresaproyecto.adapters.AdaptadorMesPersona
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.BeneficioCultivoVo
import com.example.fresaproyecto.clases.vo.BeneficioPersonalVo
import com.example.fresaproyecto.interfaces.IComunicaFragments

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CalGananciasCultivoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var fabAtras: ImageButton
    lateinit var vista: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            interfaceComunicaFragments = actividad as IComunicaFragments
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista=  inflater.inflate(R.layout.fragment_cal_ganancias_cultivo, container, false)
        recyclerBeneficios = vista.findViewById(R.id.recyclerCalAnteriores)
        recyclerBeneficios.layoutManager = LinearLayoutManager(actividad)
        recyclerBeneficios.setHasFixedSize(true)
        recyclerBeneficioAct = vista.findViewById(R.id.recyclerCalActual)
        recyclerBeneficioAct.layoutManager = LinearLayoutManager(actividad)
        recyclerBeneficioAct.setHasFixedSize(true)

        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        //llenarAdaptadorBeneficios()
        //llenarAdaptadorBeneficioAct()
        eventosMenu()

        return vista
    }

    private fun eventosMenu() {
        fabAtras.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                interfaceComunicaFragments.inicio()


            }

        })
    }
    /*fun llenarAdaptadorBeneficios() {

        Utilidades.calcularBeneficioCultivo(actividad)

        //se asigna la lista de jugadores por defecto
        var miAdaptadorBeneficios = AdaptadorMesCultivo(Utilidades.listaBeneficioCultivo!!)
        miAdaptadorBeneficios.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerBeneficios.getChildAdapterPosition(view!!))

                println("Mes: " + mesSeleccionado.mes)
                println("AÑo: " + mesSeleccionado.año)
            }
        })

        recyclerBeneficios.adapter=miAdaptadorBeneficios
    }

    fun llenarAdaptadorBeneficioAct() {

        Utilidades.calcularBeneficioCultivoAct(actividad)

        //se asigna la lista de jugadores por defecto
        var miAdaptadorBeneficioActual = AdaptadorMesCultivo(Utilidades.listaBeneficioCultivo!!)
        miAdaptadorBeneficioActual.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerBeneficioAct.getChildAdapterPosition(view!!))

                println("Mes: " + mesSeleccionado.mes)
                println("AÑo: " + mesSeleccionado.año)
            }
        })

        recyclerBeneficioAct.adapter=miAdaptadorBeneficioActual
    }

     */


    companion object {
        lateinit var mesSeleccionado: BeneficioCultivoVo
        lateinit var recyclerBeneficios: RecyclerView
        lateinit var recyclerBeneficioAct: RecyclerView

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CalGananciasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalGananciasCultivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}