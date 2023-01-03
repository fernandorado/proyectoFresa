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
import com.example.fresaproyecto.clases.vo.BeneficioCultivoVo
import com.example.fresaproyecto.fragments.CalGananciasCultivoFragment
import com.example.fresaproyecto.interfaces.IComunicaFragments


class AdaptadorMesCultivo(listaMesCultivo: List<BeneficioCultivoVo>) :
    RecyclerView.Adapter<AdaptadorMesCultivo.ViewHolderMes>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaMes: List<BeneficioCultivoVo> = Utilidades.listaBeneficioCultivo!!
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




    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderMes {


        vgrupo = viewGroup

        context = viewGroup.context

                vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_mes_cultivo, viewGroup, false)


        vista.setOnClickListener(this)
        return ViewHolderMes(vista)
    }

    override fun onBindViewHolder(viewHolderMes: ViewHolderMes, i: Int) {

        val pos: Int = i

        //viewHolderMes.txtId.setText(listaPersona.get(i).nombre)  Otra forma
        viewHolderMes.cardMes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                posicionMarcada=pos

                notifyDataSetChanged()
            }
        })

        if (posicionMarcada == i){
            //Mes Seleccionado deberia ser aqui
            CalGananciasCultivoFragment.mesSeleccionado = listaMes.get(pos) //Agregando el beneficio por mes
            viewHolderMes.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorSeleccion))
        }else{
            viewHolderMes.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorBlanco))
        }

        viewHolderMes.txtMes.setText(listaMes[i].mes.toString())
        viewHolderMes.txtAño.setText(listaMes[i].año.toString())
        viewHolderMes.txtIngreso.setText(listaMes[i].ingresos.toString())
        viewHolderMes.txtGastoJornal.setText(listaMes[i].gastoJornal.toString())
        viewHolderMes.txtGastoInsumo.setText(listaMes[i].gastoInsumo.toString())
        viewHolderMes.txtGanancia.setText(listaMes[i].beneficio.toString())

    }


    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        //return (listaIngresos.size + listaGastos.size)
        return listaMes.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderMes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtMes: TextView
        var txtAño: TextView
        var txtGastoInsumo: TextView
        var txtGastoJornal: TextView
        var txtIngreso: TextView
        var txtGanancia: TextView
        var cardMes: CardView
        var barraSeleccion: TextView

        init {
            txtMes = itemView.findViewById(R.id.idMes)
            txtAño = itemView.findViewById(R.id.idAño)
            txtGastoInsumo = itemView.findViewById(R.id.idGastoInsumo)
            txtGastoJornal = itemView.findViewById(R.id.idGastoJornal)
            txtIngreso = itemView.findViewById(R.id.idIngresoMensual)
            txtGanancia = itemView.findViewById(R.id.idGanancias)
            cardMes = itemView.findViewById(R.id.cardMes)
            barraSeleccion = itemView.findViewById((R.id.barraSeleccionMes))
        }
    }

    init {
        //this.listaPersona = listaPersona
    }
}

