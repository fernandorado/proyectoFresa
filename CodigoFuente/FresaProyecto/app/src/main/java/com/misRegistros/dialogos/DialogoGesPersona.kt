package com.misRegistros.dialogos

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.misRegistros.R
import com.misRegistros.adapters.AdaptadorPersona
import com.misRegistros.adapters.OnClickListenerPersona
import com.misRegistros.clases.Utilidades
import com.misRegistros.clases.vo.PersonaVo
import com.misRegistros.interfaces.IComunicaFragments
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class DialogoGesPersona : DialogFragment(), OnClickListenerPersona {

    lateinit var btnCerrar: ImageButton
    lateinit var btnExtNuevo: ExtendedFloatingActionButton
    lateinit var btnExtContinuar: ExtendedFloatingActionButton

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.window!!.setGravity(Gravity.TOP)
        return dialog
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
        vista = inflater.inflate(R.layout.fragment_dialogo_ges_persona, container,false)

        recyclerUsuarios = vista.findViewById(R.id.recyclerUsuario)
        recyclerUsuarios.layoutManager = GridLayoutManager(context, 2)
        recyclerUsuarios.setHasFixedSize(true)







        btnCerrar = vista.findViewById(R.id.btnCerrar)
        btnExtNuevo = vista.findViewById(R.id.btnNuevo)
        btnExtContinuar = vista.findViewById(R.id.btnContinuar)

        llenarAdaptadorUsuarios()

        eventosMenu()
        // Inflate the layout for this fragment
        return vista
    }

    override fun onClick(personaVo: PersonaVo) {
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
                interfaceComunicaFragments.regPersona()

            }

        })



        btnExtContinuar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                Utilidades.consultarListaPersonas(actividad)
                if (Utilidades.listaPersonas!!.size > 0) {
                    interfaceComunicaFragments.menuPersonal()
                    dismiss()
                } else {
                    val text = "Haga un registro para continuar"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(context, text, duration)
                    toast.show()
                }


            }

        })
    }

    companion object {
        lateinit var personaSeleccionada: PersonaVo
        lateinit var identificacion: String
        lateinit var recyclerUsuarios: RecyclerView
        lateinit var vista: View
        lateinit var actividad: Activity
        lateinit var interfaceComunicaFragments: IComunicaFragments


        fun llenarAdaptadorUsuarios() {
            Utilidades.consultarListaPersonas(actividad)
            var miAdaptadorPersona = AdaptadorPersona()

            miAdaptadorPersona.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    personaSeleccionada= Utilidades.listaPersonas!!.get(recyclerUsuarios.getChildAdapterPosition(view!!))
                }
            })

            recyclerUsuarios.adapter=miAdaptadorPersona
        }

    }
}