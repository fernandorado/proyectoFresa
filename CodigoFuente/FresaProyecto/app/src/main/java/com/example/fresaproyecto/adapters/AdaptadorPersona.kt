package com.example.fresaproyecto.adapters



import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.content.DialogInterface
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.MainActivity
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.PersonaVo
import com.example.fresaproyecto.dialogos.DialogoActPersona
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.interfaces.IComunicaFragments


class AdaptadorPersona() :
    RecyclerView.Adapter<AdaptadorPersona.ViewHolderPersona>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaPersona: List<PersonaVo> = Utilidades.listaPersonas!!
    lateinit var vista: View
    lateinit var vgrupo: ViewGroup
    lateinit var vistaAct: View
    lateinit var context: Context
    lateinit var actividad: Activity
    var identificacion : Int = 0
    var nombre : String = ""
    lateinit var interfaceComunicaFragments: IComunicaFragments
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

        //viewHolderPersona.txtId.setText(listaPersona.get(i).nombre)  Otra forma
        viewHolderPersona.cardUsuario.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                posicionMarcada=pos

                notifyDataSetChanged()
            }
        })

        if (posicionMarcada == i){
            DialogoGesPersona.personaSeleccionada = listaPersona.get(pos) //Agregando la persona Seleccionada
            viewHolderPersona.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorPersonal))
        }else{
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
                            println("getAdapterPosition"+ identificacion)
                            nombre = listaPersona[viewHolderPersona.adapterPosition].nombre.toString()





                            /*val dialogoActualizarPersona = DialogoActPersona()

                            val fragmentManager: FragmentManager =
                                (view.getContext() as FragmentActivity).supportFragmentManager // instantiate your view context

                            val fragmentTransaction: FragmentTransaction =
                                fragmentManager.beginTransaction()*/



                            //dialogoActualizarPersona.show(fragmentManager, "DialogoRegPersonas")
                            //val builder = AlertDialog.Builder(vista.context)
                            dialogoActualizar()















                        }
                        R.id.opcEliminar -> {

                            identificacion = listaPersona[viewHolderPersona.adapterPosition].id
                            println("getAdapterPosition"+ identificacion)
                            nombre = listaPersona[viewHolderPersona.adapterPosition].nombre.toString()


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
        println("Eliminar " + nombre)
        val text ="Eliminar " + nombre
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()

        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(Utilidades.TABLA_PERSONA, Utilidades.CAMPO_ID_PERSONA+"="+identificacion, null)

        if(idResultante != -1){
            /*Utilidades.listaPersonas!!.removeAt(identificacion)
            notifyDataSetChanged()
            //Es para remover con un efecto bonito pero no funcionó del todo bien, elimanaba unos y a veces los confundia en la base de datos*/
            println("El usuario se eliminó Exitosamente")
            Toast.makeText(context, "¡El usuario se eliminó Exitosamente!", Toast.LENGTH_SHORT).show()
            //Utilidades.consultarListaPersonas(MainActivity())
            DialogoGesPersona.llenarAdaptadorUsuarios()

        }else{
            Toast.makeText(context, "EL usuario no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }
        db.close()

        //DialogoGesPersona.llenarAdaptadorUsuarios()
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

