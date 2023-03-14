package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
import com.example.fresaproyecto.adapters.AdaptadorMesPersona
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.BeneficioCultivoVo
import com.example.fresaproyecto.clases.vo.BeneficioPersonalVo
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

class InformePersonalFragment : Fragment() {
    lateinit var vista: View
    lateinit var actividad: Activity

    var año: Int = 0
    var mes: Int = 0
    var idPersona = DialogoGesPersona.personaSeleccionada.id

    //TextView de año
    //TextView de Informe General
    lateinit var txtFechaSelec: TextView
    var listaInformeMes: ArrayList<BeneficioPersonalVo>? = null
    var puntos = ArrayList<Bar>()
    lateinit var btnAtras : ImageButton

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
        vista = inflater.inflate(R.layout.fragment_informe_personal, container, false)
        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())
        añoSeleccionado = añoActual.toInt()
        txtFechaSelec = vista.findViewById(R.id.txtFecha)
        txtFechaSelec.setOnClickListener { monthYear() }
        btnAtras = vista.findViewById(R.id.btnIcoAtras)
        barGraphMes = vista.findViewById(R.id.graphBar)
        recyclerInformeMes = vista.findViewById(R.id.recyclerInformeMes)
        recyclerInformeMes.layoutManager = LinearLayoutManager(actividad)
        recyclerInformeMes.setHasFixedSize(true)

        eventosClick()
        graficarBarras()
        informePorFecha(añoActual.toInt())

        return vista
    }

    fun eventosClick(){
        btnAtras.setOnClickListener { requireActivity().onBackPressed() }
    }

    fun monthYear() {
        var today: Calendar = Calendar.getInstance()
        val builder = MonthPickerDialog.Builder(
            actividad,
            { selectedMonth, selectedYear ->
                txtFechaSelec.setText("" + selectedYear)
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
        Utilidades.calcularBeneficioPersonal(actividad, añoSeleccionado, idPersona)

        listaInformeMes = Utilidades.listaBeneficioPersonal!!

        puntos.clear()
        for (i in listaInformeMes!!) {
            val barra = Bar()
            var color = generarColorHecAleatorio()
            barra.color = Color.parseColor(color)
            var mesLetras = when (i.mes) {
                1 -> "ENERO"
                2 -> "FEBRERO"
                3 -> "MARZO"
                4 -> "ABRIL"
                5 -> "MAYO"
                6 -> "JUNIO"
                7 -> "JULIO"
                8 -> "AGOSTO"
                9 -> "SEPTIEMBRE"
                10 -> "OCTUBRE"
                11 -> "NOVIEMNRE"
                12 -> "DICIEMBRE"
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
        Utilidades.calcularBeneficioPersonal(actividad, año, idPersona)

        listaInformeMes = Utilidades.listaBeneficioPersonal!!


        var miAdaptadorInforme = AdaptadorMesPersona()
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
        lateinit var fecha: BeneficioPersonalVo
        lateinit var recyclerInformeMes: RecyclerView
        lateinit var barGraphMes: BarGraph
        lateinit var interfaceComunicaFragments: IComunicaFragments

        fun cambiarFragment(mes: Int) {
            mesSeleccionado = mes
            interfaceComunicaFragments.resultadoMensual()
        }

    }
}