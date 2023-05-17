package com.misRegistros.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.misRegistros.R
import com.misRegistros.clases.ConexionSQLiteHelper
import com.misRegistros.clases.Utilidades
import com.misRegistros.clases.vo.PersonaVo
import com.misRegistros.controllers.PersonaRestController
import com.misRegistros.dialogos.DialogoActPersona
import com.misRegistros.dialogos.DialogoGesPersona
import com.misRegistros.interfaces.IComunicaFragments

class AdaptadorPersona() :
    RecyclerView.Adapter<AdaptadorPersona.ViewHolderPersona>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaPersona: List<PersonaVo> = Utilidades.listaPersonas!!
    lateinit var vista: View
    lateinit var vgrupo: ViewGroup
    lateinit var vistaAct: View
    lateinit var context: Context
    lateinit var actividad: Activity
    var identificacion: Int = 0
    var nombre: String = ""
    lateinit var interfaceComunicaFragments: IComunicaFragments
    var personaController: PersonaRestController = PersonaRestController()
    var d: AlertDialog? = null
    lateinit var builder: AlertDialog
    var posicionMarcada: Int = 0


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderPersona {
        vgrupo = viewGroup
        context = viewGroup.context
        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_persona, viewGroup, false)

        vista.setOnClickListener(this)
        return ViewHolderPersona(vista)
    }

    override fun onBindViewHolder(viewHolderPersona: ViewHolderPersona, i: Int) {
        val pos: Int = i

        viewHolderPersona.cardUsuario.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                posicionMarcada = pos

                notifyDataSetChanged()
            }
        })

        if (posicionMarcada == i) {
            DialogoGesPersona.personaSeleccionada =
                listaPersona.get(pos) //Agregando la persona Seleccionada
            viewHolderPersona.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorPersonal))
        } else {
            viewHolderPersona.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorBlanco))
        }


        viewHolderPersona.txtId.setText(listaPersona[i].id.toString())
        viewHolderPersona.txtNombre.setText(listaPersona[i].nombre.toString())
        viewHolderPersona.menuPopUp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

                val popupMenu = PopupMenu(context, view)
                popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)
                popupMenu.show()

                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->


                    when (item!!.itemId) {
                        R.id.opcEditar -> {
                            identificacion = listaPersona[viewHolderPersona.adapterPosition].id
                            nombre =
                                listaPersona[viewHolderPersona.adapterPosition].nombre.toString()

                            dialogoActualizar()
                        }
                        R.id.opcEliminar -> {

                            identificacion = listaPersona[viewHolderPersona.adapterPosition].id
                            nombre =
                                listaPersona[viewHolderPersona.adapterPosition].nombre.toString()

                            dialogoEliminar().show()

                        }
                    }

                    true
                })
            }
        })

    }


    private fun unwrap(context: Context): Activity? {
        var context: Context? = context
        while (context !is Activity && context is ContextWrapper) {
            context = context.baseContext
        }
        return context as Activity?
    }

    fun dialogoActualizar() {

        DialogoActPersona.identificacion = identificacion
        DialogoActPersona.nombre = nombre

        interfaceComunicaFragments = unwrap(context) as IComunicaFragments
        interfaceComunicaFragments.actPersona()
    }

    fun dialogoEliminar(): AlertDialog {

        val builder = AlertDialog.Builder(vista.context)

        builder.setTitle("Eliminar")
            .setMessage("Esta seguro que quiere eliminar a " + nombre.uppercase() + " de sus usuarios Registrados?")
            .setPositiveButton("Si") { dialog, _ ->

                dialog.dismiss()
                eliminarUsuario()

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }


    private fun eliminarUsuario() {
        if (personaController.delete(vista.context, identificacion) == true) {
            Toast.makeText(context, "¡El usuario se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            DialogoGesPersona.llenarAdaptadorUsuarios()

        } else {
            Toast.makeText(context, "EL usuario no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }

        /*val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_PERSONA,
                Utilidades.CAMPO_ID_PERSONA + "=" + identificacion,
                null
            )

        if (idResultante != -1) {
            Toast.makeText(context, "¡El usuario se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            DialogoGesPersona.llenarAdaptadorUsuarios()

        } else {
            Toast.makeText(context, "EL usuario no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }
        db.close()*/
    }

    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return listaPersona.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderPersona(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtId: TextView
        var txtNombre: TextView
        var menuPopUp: ImageView
        var cardUsuario: CardView
        var barraSeleccion: TextView

        init {
            txtId = itemView.findViewById(R.id.idIdentificacion)
            txtNombre = itemView.findViewById(R.id.idNombre)
            menuPopUp = itemView.findViewById(R.id.menuOpciones)
            cardUsuario = itemView.findViewById(R.id.cardUsuario)
            barraSeleccion = itemView.findViewById((R.id.barraSeleccionId))
        }
    }

    init {
        this.listaPersona = listaPersona
    }
}

