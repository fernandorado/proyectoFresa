package com.example.fresaproyecto.adapters


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.IngresoPersonalVo
import com.example.fresaproyecto.interfaces.IComunicaFragments


class AdaptadorIngresoMesPersona() :
    RecyclerView.Adapter<AdaptadorIngresoMesPersona.ViewHolderMes>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaIngresoMes: List<IngresoPersonalVo> = Utilidades.listaIngresoPersonal!!
    lateinit var vista: View
    lateinit var context: Context
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    var posicionMarcada: Int = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderMes {
        context = viewGroup.context
        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_ingreso_mes_persona, viewGroup, false)

        vista.setOnClickListener(this)
        return ViewHolderMes(vista)
    }

    override fun onBindViewHolder(viewHolderMes: ViewHolderMes, i: Int) {

        val pos: Int = i

        var mesLetras = when (listaIngresoMes[i].mes) {
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

        viewHolderMes.txtFecha.setText(listaIngresoMes[i].dia.toString() + " de " + mesLetras + " de " + listaIngresoMes[i].año.toString())
        viewHolderMes.txtConceptoIngreso.setText(listaIngresoMes[i].concepto)
        viewHolderMes.txtCostoIngreso.setText("$"+listaIngresoMes[i].precio.toString())

    }


    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        //return (listaIngresos.size + listaIngresos.size)
        return listaIngresoMes.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderMes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtFecha: TextView
        var txtConceptoIngreso: TextView
        var txtCostoIngreso: TextView

        var cardIngresoMes: CardView

        init {
            txtFecha = vista.findViewById(R.id.txtFechaIngreso)
            txtConceptoIngreso = vista.findViewById(R.id.txtConceptoIngreso)
            txtCostoIngreso = vista.findViewById(R.id.txtCostoIngreso)
            cardIngresoMes = vista.findViewById(R.id.cardIngresoMes)

        }
    }
}

