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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorMesCultivo
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.BeneficioCultivoVo
import com.example.fresaproyecto.clases.vo.CultivoVo
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
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
    //var beneficioCultivo: BeneficioCultivoVo = Utilidades.beneficioCultivo!!

    //TextView de año
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

        txtIngreso = vista.findViewById(R.id.txtValorIngreso)
        txtFecha = vista.findViewById(R.id.txtFechaInf)
        txtGasto = vista.findViewById(R.id.txtValorGasto)
        txtBeneficio = vista.findViewById(R.id.txtBeneficio)

        txtFecha.setOnClickListener{ showDatePickerDialog() }

        recyclerInformeMes = vista.findViewById(R.id.recyclerInformeMes)
        recyclerInformeMes.layoutManager = LinearLayoutManager(actividad)
        recyclerInformeMes.setHasFixedSize(true)

        informePorFecha(año,mes)

        return vista
    }


    private fun informePorFecha(año : Int, mes : Int){
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.calcularBeneficioCultivo(actividad,1, 2023)

        txtFecha.setText(""+mes+"."+año)
        println("Lista de Beneficios: "+Utilidades.listaBeneficioCultivo!![0].beneficio.toString())
        txtBeneficio.setText(Utilidades.listaBeneficioCultivo!![0].beneficio.toString())
        txtIngreso.setText(Utilidades.listaBeneficioCultivo!![0].ingresos.toString())
        txtGasto.setText(Utilidades.listaBeneficioCultivo!![0].gastos.toString())

        var miAdaptadorInforme = AdaptadorMesCultivo(Utilidades.listaBeneficioCultivo!!)
        miAdaptadorInforme.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerInformeMes.getChildAdapterPosition(view!!))
            }
        })

        recyclerInformeMes.adapter=miAdaptadorInforme
    }

    private fun informeEnero(){

    }

    companion object {
        var mesSeleccionado: Int = 0
        lateinit var recyclerInformeMes: RecyclerView

        /*fun llenarAdaptadorInformeMes() {

            Utilidades.consultarListaCultivos(DialogoGesCultivo.actividad)

            //se asigna la lista de jugadores por defecto
            var miAdaptadorInforme = AdaptadorInforme(Utilidades.listaCultivos!!)
            miAdaptadorInforme.setOnClickListener(object : View.OnClickListener {
                override fun onClick(view: View?) {
                    DialogoGesCultivo.mesSeleccionado = Utilidades.listaCultivos!!.get(
                        DialogoGesCultivo.recyclerInformeMes.getChildAdapterPosition(view!!))

                    println("Nombre: " + DialogoGesCultivo.mesSeleccionado.nombre)
                }
            })

            DialogoGesCultivo.recyclerInformeMes.adapter=miAdaptadorInforme
        }
        
         */
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