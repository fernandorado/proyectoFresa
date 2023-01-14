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
    var listaInformeMes: List<BeneficioCultivoVo> = Utilidades.listaBeneficioCultivo!!
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
        viewHolderMes.cardInformeMes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                posicionMarcada=pos

                notifyDataSetChanged()
            }
        })

        if (posicionMarcada == i){
            //Mes Seleccionado deberia ser aqui
            CalGananciasCultivoFragment.mesSeleccionado = listaInformeMes.get(pos) //Agregando el beneficio por mes
            //viewHolderMes.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorSeleccion))
        }else{
            //viewHolderMes.barraSeleccion.setBackgroundColor(vista.resources.getColor(R.color.colorBlanco))
        }

        /*viewHolderMes.txtMes.setText(listaInformeMes[i].mes.toString())
        viewHolderMes.txtAño.setText(listaInformeMes[i].año.toString())
        viewHolderMes.txtIngreso.setText(listaInformeMes[i].ingresos.toString())
        viewHolderMes.txtGastoJornal.setText(listaInformeMes[i].gastoJornal.toString())
        viewHolderMes.txtGastoInsumo.setText(listaInformeMes[i].gastoInsumo.toString())
        viewHolderMes.txtGanancia.setText(listaInformeMes[i].beneficio.toString())

         */

        viewHolderMes.txtIngreso.setText(listaInformeMes[i].ingresos.toString())
        viewHolderMes.txtGasJornal.setText(listaInformeMes[i].gastoJornal.toString())
        viewHolderMes.txtGasInsumo.setText(listaInformeMes[i].gastoInsumo.toString())
        viewHolderMes.txtIngreso.setText(listaInformeMes[i].ingresos.toString())
        viewHolderMes.txtBeneficio.setText(listaInformeMes[i].beneficio.toString())
        viewHolderMes.txtCosechaExtra.setText(listaInformeMes[i].extra.toString())
        viewHolderMes.txtCosechaPrimera.setText(listaInformeMes[i].primera.toString())
        viewHolderMes.txtCosechaSegunda.setText(listaInformeMes[i].segunda.toString())
        viewHolderMes.txtCosechaTercera.setText(listaInformeMes[i].tercera.toString())
        viewHolderMes.txtCosechaCuarta.setText(listaInformeMes[i].cuarta.toString())
        viewHolderMes.txtCosechaQuinta.setText(listaInformeMes[i].quinta.toString())
        viewHolderMes.txtCosechaMadura.setText(listaInformeMes[i].madura.toString())
        viewHolderMes.txtMes.setText(listaInformeMes[i].mes.toString()+". ")
        viewHolderMes.txtAño.setText(listaInformeMes[i].año.toString())

    }


    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        //return (listaIngresos.size + listaGastos.size)
        return listaInformeMes.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderMes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtAño : TextView
        var txtMes : TextView
        //TextView de Informe General
        //----------TextView por mes
        //TextView mes Enero
        var txtGasInsumo :TextView
        var txtGasJornal :TextView
        var txtIngreso : TextView
        var txtBeneficio :TextView
        var txtCosechaExtra :TextView
        var txtCosechaPrimera :TextView
        var txtCosechaSegunda :TextView
        var txtCosechaTercera :TextView
        var txtCosechaCuarta :TextView
        var txtCosechaQuinta :TextView
        var txtCosechaMadura :TextView

        var cardInformeMes: CardView

        init {
            txtAño = vista.findViewById(R.id.txtAño)
            txtMes = vista.findViewById(R.id.txtMes)

            txtGasInsumo = vista.findViewById(R.id.txtInsumo)
            txtGasJornal = vista.findViewById(R.id.txtJornal)
            txtIngreso = vista.findViewById(R.id.txtIngreso)
            txtBeneficio = vista.findViewById(R.id.txtBeneficio)
            txtCosechaExtra = vista.findViewById(R.id.txtPrecioExtra)
            txtCosechaPrimera = vista.findViewById(R.id.txtPrecioPrimera)
            txtCosechaSegunda = vista.findViewById(R.id.txtPrecioSegunda)
            txtCosechaTercera = vista.findViewById(R.id.txtPrecioTercera)
            txtCosechaCuarta = vista.findViewById(R.id.txtPrecioCuarta)
            txtCosechaQuinta = vista.findViewById(R.id.txtPrecioQuinta)
            txtCosechaMadura = vista.findViewById(R.id.txtPrecioMadura)
            cardInformeMes = vista.findViewById(R.id.cardInformeMes)

        }
    }

    init {
        //this.listaPersona = listaPersona
    }
}

