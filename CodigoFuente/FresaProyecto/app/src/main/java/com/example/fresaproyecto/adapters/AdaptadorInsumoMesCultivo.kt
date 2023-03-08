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
import com.example.fresaproyecto.clases.vo.InsumoCultivoVo
import com.example.fresaproyecto.fragments.InformeCultivoFragment
import com.example.fresaproyecto.interfaces.IComunicaFragments


class AdaptadorInsumoMesCultivo() :
    RecyclerView.Adapter<AdaptadorInsumoMesCultivo.ViewHolderMes>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaInsumoMes: List<InsumoCultivoVo> = Utilidades.listaInsumoCultivo!!
    lateinit var vista: View
    lateinit var context: Context
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderMes {
        context = viewGroup.context
        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_insumo_mes_cultivo, viewGroup, false)

        vista.setOnClickListener(this)
        return ViewHolderMes(vista)
    }

    override fun onBindViewHolder(viewHolderMes: ViewHolderMes, i: Int) {

        var mesLetras = when (listaInsumoMes[i].mes){
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

        viewHolderMes.txtFecha.setText(listaInsumoMes[i].dia.toString()+ " de "+mesLetras+ " de "+listaInsumoMes[i].año.toString())
        viewHolderMes.txtNombreInsumo.setText(listaInsumoMes[i].nombreInsumo)
        viewHolderMes.txtCantUsoInsumo.setText(listaInsumoMes[i].cantidadInsumo.toString() + " " + listaInsumoMes[i].unidadInsumo)
        viewHolderMes.txtPrecioInsumoTotal.setText("$"+listaInsumoMes[i].gastoTotalInsumo.toString())

    }


    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        //return (listaIngresos.size + listaGastos.size)
        return listaInsumoMes.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderMes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtFecha : TextView
        var txtNombreInsumo :TextView
        var txtCantUsoInsumo :TextView
        var txtPrecioInsumoTotal :TextView

        var cardInsumoMes: CardView

        init {
            txtFecha = vista.findViewById(R.id.txtFechaInsumo)
            txtNombreInsumo = vista.findViewById(R.id.txtNombreInsumo)
            txtCantUsoInsumo = vista.findViewById(R.id.txtCantUsoInsumo)
            txtPrecioInsumoTotal = vista.findViewById(R.id.txtPrecioInsumoTotal)
            cardInsumoMes = vista.findViewById(R.id.cardInsumoMes)
        }
    }

    init {
        //this.listaPersona = listaPersona
    }
}

