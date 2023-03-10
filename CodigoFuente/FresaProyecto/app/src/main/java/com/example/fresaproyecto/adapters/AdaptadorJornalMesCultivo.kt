package com.example.fresaproyecto.adapters



import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.JornalCultivoVo
import com.example.fresaproyecto.interfaces.IComunicaFragments


class AdaptadorJornalMesCultivo() :
    RecyclerView.Adapter<AdaptadorJornalMesCultivo.ViewHolderMes>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaJornalMes: List<JornalCultivoVo> = Utilidades.listaJornalCultivo!!
    lateinit var vista: View
    lateinit var context: Context
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    var posicionMarcada: Int = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderMes {
        context = viewGroup.context
        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_jornal_mes_cultivo, viewGroup, false)

        vista.setOnClickListener(this)
        return ViewHolderMes(vista)
    }

    override fun onBindViewHolder(viewHolderMes: ViewHolderMes, i: Int) {

        val pos: Int = i

        //viewHolderMes.txtId.setText(listaPersona.get(i).nombre)  Otra forma
        /*viewHolderMes.cardJornalMes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                posicionMarcada=(pos+1)
                InformeCultivoFragment.cambiarFragment(posicionMarcada)
                notifyDataSetChanged()
            }
        })


         */
        /*viewHolderMes.txtMes.setText(listaJornalMes[i].mes.toString())
        viewHolderMes.txtAño.setText(listaJornalMes[i].año.toString())
        viewHolderMes.txtIngreso.setText(listaJornalMes[i].ingresos.toString())
        viewHolderMes.txtGastoJornal.setText(listaJornalMes[i].gastoJornal.toString())
        viewHolderMes.txtGastoInsumo.setText(listaJornalMes[i].gastoInsumo.toString())
        viewHolderMes.txtGanancia.setText(listaJornalMes[i].beneficio.toString())

         */

        var mesLetras = when (listaJornalMes[i].mes){
            1 -> "Enero"
            2 -> "Febrero"
            3 -> "Marzo"
            4 -> "Abril"
            5 -> "Mayo"
            6 -> "Junio"
            7 -> "Julio"
            8 -> "Agosto"
            9 -> "Septiembre"
            10 -> "Octubre"
            11 -> "Noviembre"
            12 -> "Diciembre"
            else -> "Sin Fecha"
        }

        viewHolderMes.txtFecha.setText(listaJornalMes[i].dia.toString()+ " de "+mesLetras+ " de "+listaJornalMes[i].año.toString())
        viewHolderMes.txtActividadJornal.setText(listaJornalMes[i].actividad)
        viewHolderMes.txtCantidadJornal.setText(listaJornalMes[i].cantidadJornal.toString() + " Jornales")
        viewHolderMes.txtPrecioJornal.setText("$"+listaJornalMes[i].precioJornal.toString())
        viewHolderMes.txtPrecioJornalTotal.setText("$"+listaJornalMes[i].gastoTotalJornal.toString())

    }


    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        //return (listaIngresos.size + listaGastos.size)
        return listaJornalMes.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderMes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtFecha : TextView
        var txtActividadJornal :TextView
        var txtCantidadJornal :TextView
        var txtPrecioJornal :TextView
        var txtPrecioJornalTotal :TextView

        var cardJornalMes: CardView

        init {
            txtFecha = vista.findViewById(R.id.txtFechaJornal)
            txtActividadJornal = vista.findViewById(R.id.txtActividadJornal)
            txtCantidadJornal = vista.findViewById(R.id.txtCantidadJornal)
            txtPrecioJornal = vista.findViewById(R.id.txtPrecioJornal)
            txtPrecioJornalTotal = vista.findViewById(R.id.txtPrecioJornalTotal)
            cardJornalMes = vista.findViewById(R.id.cardJornalMes)

        }
    }

    init {
        //this.listaPersona = listaPersona
    }
}

