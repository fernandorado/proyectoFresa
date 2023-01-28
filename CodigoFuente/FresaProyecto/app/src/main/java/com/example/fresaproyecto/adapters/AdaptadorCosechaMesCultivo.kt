package com.example.fresaproyecto.adapters



import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.MainActivity
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.CosechaCultivoVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.io.ByteArrayInputStream


class AdaptadorCosechaMesCultivo(listaMesCultivo: List<CosechaCultivoVo>) :
    RecyclerView.Adapter<AdaptadorCosechaMesCultivo.ViewHolderMes>(), View.OnClickListener {
    private var listener: View.OnClickListener? = null
    var listaCosechaMes: List<CosechaCultivoVo> = Utilidades.listaCosechaCultivo!!
    lateinit var vista: View
    lateinit var vistaImagen: View
    lateinit var imgFactura: ImageView
    lateinit var bitmap: Bitmap
    lateinit var viewGroup2: ViewGroup

    lateinit var context: Context
    lateinit var actividad: Activity
    var identificacion : Int = 0
    var nombre : String = ""
    lateinit var interfaceComunicaFragments: IComunicaFragments
    var d: AlertDialog? = null
    lateinit var builder: AlertDialog
    var posicionMarcada: Int = 0

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolderMes {

        viewGroup2 = viewGroup
        context = viewGroup.context


        vista = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_list_cosecha_mes_cultivo, viewGroup, false)
        vistaImagen = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.imagen, viewGroup, false)
        imgFactura = vistaImagen.findViewById(R.id.imgFactura)
        viewGroup.removeView(vista)
        viewGroup.removeView(vistaImagen)
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

        viewHolderMes.txtVerFactura.setOnClickListener{
            val blob: ByteArray = listaCosechaMes[i].imgFactura.inputStream().readBytes()
            val bais = ByteArrayInputStream(blob)
            bitmap = BitmapFactory.decodeStream(bais)
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("Factura")
                .setPositiveButton("Cerrar") { dialog, _ ->
                    dialog.dismiss()
                    viewGroup2.removeView(vista)
                    viewGroup2.removeView(vistaImagen)
                }
            var titulo:AlertDialog = builder.create()
            imgFactura.setImageBitmap(bitmap)
            titulo.setView(vistaImagen)
            titulo.show()
        }

    }

    /*fun mostrarDialogo(bitmap: Bitmap) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Factura")
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
        builder.show()
        imgFactura.setImageBitmap(bitmap)
    }*/

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
        var txtVerFactura :TextView


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
            txtVerFactura = vista.findViewById(R.id.txtVerFactura)



            cardInformeMes = vista.findViewById(R.id.cardCosechaMes)

        }
    }

    init {
        //this.listaPersona = listaPersona
    }
}

