package com.example.fresaproyecto.adapters

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.MainActivity
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.CultivoVo
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.dialogos.DialogoRegCultivo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream


class AdaptadorCultivo(listaCultivo: List<CultivoVo>) :
    RecyclerView.Adapter<AdaptadorCultivo.ViewHolderCultivo>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaCultivo: List<CultivoVo> = Utilidades.listaCultivos!!
    lateinit var vista: View
    lateinit var vgrupo: ViewGroup
    lateinit var vistaAct: View
    lateinit var context: Context
    var actividad: MainActivity? = null
    lateinit var campoName: EditText
    lateinit var campoCant: EditText
    lateinit var imgCultivo: ImageView
    var identificacion: Int = 0
    var nombre: String = ""
    var cantidad: Int = 0
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var builder: AlertDialog
    var posicionMarcada: Int = 0
    lateinit var bitmap: Bitmap

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderCultivo {

        vgrupo = viewGroup
        context = viewGroup.context
        actividad = viewGroup.context as? MainActivity


        /*val activity = viewGroup.context as? MainActivity
        interfaceComunicaFragments = activity as IComunicaFragments*/


        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_cultivos, viewGroup, false)

        vista.setOnClickListener(this)
        return ViewHolderCultivo(vista)
    }

    override fun onBindViewHolder(ViewHolderCultivo: ViewHolderCultivo, i: Int) {
        val pos: Int = i


        //viewHolderPersona.txtId.setText(listaPersona.get(i).nombre)  Otra forma
        ViewHolderCultivo.cardCultivo.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                posicionMarcada = pos

                notifyDataSetChanged()
            }
        })

        if (posicionMarcada == i) {
            DialogoGesCultivo.identificacion = posicionMarcada
            DialogoGesCultivo.cultivoSeleccionado =
                listaCultivo.get(pos) //Agregando la persona Seleccionada
            ViewHolderCultivo.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorSeleccion))
        } else {
            ViewHolderCultivo.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorBlanco))
        }

        //ViewHolderCultivo.txtId.setText(listaCultivo.get(i).nombre)  Otra forma
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
                            println("getAdapterPosition" + identificacion)
                            nombre =
                                listaCultivo[ViewHolderCultivo.adapterPosition].nombre.toString()
                            cantidad = listaCultivo[ViewHolderCultivo.adapterPosition].cantidad
                            //dialogoActualizar()
                            //interfaceComunicaFragments.regCultivo()
                            val fragmentActivity:FragmentActivity = context as FragmentActivity
                            val fm:FragmentManager = fragmentActivity.supportFragmentManager

                            val dialog : DialogoRegCultivo = DialogoRegCultivo()
                            dialog.show(fm, "Fragment")

                            //dialog.show(actividad?.supportFragmentManager,"DialogoRegCultivo")
                            //actividad?.supportFragmentManager?.let { dialog.show(it, "DialogoRegCultivo") }



                        }
                        R.id.opcEliminar -> {
                            DialogoGesCultivo.identificacion = -1
                            identificacion = listaCultivo[ViewHolderCultivo.adapterPosition].id
                            println("getAdapterPosition" + identificacion)
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
        vistaAct = LayoutInflater.from(context)
            .inflate(R.layout.fragment_dialogo_reg_cultivo, vgrupo, false)

        campoName = vistaAct.findViewById(R.id.campoNombre)
        campoCant = vistaAct.findViewById(R.id.campoCantidad)
        imgCultivo = vistaAct.findViewById(R.id.imgCultivo)
        builder = AlertDialog.Builder(vista.context).create()

        //d = builder.create()

        //builder.setTitle("Actualizar")
        builder.setView(vistaAct)

        vgrupo.removeView(vista)


        campoName.setText(nombre)
        campoCant.setText(""+cantidad)
        imgCultivo.setImageBitmap(bitmap)


        var btnActualizar: Button = vistaAct.findViewById(R.id.idBtnActualizar)
        var btnGuardar: Button = vistaAct.findViewById(R.id.idBtnGuardar)
        var btnCerrar: ImageButton = vistaAct.findViewById(R.id.btnIcoAtras)
        var txtTitulo: TextView = vistaAct.findViewById(R.id.txtTitulo)
        //var btnCancelar: Button = vistaAct.findViewById(R.id.btnCancelarAct)
        txtTitulo.setText("Actualizar Cultivo")
        btnGuardar.visibility = View.GONE
        btnActualizar.visibility = View.VISIBLE

        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                actualizarCultivo()

            }

        })

        btnCerrar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {

                builder.dismiss()
                vgrupo.removeView(vistaAct)

            }

        })
        return builder.show()
    }

    private fun actualizarCultivo() {
        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase


        if ((campoName.text.toString() != null && !campoName.text.toString().trim()
                .equals("")) and (campoCant.text.toString() != null && !campoCant.text.toString()
                .trim().equals(""))
        ) {

            val values = ContentValues()
            var baos: ByteArrayOutputStream = ByteArrayOutputStream(20480)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            var blob = baos.toByteArray()
            //Esto podria modificarlo
            //values.put(Utilidades.CAMPO_ID_PERSONA,id.text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_NOMBRE_CULTIVO, campoName.text.toString())
            values.put(Utilidades.CAMPO_CANT_PLANTAS, campoCant.text.toString())
            values.put(Utilidades.CAMPO_FOTO_CULTIVO, blob)

            val idResultante: Number = db.update(
                Utilidades.TABLA_CULTIVO,
                values,
                Utilidades.CAMPO_ID_CULTIVO + "=" + identificacion,
                null
            )

            if (idResultante != -1) {
                /*Utilidades.listaCultivos!!.removeAt(identificacion)
            notifyDataSetChanged()
            //Es para remover con un efecto bonito pero no funcionó del todo bien, elimanaba unos y a veces los confundia en la base de datos*/
                println("El usuario se Actualizó Exitosamente")
                Toast.makeText(
                    context,
                    "¡El usuario se Actualizó Exitosamente!",
                    Toast.LENGTH_SHORT
                ).show()
                //Utilidades.consultarlistaCultivos(MainActivity())
                DialogoGesCultivo.llenarAdaptadorCultivos()

                builder.dismiss()


            } else {
                Toast.makeText(
                    context,
                    "EL usuario no se pudo Actualizar, intente nuevamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            if (campoName.text.toString().isEmpty()) {
                campoName.setError("Este campo no puede quedar vacio")
            } else if (campoCant.text.toString().isEmpty()) {
                campoCant.setError("Este campo no puede quedar vacio")
            }
            Toast.makeText(
                actividad,
                "Verifique que todos los campos esten registrados \n ",
                Toast.LENGTH_LONG
            ).show()
        }
        db.close()
    }

    fun dialogoEliminar(): AlertDialog {

        val builder = AlertDialog.Builder(vista.context)

        builder.setTitle("Eliminar")
            .setMessage("Esta seguro que quiere eliminar a " + nombre.uppercase() + " de sus usuarios Registrados?")
            .setPositiveButton("Si") { dialog, _ ->

                dialog.dismiss()
                eliminarCultivo()

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }


    private fun eliminarCultivo() {
        println("Eliminar " + nombre)
        val text = "Eliminar " + nombre
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()

        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_CULTIVO,
                Utilidades.CAMPO_ID_CULTIVO + "=" + identificacion,
                null
            )

        if (idResultante != -1) {
            /*Utilidades.listaCultivos!!.removeAt(identificacion)
            notifyDataSetChanged()
            //Es para remover con un efecto bonito pero no funcionó del todo bien, elimanaba unos y a veces los confundia en la base de datos*/
            println("El usuario se eliminó Exitosamente")
            Toast.makeText(context, "¡El usuario se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            //Utilidades.consultarlistaCultivos(MainActivity())
            DialogoGesCultivo.llenarAdaptadorCultivos()

        } else {
            Toast.makeText(context, "EL usuario no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }
        db.close()

        //DialogoGesPersona.llenarAdaptadorUsuarios()
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

