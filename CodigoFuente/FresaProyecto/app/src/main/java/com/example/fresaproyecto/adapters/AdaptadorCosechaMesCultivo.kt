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
import com.example.fresaproyecto.clases.vo.CosechaCultivoVo
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.fragments.CalGananciasCultivoFragment
import com.example.fresaproyecto.fragments.InformeCultivoFragment
import com.example.fresaproyecto.interfaces.IComunicaFragments


class AdaptadorCosechaMesCultivo(listaMesCultivo: List<CosechaCultivoVo>) :
    RecyclerView.Adapter<AdaptadorCosechaMesCultivo.ViewHolderMes>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaCosechaMes: List<CosechaCultivoVo> = Utilidades.listaCosechaCultivo!!
    lateinit var vista: View
    lateinit var context: Context
    lateinit var actividad: Activity
    var identificacion : Int = 0
    var nombre : String = ""
    lateinit var interfaceComunicaFragments: IComunicaFragments
    var d: AlertDialog? = null
    lateinit var builder: AlertDialog
    var posicionMarcada: Int = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderMes {


        context = viewGroup.context

        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_cosecha_mes_cultivo, viewGroup, false)


        vista.setOnClickListener(this)
        return ViewHolderMes(vista)
    }

    override fun onBindViewHolder(viewHolderMes: ViewHolderMes, i: Int) {

        val pos: Int = i

        //viewHolderMes.txtId.setText(listaPersona.get(i).nombre)  Otra forma
        viewHolderMes.cardInformeMes.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                posicionMarcada=(pos+1)
                notifyDataSetChanged()
            }
        })


        /*viewHolderMes.txtMes.setText(listaCosechaMes[i].mes.toString())
        viewHolderMes.txtAño.setText(listaCosechaMes[i].año.toString())
        viewHolderMes.txtIngreso.setText(listaCosechaMes[i].ingresos.toString())
        viewHolderMes.txtGastoJornal.setText(listaCosechaMes[i].gastoJornal.toString())
        viewHolderMes.txtGastoInsumo.setText(listaCosechaMes[i].gastoInsumo.toString())
        viewHolderMes.txtGanancia.setText(listaCosechaMes[i].beneficio.toString())

         */

        var mesLetras = when (listaCosechaMes[i].mes){
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

        viewHolderMes.txtFecha.setText(listaCosechaMes[i].dia.toString()+ " de "+mesLetras+ " de "+listaCosechaMes[i].año.toString())
        viewHolderMes.txtLibrasExtra.setText(listaCosechaMes[i].extra.toString())
        viewHolderMes.txtLibrasPrimera.setText(listaCosechaMes[i].primera.toString())
        viewHolderMes.txtLibrasSegunda.setText(listaCosechaMes[i].segunda.toString())
        viewHolderMes.txtLibrasTercera.setText(listaCosechaMes[i].tercera.toString())
        viewHolderMes.txtLibrasCuarta.setText(listaCosechaMes[i].cuarta.toString())
        viewHolderMes.txtLibrasQuinta.setText(listaCosechaMes[i].quinta.toString())
        viewHolderMes.txtLibrasMadura.setText(listaCosechaMes[i].madura.toString())
        viewHolderMes.txtPrecioExtra.setText("$"+listaCosechaMes[i].precioExtra.toString())
        viewHolderMes.txtPrecioPrimera.setText("$"+listaCosechaMes[i].precioPrimera.toString())
        viewHolderMes.txtPrecioSegunda.setText("$"+listaCosechaMes[i].precioSegunda.toString())
        viewHolderMes.txtPrecioTercera.setText("$"+listaCosechaMes[i].precioTercera.toString())
        viewHolderMes.txtPrecioCuarta.setText("$"+listaCosechaMes[i].precioCuarta.toString())
        viewHolderMes.txtPrecioQuinta.setText("$"+listaCosechaMes[i].precioQuinta.toString())
        viewHolderMes.txtPrecioMadura.setText("$"+listaCosechaMes[i].precioMadura.toString())
        viewHolderMes.txtTotal.setText("$"+listaCosechaMes[i].dineroTotal.toString())


    }


    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    override fun getItemCount(): Int {
        //return (listaIngresos.size + listaGastos.size)
        return listaCosechaMes.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    inner class ViewHolderMes(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtFecha : TextView
        var txtTotal : TextView
        //TextView de Informe General
        //----------TextView por mes
        //TextView mes Enero
        var txtLibrasExtra :TextView
        var txtLibrasPrimera :TextView
        var txtLibrasSegunda :TextView
        var txtLibrasTercera :TextView
        var txtLibrasCuarta :TextView
        var txtLibrasQuinta :TextView
        var txtLibrasMadura :TextView
        var txtPrecioExtra :TextView
        var txtPrecioPrimera :TextView
        var txtPrecioSegunda :TextView
        var txtPrecioTercera :TextView
        var txtPrecioCuarta :TextView
        var txtPrecioQuinta :TextView
        var txtPrecioMadura :TextView

        var cardInformeMes: CardView

        init {
            txtFecha = vista.findViewById(R.id.txtFechaCosecha)

            txtLibrasExtra = vista.findViewById(R.id.txtLibraExtra)
            txtLibrasPrimera = vista.findViewById(R.id.txtLibraPrimera)
            txtLibrasSegunda = vista.findViewById(R.id.txtLibraSegunda)
            txtLibrasTercera = vista.findViewById(R.id.txtLibraTercera)
            txtLibrasCuarta = vista.findViewById(R.id.txtLibraCuarta)
            txtLibrasQuinta = vista.findViewById(R.id.txtLibraQuinta)
            txtLibrasMadura = vista.findViewById(R.id.txtLibraMadura)
            txtPrecioExtra = vista.findViewById(R.id.txtPrecioExtra)
            txtPrecioPrimera = vista.findViewById(R.id.txtPrecioPrimera)
            txtPrecioSegunda = vista.findViewById(R.id.txtPrecioSegunda)
            txtPrecioTercera = vista.findViewById(R.id.txtPrecioTercera)
            txtPrecioCuarta = vista.findViewById(R.id.txtPrecioCuarta)
            txtPrecioQuinta = vista.findViewById(R.id.txtPrecioQuinta)
            txtPrecioMadura = vista.findViewById(R.id.txtPrecioMadura)
            txtTotal = vista.findViewById(R.id.txtTotalCosecha)

            cardInformeMes = vista.findViewById(R.id.cardCosechaMes)

        }
    }

    init {
        //this.listaPersona = listaPersona
    }
}

