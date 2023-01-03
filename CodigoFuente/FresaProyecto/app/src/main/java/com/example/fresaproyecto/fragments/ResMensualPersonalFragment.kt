package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorMesPersona
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.BeneficioPersonalVo
import com.example.fresaproyecto.interfaces.IComunicaFragments

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ResMensualPersonalFragment : Fragment() {
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var fabAtras: ImageButton
    lateinit var vista: View

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
        vista=  inflater.inflate(R.layout.fragment_resultado_mensual_personal, container, false)
        recyclerBeneficios = vista.findViewById(R.id.recyclerCalAnteriores)
        recyclerBeneficios.layoutManager = LinearLayoutManager(actividad)
        recyclerBeneficios.setHasFixedSize(true)
        recyclerBeneficioAct = vista.findViewById(R.id.recyclerCalActual)
        recyclerBeneficioAct.layoutManager = LinearLayoutManager(actividad)
        recyclerBeneficioAct.setHasFixedSize(true)

        fabAtras = vista.findViewById(R.id.btnIcoAtras)
        llenarAdaptadorBeneficios()
        llenarAdaptadorBeneficioAct()
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

    fun llenarAdaptadorBeneficios() {

        Utilidades.calcularBeneficioPersonal(actividad)

        //se asigna la lista de jugadores por defecto
        var miAdaptadorBeneficios = AdaptadorMesPersona(Utilidades.listaBeneficioPersonal!!)
        miAdaptadorBeneficios.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                mesSeleccionado = Utilidades.listaBeneficioPersonal!!.get(recyclerBeneficios.getChildAdapterPosition(view!!))

                println("Mes: " + mesSeleccionado.mes)
                println("AÑo: " + mesSeleccionado.año)
            }
        })

        recyclerBeneficios.adapter=miAdaptadorBeneficios
    }

    fun llenarAdaptadorBeneficioAct() {

        Utilidades.calcularBeneficioPersonalAct(actividad)

        //se asigna la lista de jugadores por defecto
        var miAdaptadorBeneficioActual = AdaptadorMesPersona(Utilidades.listaBeneficioPersonal!!)
        miAdaptadorBeneficioActual.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                mesSeleccionado = Utilidades.listaBeneficioPersonal!!.get(recyclerBeneficioAct.getChildAdapterPosition(view!!))

                println("Mes: " + mesSeleccionado.mes)
                println("AÑo: " + mesSeleccionado.año)
            }
        })

        recyclerBeneficioAct.adapter=miAdaptadorBeneficioActual
    }


    companion object {
        lateinit var mesSeleccionado: BeneficioPersonalVo
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
            ResMensualPersonalFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}