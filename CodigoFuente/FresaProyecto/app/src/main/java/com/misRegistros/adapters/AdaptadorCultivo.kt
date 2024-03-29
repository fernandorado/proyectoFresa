package com.misRegistros.adapters

import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.content.ContextWrapper
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
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
import com.misRegistros.clases.vo.CultivoVo
import com.misRegistros.controllers.CultivoRestController
import com.misRegistros.dialogos.DialogoActCultivo
import com.misRegistros.dialogos.DialogoGesCultivo
import com.misRegistros.dialogos.DialogoRegCultivo
import com.misRegistros.interfaces.IComunicaFragments
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


class AdaptadorCultivo() :
    RecyclerView.Adapter<AdaptadorCultivo.ViewHolderCultivo>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaCultivo: List<CultivoVo> = Utilidades.listaCultivos!!
    lateinit var vista: View
    lateinit var vgrupo: ViewGroup
    lateinit var vistaAct: View
    lateinit var context: Context
    lateinit var actividad: Activity
    lateinit var campoName: EditText
    lateinit var campoCant: EditText
    lateinit var imgCultivo: ImageView
    var identificacion: Int = 0
    var nombre: String = ""
    var cantidad: Int = 0
    lateinit var interfaceComunicaFragments: IComunicaFragments
    var cultivoController: CultivoRestController = CultivoRestController()
    lateinit var builder: AlertDialog
    var posicionMarcada: Int = 0
    lateinit var bitmap: Bitmap

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderCultivo {

        vgrupo = viewGroup
        context = viewGroup.getContext()

        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_cultivos, viewGroup, false)

        vista.setOnClickListener(this)
        return ViewHolderCultivo(vista)
    }

    private fun unwrap(context: Context): Activity? {
        var context: Context? = context
        while (context !is Activity && context is ContextWrapper) {
            context = context.baseContext
        }
        return context as Activity?
    }

    override fun onBindViewHolder(ViewHolderCultivo: ViewHolderCultivo, i: Int) {
        val pos: Int = i

        ViewHolderCultivo.cardCultivo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                posicionMarcada = pos
                notifyDataSetChanged()
            }
        })

        if (posicionMarcada == i) {
            DialogoGesCultivo.cultivoSeleccionado =
                listaCultivo.get(pos) //Agregando el cultivo Seleccionado
            ViewHolderCultivo.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorCultivo))
        } else {
            ViewHolderCultivo.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorBlanco))
        }

        ViewHolderCultivo.txtCant.setText(listaCultivo[i].cantidad.toString())
        ViewHolderCultivo.txtNombre.setText(listaCultivo[i].nombre.toString())
        val blob: ByteArray = listaCultivo[i].imgCultivo.inputStream().readBytes()
        val bais = ByteArrayInputStream(blob)
        bitmap = BitmapFactory.decodeStream(bais)
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        ViewHolderCultivo.imgCultivo.setImageBitmap(bitmap)
        ViewHolderCultivo.menuPopUp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

                val popupMenu = PopupMenu(context, view)
                popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)
                popupMenu.show()

                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->
                    when (item!!.itemId) {
                        R.id.opcEditar -> {
                            val blob: ByteArray =
                                listaCultivo[ViewHolderCultivo.adapterPosition].imgCultivo.inputStream()
                                    .readBytes()
                            val bais = ByteArrayInputStream(blob)
                            bitmap = BitmapFactory.decodeStream(bais)
                            identificacion = listaCultivo[ViewHolderCultivo.adapterPosition].id
                            nombre =
                                listaCultivo[ViewHolderCultivo.adapterPosition].nombre.toString()
                            cantidad = listaCultivo[ViewHolderCultivo.adapterPosition].cantidad
                            dialogoActualizar()

                        }
                        R.id.opcEliminar -> {
                            identificacion = listaCultivo[ViewHolderCultivo.adapterPosition].id
                            nombre =
                                listaCultivo[ViewHolderCultivo.adapterPosition].nombre.toString()
                            cantidad = listaCultivo[ViewHolderCultivo.adapterPosition].cantidad

                            dialogoEliminar().show()

                        }
                    }

                    true
                })
            }
        })
    }

    fun dialogoActualizar() {

        DialogoActCultivo.nombre = nombre
        DialogoActCultivo.cantidad = cantidad
        DialogoActCultivo.identificacion = identificacion
        DialogoActCultivo.bitmap = bitmap

        interfaceComunicaFragments = unwrap(context) as IComunicaFragments
        interfaceComunicaFragments.actCultivo()

    }

    fun dialogoEliminar(): AlertDialog {

        val builder = AlertDialog.Builder(vista.context)

        builder.setTitle("Eliminar")
            .setMessage("Esta seguro que quiere eliminar a " + nombre.uppercase() + " de sus cultivos Registrados?")
            .setPositiveButton("Si") { dialog, _ ->

                eliminarCultivo()
                dialog.dismiss()

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }


    private fun eliminarCultivo() {

        if (cultivoController.delete(vista.context, identificacion) == true) {
            Toast.makeText(context, "¡El cultivo se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            DialogoGesCultivo.llenarAdaptadorCultivos()

        } else {
            Toast.makeText(context, "EL cultivo no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }

    }

    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return listaCultivo.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderCultivo(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtCant: TextView
        var txtNombre: TextView
        var menuPopUp: ImageView
        var imgCultivo: ImageView
        var cardCultivo: CardView
        var barraSeleccion: TextView

        init {
            txtCant = itemView.findViewById(R.id.idCantidadCultivo)
            txtNombre = itemView.findViewById(R.id.idNombreCultivo)
            menuPopUp = itemView.findViewById(R.id.menuOpciones)
            imgCultivo = itemView.findViewById(R.id.idImagenCultivo)
            cardCultivo = itemView.findViewById(R.id.cardCultivo)
            barraSeleccion = itemView.findViewById((R.id.barraSeleccionIdCultivo))
        }
    }

    init {
        this.listaCultivo = listaCultivo
    }
}

