package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demogorgorn.monthpicker.MonthPickerDialog
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import com.example.fresaproyecto.MainActivity
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorMesCultivo
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.BeneficioCultivoVo
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


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

    var año: Int = 0
    var mes: Int = 0
    var idCultivo = DialogoGesCultivo.cultivoSeleccionado.id

    //TextView de año
    //TextView de Informe General
    lateinit var txtFechaSelec: TextView
    var listaInformeMes: ArrayList<BeneficioCultivoVo>? = null
    var puntos = ArrayList<Bar>()
    //----------TextView por mes
    //TextView mes Enero

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


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_informe_cultivo, container, false)
        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        añoSeleccionado = añoActual.toInt()

        txtFechaSelec = vista.findViewById(R.id.txtFecha)

        txtFechaSelec.setOnClickListener { monthYear() }

        barGraphMes = vista.findViewById(R.id.graphBar)

        recyclerInformeMes = vista.findViewById(R.id.recyclerInformeMes)
        recyclerInformeMes.layoutManager = LinearLayoutManager(actividad)
        recyclerInformeMes.setHasFixedSize(true)

        graficarBarras()
        informePorFecha(añoActual.toInt())

        return vista
    }

    fun monthYear(){
        var today :Calendar = Calendar.getInstance()
        val builder = MonthPickerDialog.Builder(actividad,
            { selectedMonth, selectedYear ->
                txtFechaSelec.setText(""+selectedYear)
                añoSeleccionado = selectedYear
                graficarBarras()
                informePorFecha(selectedYear)
            }, today.get(Calendar.YEAR), today.get(Calendar.MONTH)
        )

        builder.setActivatedMonth(Calendar.JULY)
            .setMinYear(2023)
            .setActivatedYear(today.get(Calendar.YEAR))
            .setMaxYear(2100)
            .setTitle("Seleccione el Año")
            .showYearOnly()
            .build().show()


    }

    fun graficarBarras() {
        Utilidades.calcularBeneficioCultivo(actividad, añoSeleccionado, idCultivo)

        listaInformeMes= Utilidades.listaBeneficioCultivo!!

        puntos.clear()
        for (i in listaInformeMes!!) {
            val barra = Bar()
            var color = generarColorHecAleatorio()
            barra.color = Color.parseColor(color)
            var mesLetras = when (i.mes) {
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
            barra.name = mesLetras
            barra.value = i.beneficio.toString().toFloat()
            puntos.add(barra)
        }
        barGraphMes.bars = puntos
    }

    fun generarColorHecAleatorio(): String {
        val letras =
            arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F")
        var color = "#"
        for (i in 0..5) {
            color += letras[(Math.random() * 15).roundToInt()]
        }
        return color

    }

    private fun informePorFecha(año: Int) {
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.calcularBeneficioCultivo(actividad,año, idCultivo)

        listaInformeMes= Utilidades.listaBeneficioCultivo!!

        println("Lista de Beneficios: " + Utilidades.listaBeneficioCultivo!![0].beneficio.toString())

        var miAdaptadorInforme = AdaptadorMesCultivo(Utilidades.listaBeneficioCultivo!!)
        miAdaptadorInforme.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerInformeMes.getChildAdapterPosition(view!!))
            }
        })

        recyclerInformeMes.adapter = miAdaptadorInforme
    }


    companion object {
        var mesSeleccionado: Int = 0
        var añoSeleccionado: Int = 0
        lateinit var fecha: BeneficioCultivoVo
        lateinit var recyclerInformeMes: RecyclerView
        lateinit var barGraphMes: BarGraph
        lateinit var interfaceComunicaFragments: IComunicaFragments

        fun cambiarFragment(mes: Int) {
            mesSeleccionado = mes
            interfaceComunicaFragments.resultadoMensualCultivo()
        }

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