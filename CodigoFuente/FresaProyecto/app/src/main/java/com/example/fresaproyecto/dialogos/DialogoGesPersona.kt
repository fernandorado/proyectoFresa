package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.SupportActionModeWrapper
import androidx.appcompat.view.SupportMenuInflater
import androidx.core.view.isVisible
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorPersona
import com.example.fresaproyecto.adapters.OnClickListenerPersona
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.PersonaVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogoGesPersona.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoGesPersona : DialogFragment(), OnClickListenerPersona {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    lateinit var campoNombre: EditText
    lateinit var campoIdentificacion: EditText
    lateinit var btnCerrar: ImageButton
    lateinit var btnExtNuevo: ExtendedFloatingActionButton
    lateinit var btnExtContinuar: ExtendedFloatingActionButton









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
        vista = inflater.inflate(R.layout.fragment_dialogo_ges_persona, container,false)

        recyclerUsuarios = vista.findViewById(R.id.recyclerUsuario)
        recyclerUsuarios.layoutManager = LinearLayoutManager(actividad)
        recyclerUsuarios.setHasFixedSize(true)







        btnCerrar = vista.findViewById(R.id.btnCerrar)
        btnExtNuevo = vista.findViewById(R.id.btnNuevo)
        btnExtContinuar = vista.findViewById(R.id.btnContinuar)

        //btnFlotanteActualziar.visibility = View.GONE
        //btnFlotanteEliminar.visibility = View.GONE
        //btnExtEditar.shrink()  Para cerrar el boton Extend
        //btnExtEditar.extend()  Para abrir el boton, extenderlo

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
                // Do some work here
                /*if(btnFlotanteActualziar.isVisible and btnFlotanteEliminar.isVisible){
                    btnFlotanteActualziar.visibility = View.GONE
                    btnFlotanteEliminar.visibility = View.GONE
                }else{
                    btnFlotanteActualziar.visibility = View.VISIBLE
                    btnFlotanteEliminar.visibility = View.VISIBLE
                }*/
                interfaceComunicaFragments.regPersona()

            }

        })



        btnExtContinuar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                val text ="Continuar"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
                interfaceComunicaFragments.menuPersonal()
                dismiss()



            }

        })
    }

    private fun seleccionarUsuario(){

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

            //se asigna la lista de jugadores por defecto
            var miAdaptadorJugadores = AdaptadorPersona()

            miAdaptadorJugadores.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    personaSeleccionada= Utilidades.listaPersonas!!.get(recyclerUsuarios.getChildAdapterPosition(view!!))
                }
            })

            recyclerUsuarios.adapter=miAdaptadorJugadores
        }
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DialogoGesPersona.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoGesPersona().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}