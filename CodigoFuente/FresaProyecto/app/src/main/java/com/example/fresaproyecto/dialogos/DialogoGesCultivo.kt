package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorCultivo
import com.example.fresaproyecto.adapters.OnClickListenerCultivo
import com.example.fresaproyecto.adapters.OnClickListenerPersona
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.CultivoVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogoGesCultivo.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoGesCultivo : DialogFragment(), OnClickListenerCultivo {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var btnCerrar: ImageButton
    lateinit var btnExtNuevo: ExtendedFloatingActionButton
    lateinit var btnExtContinuar: ExtendedFloatingActionButton




    lateinit var interfaceComunicaFragments: IComunicaFragments




    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)



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
        vista = inflater.inflate(R.layout.fragment_dialogo_ges_cultivo, container,false)

        recyclerCultivos = vista.findViewById(R.id.recyclerCultivo)
        //recyclerCultivos.layoutManager = LinearLayoutManager(actividad)
        recyclerCultivos.layoutManager = GridLayoutManager(context, 2)
        recyclerCultivos.setHasFixedSize(true)


        btnCerrar = vista.findViewById(R.id.btnCerrar)
        btnExtNuevo = vista.findViewById(R.id.btnNuevo)
        btnExtContinuar = vista.findViewById(R.id.btnContinuar)

        //btnFlotanteActualziar.visibility = View.GONE
        //btnFlotanteEliminar.visibility = View.GONE
        //btnExtEditar.shrink()  Para cerrar el boton Extend
        //btnExtEditar.extend()  Para abrir el boton, extenderlo

        llenarAdaptadorCultivos()

        eventosMenu()
        // Inflate the layout for this fragment
        return vista
    }

    override fun onClick(cultivoVo: CultivoVo) {
        TODO("Not yet implemented")
    }

    private fun eventosMenu(){


        btnCerrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()



            }

        })

        btnExtNuevo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                /*if(btnFlotanteActualziar.isVisible and btnFlotanteEliminar.isVisible){
                    btnFlotanteActualziar.visibility = View.GONE
                    btnFlotanteEliminar.visibility = View.GONE
                }else{
                    btnFlotanteActualziar.visibility = View.VISIBLE
                    btnFlotanteEliminar.visibility = View.VISIBLE
                }*/
                interfaceComunicaFragments.regCultivo()

            }

        })



        btnExtContinuar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                val text ="Continuar"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
                interfaceComunicaFragments.menuCultivo()
                dismiss()



            }

        })
    }

    companion object {
        lateinit var cultivoSeleccionado: CultivoVo
        lateinit var identificacion: String
        lateinit var recyclerCultivos: RecyclerView
        lateinit var vista: View
        lateinit var actividad: Activity






        fun llenarAdaptadorCultivos() {

            Utilidades.consultarListaCultivos(actividad)

            //se asigna la lista de jugadores por defecto
            var miAdaptadorCultivos = AdaptadorCultivo(Utilidades.listaCultivos!!)
            miAdaptadorCultivos.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    cultivoSeleccionado= Utilidades.listaCultivos!!.get(recyclerCultivos.getChildAdapterPosition(view!!))

                    println("Nombre: " + cultivoSeleccionado.nombre)
                }
            })

            recyclerCultivos.adapter=miAdaptadorCultivos
        }
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DialogoGesCultivo.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoGesCultivo().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}