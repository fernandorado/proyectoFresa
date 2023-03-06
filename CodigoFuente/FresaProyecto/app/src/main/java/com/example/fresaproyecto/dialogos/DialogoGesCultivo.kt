package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorCultivo
import com.example.fresaproyecto.adapters.OnClickListenerCultivo
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.CultivoVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class DialogoGesCultivo : DialogFragment(), OnClickListenerCultivo {

    lateinit var btnCerrar: ImageButton
    lateinit var btnExtNuevo: ExtendedFloatingActionButton
    lateinit var btnExtContinuar: ExtendedFloatingActionButton
    lateinit var interfaceComunicaFragments: IComunicaFragments

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
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
        vista = inflater.inflate(R.layout.fragment_dialogo_ges_cultivo, container, false)
        recyclerCultivos = vista.findViewById(R.id.recyclerCultivo)
        recyclerCultivos.layoutManager = GridLayoutManager(context, 2)
        recyclerCultivos.setHasFixedSize(true)
        btnCerrar = vista.findViewById(R.id.btnCerrar)
        btnExtNuevo = vista.findViewById(R.id.btnNuevo)
        btnExtContinuar = vista.findViewById(R.id.btnContinuar)
        llenarAdaptadorCultivos()
        eventosMenu()
        return vista
    }

    override fun onClick(cultivoVo: CultivoVo) {
        TODO("Not yet implemented")
    }

    private fun eventosMenu() {
        btnCerrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()

            }

        })

        btnExtNuevo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                interfaceComunicaFragments.regCultivo()

            }

        })

        btnExtContinuar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here

                Utilidades.consultarListaCultivos(actividad)
                if (Utilidades.listaCultivos!!.size > 0) {
                    val text = "Continuar"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                    interfaceComunicaFragments.menuCultivo()
                    dismiss()
                } else {
                    val text = "Registre un cultivo para continuar"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }
            }
        })
    }

    companion object {
        lateinit var cultivoSeleccionado: CultivoVo
        lateinit var recyclerCultivos: RecyclerView
        lateinit var vista: View
        lateinit var actividad: Activity

        fun llenarAdaptadorCultivos() {
            Utilidades.consultarListaCultivos(actividad)
            //se asigna la lista de jugadores por defecto
            var miAdaptadorCultivos = AdaptadorCultivo()
            miAdaptadorCultivos.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    cultivoSeleccionado =
                        Utilidades.listaCultivos!!.get(recyclerCultivos.getChildAdapterPosition(view!!))
                }
            })
            recyclerCultivos.adapter = miAdaptadorCultivos
        }
    }
}