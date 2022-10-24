package com.example.fresaproyecto.adapters



import android.app.Activity
import android.app.AlertDialog
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.CultivoVo
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.interfaces.IComunicaFragments


class AdaptadorCultivo(listaCultivo: List<CultivoVo>) :
    RecyclerView.Adapter<AdaptadorCultivo.ViewHolderCultivo>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaCultivo: List<CultivoVo> = Utilidades.listaCultivos!!
    lateinit var vista: View
    lateinit var vgrupo: ViewGroup
    lateinit var vistaAct: View
    lateinit var context: Context
    lateinit var actividad: Activity
    var identificacion : Int = 0
    var nombre : String = ""
    var cantidad : Int = 0
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var builder: AlertDialog




    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderCultivo {


        vgrupo = viewGroup










        context = viewGroup.context



                vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_cultivo, viewGroup, false)


        vista.setOnClickListener(this)
        return ViewHolderCultivo(vista)
    }

    override fun onBindViewHolder(ViewHolderCultivo: ViewHolderCultivo, i: Int) {






        //ViewHolderCultivo.txtId.setText(listaCultivo.get(i).nombre)  Otra forma
        ViewHolderCultivo.txtCant.setText(listaCultivo[i].cantidad.toString())
        ViewHolderCultivo.txtNombre.setText(listaCultivo[i].nombre.toString())
        ViewHolderCultivo.menuPopUp.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {

                val popupMenu = PopupMenu(context, view)
                popupMenu.menuInflater.inflate(R.menu.menu_main, popupMenu.menu)
                popupMenu.show()

                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->


                    when (item!!.itemId) {
                        R.id.opcEditar -> {
                            identificacion = listaCultivo[ViewHolderCultivo.adapterPosition].id
                            println("getAdapterPosition"+ identificacion)
                            nombre = listaCultivo[ViewHolderCultivo.adapterPosition].nombre.toString()
                            cantidad = listaCultivo[ViewHolderCultivo.adapterPosition].cantidad





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

                            identificacion = listaCultivo[ViewHolderCultivo.adapterPosition].id
                            println("getAdapterPosition"+ identificacion)
                            nombre = listaCultivo[ViewHolderCultivo.adapterPosition].nombre.toString()
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
            .inflate(R.layout.fragment_dialogo_act_cultivo, vgrupo, false)

        builder = AlertDialog.Builder(vista.context).create()

        //d = builder.create()

        builder.setTitle("Actualizar")
        builder.setView(vistaAct)

        /*.setPositiveButton("Actualizar") { dialog, _ ->
            //Se cierra el dialogo
            actualizarUsuario()


            //Se remueven las vistas
            vgrupo.removeView(vistaAct)
            vgrupo.removeView(vista)

        }

        .setNegativeButton("Cerrar") { dialog, _ ->
            //Se cierra el dialogo
            dialog.dismiss()
            //Se remueven las vistas
            vgrupo.removeView(vistaAct)
            vgrupo.removeView(vista)

        }
    .setCancelable(false)
    .create()*/


        vgrupo.removeView(vista)


        var btnActualizar: Button = vistaAct.findViewById(R.id.btnActualizarAct)
        var btnCancelar: Button = vistaAct.findViewById(R.id.btnCancelarAct)






        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                actualizarUsuario()



            }

        })


        btnCancelar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {


                //d?.dismiss()
                builder.dismiss()
                vgrupo.removeView(vistaAct)



            }

        })
        return builder.show()
    }

    private fun actualizarUsuario() {
        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase
        //var id: EditText = vistaAct.findViewById(R.id.campoIdentificacionAct)
        var nombre: EditText = vistaAct.findViewById(R.id.campoNombreAct)
        var cantidad: EditText = vistaAct.findViewById(R.id.campoCantidadAct)





        //if((id.text.toString()!=null && !id.text.toString().trim().equals("")) and (nombre.text.toString()!=null && !nombre.text.toString().trim().equals(""))) {


            val values = ContentValues()
            //Esto podria modificarlo
            //values.put(Utilidades.CAMPO_ID_PERSONA,id.text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_NOMBRE_CULTIVO, nombre.text.toString())
            values.put(Utilidades.CAMPO_CANT_PLANTAS, cantidad.text.toString())

            val idResultante: Number = db.update(
                Utilidades.TABLA_CULTIVO,
                values,
                Utilidades.CAMPO_ID_CULTIVO+"="+identificacion,
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
        /*}else{
            if(id.text.toString().isBlank()){
                //id.setError("Este campo no puede quedar vacio")
                id.error = "Este Campo no puede quedar vacio"
            }else if (nombre.text.toString().isBlank()){
                nombre.setError("Este campo no puede quedar vacio")
            }
            Toast.makeText(context, "Verifique que todos los campos esten llenos\n ", Toast.LENGTH_LONG).show()
        }*/
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
        val text ="Eliminar " + nombre
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(context, text, duration)
        toast.show()

        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(Utilidades.TABLA_CULTIVO, Utilidades.CAMPO_ID_CULTIVO+"="+identificacion, null)

        if(idResultante != -1){
            /*Utilidades.listaCultivos!!.removeAt(identificacion)
            notifyDataSetChanged()
            //Es para remover con un efecto bonito pero no funcionó del todo bien, elimanaba unos y a veces los confundia en la base de datos*/
            println("El usuario se eliminó Exitosamente")
            Toast.makeText(context, "¡El usuario se eliminó Exitosamente!", Toast.LENGTH_SHORT).show()
            //Utilidades.consultarlistaCultivos(MainActivity())
            DialogoGesCultivo.llenarAdaptadorCultivos()

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

        init {
            txtCant = itemView.findViewById(R.id.idCantidad)
            txtNombre = itemView.findViewById(R.id.idNombre)
            menuPopUp = itemView.findViewById(R.id.menuOpciones)
        }
    }

    init {
        this.listaCultivo = listaCultivo
    }
}

