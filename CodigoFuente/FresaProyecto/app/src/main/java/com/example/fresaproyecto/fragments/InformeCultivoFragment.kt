package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fresaproyecto.R
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.interfaces.IComunicaFragments


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InformeCultivoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InformeCultivoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    var año : Int = 0
    var mes : Int = 0

    //TextView de año
    lateinit var txtAño1 : TextView
    lateinit var txtAño2 : TextView
    lateinit var txtAño3 : TextView
    lateinit var txtAño4 : TextView
    lateinit var txtAño5 : TextView
    lateinit var txtAño6 : TextView
    lateinit var txtAño7 : TextView
    lateinit var txtAño8 : TextView
    lateinit var txtAño9 : TextView
    lateinit var txtAño10 : TextView
    lateinit var txtAño11 : TextView
    lateinit var txtAño12 : TextView
    //TextView de Informe General
    lateinit var txtIngreso : TextView
    lateinit var txtFecha : TextView
    lateinit var txtGasto : TextView
    lateinit var txtBeneficio : TextView
    //----------TextView por mes
    //TextView mes Enero
    lateinit var txtFechaEnr :TextView
    lateinit var txtGasInsumoEnr :TextView
    lateinit var txtGasJornalEnr :TextView
    lateinit var txtBeneficioEnr :TextView
    lateinit var txtCosechaExtraEnr :TextView
    lateinit var txtCosechaPrimeraEnr :TextView
    lateinit var txtCosechaSegundaEnr :TextView
    lateinit var txtCosechaTerceraEnr :TextView
    lateinit var txtCosechaCuartaEnr :TextView
    lateinit var txtCosechaQuintaEnr :TextView
    lateinit var txtCosechaMaduraEnr :TextView
    //TextView mes Febrero
    lateinit var txtFechaFbr :TextView
    lateinit var txtGasInsumoFbr :TextView
    lateinit var txtGasJornalFbr :TextView
    lateinit var txtBeneficioFbr :TextView
    lateinit var txtCosechaExtraFbr :TextView
    lateinit var txtCosechaPrimeraFbr :TextView
    lateinit var txtCosechaSegundaFbr :TextView
    lateinit var txtCosechaTerceraFbr :TextView
    lateinit var txtCosechaCuartaFbr :TextView
    lateinit var txtCosechaQuintaFbr :TextView
    lateinit var txtCosechaMaduraFbr :TextView
    //TextView mes Marzo
    lateinit var txtFechaMar :TextView
    lateinit var txtGasInsumoMar :TextView
    lateinit var txtGasJornalMar :TextView
    lateinit var txtBeneficioMar :TextView
    lateinit var txtCosechaExtraMar :TextView
    lateinit var txtCosechaPrimeraMar :TextView
    lateinit var txtCosechaSegundaMar :TextView
    lateinit var txtCosechaTerceraMar :TextView
    lateinit var txtCosechaCuartaMar :TextView
    lateinit var txtCosechaQuintaMar :TextView
    lateinit var txtCosechaMaduraMar :TextView
    //TextView mes Abril
    lateinit var txtFechaAbr :TextView
    lateinit var txtGasInsumoAbr :TextView
    lateinit var txtGasJornalAbr :TextView
    lateinit var txtBeneficioAbr :TextView
    lateinit var txtCosechaExtraAbr :TextView
    lateinit var txtCosechaPrimeraAbr :TextView
    lateinit var txtCosechaSegundaAbr :TextView
    lateinit var txtCosechaTerceraAbr :TextView
    lateinit var txtCosechaCuartaAbr :TextView
    lateinit var txtCosechaQuintaAbr :TextView
    lateinit var txtCosechaMaduraAbr :TextView
    //TextView mes Mayo
    lateinit var txtFechaMay :TextView
    lateinit var txtGasInsumoMay :TextView
    lateinit var txtGasJornalMay :TextView
    lateinit var txtBeneficioMay :TextView
    lateinit var txtCosechaExtraMay :TextView
    lateinit var txtCosechaPrimeraMay :TextView
    lateinit var txtCosechaSegundaMay :TextView
    lateinit var txtCosechaTerceraMay :TextView
    lateinit var txtCosechaCuartaMay :TextView
    lateinit var txtCosechaQuintaMay :TextView
    lateinit var txtCosechaMaduraMay :TextView
    //TextView mes Junio
    lateinit var txtFechaJun :TextView
    lateinit var txtGasInsumoJun :TextView
    lateinit var txtGasJornalJun :TextView
    lateinit var txtBeneficioJun :TextView
    lateinit var txtCosechaExtraJun :TextView
    lateinit var txtCosechaPrimeraJun :TextView
    lateinit var txtCosechaSegundaJun :TextView
    lateinit var txtCosechaTerceraJun :TextView
    lateinit var txtCosechaCuartaJun :TextView
    lateinit var txtCosechaQuintaJun :TextView
    lateinit var txtCosechaMaduraJun :TextView
    //TextView mes Julio
    lateinit var txtFechaJul :TextView
    lateinit var txtGasInsumoJul :TextView
    lateinit var txtGasJornalJul :TextView
    lateinit var txtBeneficioJul :TextView
    lateinit var txtCosechaExtraJul :TextView
    lateinit var txtCosechaPrimeraJul :TextView
    lateinit var txtCosechaSegundaJul :TextView
    lateinit var txtCosechaTerceraJul :TextView
    lateinit var txtCosechaCuartaJul :TextView
    lateinit var txtCosechaQuintaJul :TextView
    lateinit var txtCosechaMaduraJul :TextView
    //TextView mes Agosto
    lateinit var txtFechaAgo :TextView
    lateinit var txtGasInsumoAgo :TextView
    lateinit var txtGasJornalAgo :TextView
    lateinit var txtBeneficioAgo :TextView
    lateinit var txtCosechaExtraAgo :TextView
    lateinit var txtCosechaPrimeraAgo :TextView
    lateinit var txtCosechaSegundaAgo :TextView
    lateinit var txtCosechaTerceraAgo :TextView
    lateinit var txtCosechaCuartaAgo :TextView
    lateinit var txtCosechaQuintaAgo :TextView
    lateinit var txtCosechaMaduraAgo :TextView
    //TextView mes Septiembre
    lateinit var txtFechaSep :TextView
    lateinit var txtGasInsumoSep :TextView
    lateinit var txtGasJornalSep :TextView
    lateinit var txtBeneficioSep :TextView
    lateinit var txtCosechaExtraSep :TextView
    lateinit var txtCosechaPrimeraSep :TextView
    lateinit var txtCosechaSegundaSep :TextView
    lateinit var txtCosechaTerceraSep :TextView
    lateinit var txtCosechaCuartaSep :TextView
    lateinit var txtCosechaQuintaSep :TextView
    lateinit var txtCosechaMaduraSep :TextView
    //TextView mes Octubre
    lateinit var txtFechaOct :TextView
    lateinit var txtGasInsumoOct :TextView
    lateinit var txtGasJornalOct :TextView
    lateinit var txtBeneficioOct :TextView
    lateinit var txtCosechaExtraOct :TextView
    lateinit var txtCosechaPrimeraOct :TextView
    lateinit var txtCosechaSegundaOct :TextView
    lateinit var txtCosechaTerceraOct :TextView
    lateinit var txtCosechaCuartaOct :TextView
    lateinit var txtCosechaQuintaOct :TextView
    lateinit var txtCosechaMaduraOct :TextView
    //TextView mes Noviembre
    lateinit var txtFechaNov :TextView
    lateinit var txtGasInsumoNov :TextView
    lateinit var txtGasJornalNov :TextView
    lateinit var txtBeneficioNov :TextView
    lateinit var txtCosechaExtraNov :TextView
    lateinit var txtCosechaPrimeraNov :TextView
    lateinit var txtCosechaSegundaNov :TextView
    lateinit var txtCosechaTerceraNov :TextView
    lateinit var txtCosechaCuartaNov :TextView
    lateinit var txtCosechaQuintaNov :TextView
    lateinit var txtCosechaMaduraNov :TextView
    //TextView mes Diciembre
    lateinit var txtFechaDic :TextView
    lateinit var txtGasInsumoDic :TextView
    lateinit var txtGasJornalDic :TextView
    lateinit var txtBeneficioDic :TextView
    lateinit var txtCosechaExtraDic :TextView
    lateinit var txtCosechaPrimeraDic :TextView
    lateinit var txtCosechaSegundaDic :TextView
    lateinit var txtCosechaTerceraDic :TextView
    lateinit var txtCosechaCuartaDic :TextView
    lateinit var txtCosechaQuintaDic :TextView
    lateinit var txtCosechaMaduraDic :TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity) {
            actividad = context
            interfaceComunicaFragments = actividad as IComunicaFragments
        }
    }

    fun onDateSelected (day:Int, month:Int, year:Int){
        txtFecha.setText("$year-$month-$day")
        mes=month
        año=year
    }

    private fun showDatePickerDialog() {
        val datePicker = DatePickerFragment{day, month, year -> onDateSelected(year, month+1, day)}
        datePicker.show(parentFragmentManager, "datePicker")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_informe_cultivo, container, false)
        txtAño1 = vista.findViewById(R.id.txtAño1)
        txtAño2 = vista.findViewById(R.id.txtAño2)
        txtAño3 = vista.findViewById(R.id.txtAño3)
        txtAño4 = vista.findViewById(R.id.txtAño4)
        txtAño5 = vista.findViewById(R.id.txtAño5)
        txtAño6 = vista.findViewById(R.id.txtAño6)
        txtAño7 = vista.findViewById(R.id.txtAño7)
        txtAño8 = vista.findViewById(R.id.txtAño8)
        txtAño9 = vista.findViewById(R.id.txtAño9)
        txtAño10 = vista.findViewById(R.id.txtAño10)
        txtAño11 = vista.findViewById(R.id.txtAño11)
        txtAño12 = vista.findViewById(R.id.txtAño12)

        txtIngreso = vista.findViewById(R.id.txtValorIngreso)
        txtFecha = vista.findViewById(R.id.txtFechaInf)
        txtGasto = vista.findViewById(R.id.txtValorGasto)
        txtBeneficio = vista.findViewById(R.id.txtBeneficio)

        txtFechaEnr = vista.findViewById(R.id.txtAño1)
        txtGasInsumoEnr = vista.findViewById(R.id.txtInsumo)
        txtGasJornalEnr = vista.findViewById(R.id.txtJornal)
        txtBeneficioEnr = vista.findViewById(R.id.txtBeneficioEnr)
        txtCosechaExtraEnr = vista.findViewById(R.id.txtPrecioExtra)
        txtCosechaPrimeraEnr = vista.findViewById(R.id.txtPrecioPrimera)
        txtCosechaSegundaEnr = vista.findViewById(R.id.txtPrecioSegunda)
        txtCosechaTerceraEnr = vista.findViewById(R.id.txtPrecioTercera)
        txtCosechaCuartaEnr = vista.findViewById(R.id.txtPrecioCuarta)
        txtCosechaQuintaEnr = vista.findViewById(R.id.txtPrecioQuinta)
        txtCosechaMaduraEnr = vista.findViewById(R.id.txtPrecioMadura)

        txtFechaFbr = vista.findViewById(R.id.txtAño2)
        txtGasInsumoFbr = vista.findViewById(R.id.txtInsumoFeb)
        txtGasJornalFbr = vista.findViewById(R.id.txtJornalFeb)
        txtBeneficioFbr = vista.findViewById(R.id.txtBeneficioFeb)
        txtCosechaExtraFbr = vista.findViewById(R.id.txtPrecioExtraFeb)
        txtCosechaPrimeraFbr = vista.findViewById(R.id.txtPrecioPrimeraFeb)
        txtCosechaSegundaFbr = vista.findViewById(R.id.txtPrecioSegundaFeb)
        txtCosechaTerceraFbr = vista.findViewById(R.id.txtPrecioTerceraFeb)
        txtCosechaCuartaFbr = vista.findViewById(R.id.txtPrecioCuartaFeb)
        txtCosechaQuintaFbr = vista.findViewById(R.id.txtPrecioQuintaFeb)
        txtCosechaMaduraFbr = vista.findViewById(R.id.txtPrecioMaduraFeb)

        txtFechaMar = vista.findViewById(R.id.txtAño3)
        txtGasInsumoMar = vista.findViewById(R.id.txtInsumoMar)
        txtGasJornalMar = vista.findViewById(R.id.txtJornalMar)
        txtBeneficioMar = vista.findViewById(R.id.txtBeneficioMar)
        txtCosechaExtraMar = vista.findViewById(R.id.txtPrecioExtraMar)
        txtCosechaPrimeraMar = vista.findViewById(R.id.txtPrecioPrimeraMar)
        txtCosechaSegundaMar = vista.findViewById(R.id.txtPrecioSegundaMar)
        txtCosechaTerceraMar = vista.findViewById(R.id.txtPrecioTerceraMar)
        txtCosechaCuartaMar = vista.findViewById(R.id.txtPrecioCuartaMar)
        txtCosechaQuintaMar = vista.findViewById(R.id.txtPrecioQuintaMar)
        txtCosechaMaduraMar = vista.findViewById(R.id.txtPrecioMaduraMar)

        txtFechaAbr = vista.findViewById(R.id.txtAño4)
        txtGasInsumoAbr = vista.findViewById(R.id.txtInsumoAbr)
        txtGasJornalAbr = vista.findViewById(R.id.txtJornalAbr)
        txtBeneficioAbr = vista.findViewById(R.id.txtBeneficioAbr)
        txtCosechaExtraAbr = vista.findViewById(R.id.txtPrecioExtraAbr)
        txtCosechaPrimeraAbr = vista.findViewById(R.id.txtPrecioPrimeraAbr)
        txtCosechaSegundaAbr = vista.findViewById(R.id.txtPrecioSegundaAbr)
        txtCosechaTerceraAbr = vista.findViewById(R.id.txtPrecioTerceraAbr)
        txtCosechaCuartaAbr = vista.findViewById(R.id.txtPrecioCuartaAbr)
        txtCosechaQuintaAbr = vista.findViewById(R.id.txtPrecioQuintaAbr)
        txtCosechaMaduraAbr = vista.findViewById(R.id.txtPrecioMaduraAbr)

        txtFechaMay = vista.findViewById(R.id.txtAño5)
        txtGasInsumoMay = vista.findViewById(R.id.txtInsumoMay)
        txtGasJornalMay = vista.findViewById(R.id.txtJornalMay)
        txtBeneficioMay = vista.findViewById(R.id.txtBeneficioMay)
        txtCosechaExtraMay = vista.findViewById(R.id.txtPrecioExtraMay)
        txtCosechaPrimeraMay = vista.findViewById(R.id.txtPrecioPrimeraMay)
        txtCosechaSegundaMay = vista.findViewById(R.id.txtPrecioSegundaMay)
        txtCosechaTerceraMay = vista.findViewById(R.id.txtPrecioTerceraMay)
        txtCosechaCuartaMay = vista.findViewById(R.id.txtPrecioCuartaMay)
        txtCosechaQuintaMay = vista.findViewById(R.id.txtPrecioQuintaMay)
        txtCosechaMaduraMay = vista.findViewById(R.id.txtPrecioMaduraMay)

        txtFechaJun = vista.findViewById(R.id.txtAño6)
        txtGasInsumoJun = vista.findViewById(R.id.txtInsumoJun)
        txtGasJornalJun = vista.findViewById(R.id.txtJornalJun)
        txtBeneficioJun = vista.findViewById(R.id.txtBeneficioJun)
        txtCosechaExtraJun = vista.findViewById(R.id.txtPrecioExtraJun)
        txtCosechaPrimeraJun = vista.findViewById(R.id.txtPrecioPrimeraJun)
        txtCosechaSegundaJun = vista.findViewById(R.id.txtPrecioSegundaJun)
        txtCosechaTerceraJun = vista.findViewById(R.id.txtPrecioTerceraJun)
        txtCosechaCuartaJun = vista.findViewById(R.id.txtPrecioCuartaJun)
        txtCosechaQuintaJun = vista.findViewById(R.id.txtPrecioQuintaJun)
        txtCosechaMaduraJun = vista.findViewById(R.id.txtPrecioMaduraJun)

        txtFechaJul = vista.findViewById(R.id.txtAño7)
        txtGasInsumoJul = vista.findViewById(R.id.txtInsumoJul)
        txtGasJornalJul = vista.findViewById(R.id.txtJornalJul)
        txtBeneficioJul = vista.findViewById(R.id.txtBeneficioJul)
        txtCosechaExtraJul = vista.findViewById(R.id.txtPrecioExtraJul)
        txtCosechaPrimeraJul = vista.findViewById(R.id.txtPrecioPrimeraJul)
        txtCosechaSegundaJul = vista.findViewById(R.id.txtPrecioSegundaJul)
        txtCosechaTerceraJul = vista.findViewById(R.id.txtPrecioTerceraJul)
        txtCosechaCuartaJul = vista.findViewById(R.id.txtPrecioCuartaJul)
        txtCosechaQuintaJul = vista.findViewById(R.id.txtPrecioQuintaJul)
        txtCosechaMaduraJul = vista.findViewById(R.id.txtPrecioMaduraJul)

        txtFechaAgo = vista.findViewById(R.id.txtAño8)
        txtGasInsumoAgo = vista.findViewById(R.id.txtInsumoAgo)
        txtGasJornalAgo = vista.findViewById(R.id.txtJornalAgo)
        txtBeneficioAgo = vista.findViewById(R.id.txtBeneficioAgo)
        txtCosechaExtraAgo = vista.findViewById(R.id.txtPrecioExtraAgo)
        txtCosechaPrimeraAgo = vista.findViewById(R.id.txtPrecioPrimeraAgo)
        txtCosechaSegundaAgo = vista.findViewById(R.id.txtPrecioSegundaAgo)
        txtCosechaTerceraAgo = vista.findViewById(R.id.txtPrecioTerceraAgo)
        txtCosechaCuartaAgo = vista.findViewById(R.id.txtPrecioCuartaAgo)
        txtCosechaQuintaAgo = vista.findViewById(R.id.txtPrecioQuintaAgo)
        txtCosechaMaduraAgo = vista.findViewById(R.id.txtPrecioMaduraAgo)

        txtFechaSep = vista.findViewById(R.id.txtAño9)
        txtGasInsumoSep = vista.findViewById(R.id.txtInsumoSep)
        txtGasJornalSep = vista.findViewById(R.id.txtJornalSep)
        txtBeneficioSep = vista.findViewById(R.id.txtBeneficioSep)
        txtCosechaExtraSep = vista.findViewById(R.id.txtPrecioExtraSep)
        txtCosechaPrimeraSep = vista.findViewById(R.id.txtPrecioPrimeraSep)
        txtCosechaSegundaSep = vista.findViewById(R.id.txtPrecioSegundaSep)
        txtCosechaTerceraSep = vista.findViewById(R.id.txtPrecioTerceraSep)
        txtCosechaCuartaSep = vista.findViewById(R.id.txtPrecioCuartaSep)
        txtCosechaQuintaSep = vista.findViewById(R.id.txtPrecioQuintaSep)
        txtCosechaMaduraSep = vista.findViewById(R.id.txtPrecioMaduraSep)

        txtFechaOct = vista.findViewById(R.id.txtAño10)
        txtGasInsumoOct = vista.findViewById(R.id.txtInsumoOct)
        txtGasJornalOct = vista.findViewById(R.id.txtJornalOct)
        txtBeneficioOct = vista.findViewById(R.id.txtBeneficioOct)
        txtCosechaExtraOct = vista.findViewById(R.id.txtPrecioExtraOct)
        txtCosechaPrimeraOct = vista.findViewById(R.id.txtPrecioPrimeraOct)
        txtCosechaSegundaOct = vista.findViewById(R.id.txtPrecioSegundaOct)
        txtCosechaTerceraOct = vista.findViewById(R.id.txtPrecioTerceraOct)
        txtCosechaCuartaOct = vista.findViewById(R.id.txtPrecioCuartaOct)
        txtCosechaQuintaOct = vista.findViewById(R.id.txtPrecioQuintaOct)
        txtCosechaMaduraOct = vista.findViewById(R.id.txtPrecioMaduraOct)

        txtFechaNov = vista.findViewById(R.id.txtAño11)
        txtGasInsumoNov = vista.findViewById(R.id.txtInsumoNov)
        txtGasJornalNov = vista.findViewById(R.id.txtJornalNov)
        txtBeneficioNov = vista.findViewById(R.id.txtBeneficioNov)
        txtCosechaExtraNov = vista.findViewById(R.id.txtPrecioExtraNov)
        txtCosechaPrimeraNov = vista.findViewById(R.id.txtPrecioPrimeraNov)
        txtCosechaSegundaNov = vista.findViewById(R.id.txtPrecioSegundaNov)
        txtCosechaTerceraNov = vista.findViewById(R.id.txtPrecioTerceraNov)
        txtCosechaCuartaNov = vista.findViewById(R.id.txtPrecioCuartaNov)
        txtCosechaQuintaNov = vista.findViewById(R.id.txtPrecioQuintaNov)
        txtCosechaMaduraNov = vista.findViewById(R.id.txtPrecioMaduraNov)

        txtFechaDic = vista.findViewById(R.id.txtAño12)
        txtGasInsumoDic = vista.findViewById(R.id.txtInsumoDic)
        txtGasJornalDic = vista.findViewById(R.id.txtJornalDic)
        txtBeneficioDic = vista.findViewById(R.id.txtBeneficioDic)
        txtCosechaExtraDic = vista.findViewById(R.id.txtPrecioExtraDic)
        txtCosechaPrimeraDic = vista.findViewById(R.id.txtPrecioPrimeraDic)
        txtCosechaSegundaDic = vista.findViewById(R.id.txtPrecioSegundaDic)
        txtCosechaTerceraDic = vista.findViewById(R.id.txtPrecioTerceraDic)
        txtCosechaCuartaDic = vista.findViewById(R.id.txtPrecioCuartaDic)
        txtCosechaQuintaDic = vista.findViewById(R.id.txtPrecioQuintaDic)
        txtCosechaMaduraDic = vista.findViewById(R.id.txtPrecioMaduraDic)

        txtFecha.setOnClickListener{ showDatePickerDialog() }

        informePorFecha(año,mes)

        return vista
    }

    private fun informePorFecha(año : Int, mes : Int){
        
    }

    private fun informeEnero(){

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment InformeCultivoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InformeCultivoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}