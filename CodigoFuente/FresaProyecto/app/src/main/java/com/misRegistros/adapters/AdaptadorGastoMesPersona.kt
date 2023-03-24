package com.misRegistros.adapters



import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.misRegistros.R
import com.misRegistros.clases.Utilidades
import com.misRegistros.clases.vo.GastoPersonalVo
import com.misRegistros.interfaces.IComunicaFragments


class AdaptadorGastoMesPersona() :
    RecyclerView.Adapter<AdaptadorGastoMesPersona.ViewHolderMes>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaGastoMes: List<GastoPersonalVo> = Utilidades.listaGastoPersonal!!
    lateinit var vista: View
    lateinit var context: Context
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderMes {
        context = viewGroup.context
        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_gasto_mes_persona, viewGroup, false)

        vista.setOnClickListener(this)
        return ViewHolderMes(vista)
    }

    override fun onBindViewHolder(viewHolderMes: ViewHolderMes, i: Int) {

        val pos: Int = i

        var mesLetras = when (listaGastoMes[i].mes){
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

        viewHolderMes.txtFecha.setText(listaGastoMes[i].dia.toString()+ " de "+mesLetras+ " de "+listaGastoMes[i].año.toString())
        viewHolderMes.txtConceptoGasto.setText(listaGastoMes[i].concepto)
        viewHolderMes.txtCostoGasto.setText("$"+listaGastoMes[i].precio.toString())

    }


    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        return listaGastoMes.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderMes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtFecha : TextView
        var txtConceptoGasto :TextView
        var txtCostoGasto :TextView

        var cardGastoMes: CardView

        init {
            txtFecha = vista.findViewById(R.id.txtFechaGasto)
            txtConceptoGasto = vista.findViewById(R.id.txtConceptoGasto)
            txtCostoGasto = vista.findViewById(R.id.txtCostoGasto)
            cardGastoMes = vista.findViewById(R.id.cardGastoMes)

        }
    }

}

