package com.misRegistros.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demogorgorn.monthpicker.MonthPickerDialog
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import com.google.android.material.snackbar.Snackbar
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import com.misRegistros.R
import com.misRegistros.adapters.AdaptadorMesCultivo
import com.misRegistros.clases.Utilidades
import com.misRegistros.clases.vo.BeneficioCultivoVo
import com.misRegistros.dialogos.DialogoGesCultivo
import com.misRegistros.interfaces.IComunicaFragments
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt


class InformeCultivoFragment : Fragment() {
    lateinit var vista: View
    lateinit var actividad: Activity

    var año: Int = 0
    var mes: Int = 0
    var idCultivo = DialogoGesCultivo.cultivoSeleccionado.id
    var nombre = DialogoGesCultivo.cultivoSeleccionado.nombre
    var cantidadPlantas = DialogoGesCultivo.cultivoSeleccionado.cantidad
    lateinit var btnIcoAtras : ImageButton

    //TextView de año
    //TextView de Informe General
    lateinit var requestPermissionLaunquer: ActivityResultLauncher<String>
    lateinit var txtFechaSelec: TextView
    lateinit var idBtnGuardarPDF: Button


    var listaInformeMes: ArrayList<BeneficioCultivoVo>? = null
    var puntos = ArrayList<Bar>()
    //----------TextView por mes
    //TextView mes Enero

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
        btnIcoAtras = vista.findViewById(R.id.btnIcoAtras)
        idBtnGuardarPDF = vista.findViewById(R.id.idBtnGuardarPDF)
        recyclerInformeMes = vista.findViewById(R.id.recyclerInformeMes)
        //recyclerInformeMes.layoutManager = GridLayoutManager(context, 2)
        recyclerInformeMes.layoutManager = LinearLayoutManager(actividad)
        recyclerInformeMes.setHasFixedSize(true)

        requestPermissionLaunquer = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isAceptado ->
            if (isAceptado) Toast.makeText(context, "PERMISOS CONCEDIDOS", Toast.LENGTH_SHORT).show()
            else Toast.makeText(context, "PERMISOS DENEGADOS", Toast.LENGTH_SHORT).show()
        }

        eventosClick()
        graficarBarras()
        informePorFecha(añoActual.toInt())

        return vista
    }

    private fun eventosClick() {
        idBtnGuardarPDF.setOnClickListener { verificarPermisos(it) }
        btnIcoAtras.setOnClickListener { requireActivity().onBackPressed() }
    }

    private fun verificarPermisos(view: View) {
        when {
            ContextCompat.checkSelfPermission(
                actividad,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
                    == PackageManager.PERMISSION_GRANTED -> {
                crearPDF()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                actividad, android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) -> {
                Snackbar.make(
                    view,
                    "ESTE PERMISO ES NECESARIO PARA CONTINUAR",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("OK") {
                    requestPermissionLaunquer.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }.show()
            }
            else -> {
                requestPermissionLaunquer.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            }
        }
    }

    private fun crearPDF() {
        listaInformeMes = Utilidades.listaBeneficioCultivo!!
        var path = Environment.getExternalStorageDirectory().absolutePath + "/InformeCultivo"
        val dir = File(path)
        if (!dir.exists()) {
            dir.mkdirs()
        }

        val file = File(dir, "informe_" + nombre + "_" + añoSeleccionado + ".pdf")
        val fileOutputStream = FileOutputStream(file)
        val documento = Document()
        PdfWriter.getInstance(documento, fileOutputStream)
        documento.open()
        val titulo = Paragraph(
            "Informe del Cultivo:\n" + nombre + " para el año " + añoSeleccionado + "\n"+"Cantidad de plantas: "+cantidadPlantas+"\n\n",
            FontFactory.getFont("arial", 13f, Font.BOLD, BaseColor.BLACK)
        )
        val salto = Paragraph(
            "\n",
            FontFactory.getFont("arial", 22f, Font.BOLD, BaseColor.BLUE)
        )
        documento.add(titulo)

        var tablaInforme = PdfPTable(4)
        val año = Paragraph(
            "FECHA",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.RED)
        )
        val ingreso = Paragraph(
            "INGRESO",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.RED)
        )
        val gasto = Paragraph(
            "GASTO",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.RED)
        )
        val beneficio = Paragraph(
            "BENEFICIO",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.RED)
        )
        val insumos = Paragraph(
            "INSUMOS",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.MAGENTA)
        )
        val jornales = Paragraph(
            "JORNALES",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.DARK_GRAY)
        )
        val extra = Paragraph(
            "EXTRA Lb",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.ORANGE)
        )
        val primera = Paragraph(
            "PRIMERA Lb",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.ORANGE)
        )
        val segunda = Paragraph(
            "SEGUNDA Lb",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.ORANGE)
        )
        val tercera = Paragraph(
            "TERCERA Lb",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.ORANGE)
        )
        val cuarta = Paragraph(
            "CUARTA Lb",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.ORANGE)
        )
        val quinta = Paragraph(
            "QUINTA Lb",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.ORANGE)
        )
        val madura = Paragraph(
            "MADURA Lb",
            FontFactory.getFont("arial", 11f, Font.BOLD, BaseColor.ORANGE)
        )


        for (item in listaInformeMes!!) {
            tablaInforme.addCell(año)
            tablaInforme.addCell(ingreso)
            tablaInforme.addCell(gasto)
            tablaInforme.addCell(beneficio)
            var mesLetras = when (item.mes) {
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
            tablaInforme.addCell(mesLetras + "/" + item.año)
            tablaInforme.addCell("$" + item.ingresos.toString())
            tablaInforme.addCell("$" + item.gastos.toString())
            tablaInforme.addCell("$" + item.beneficio.toString())

            //TABLA DE GASTOS
            var tablaGastos = PdfPTable(2)
            tablaGastos.addCell(insumos)
            tablaGastos.addCell(jornales)

            tablaGastos.addCell("$" + item.gastoInsumo.toString())
            tablaGastos.addCell("$" + item.gastoJornal.toString())

            //TABLA DE INGRESOS
            var tablaCosecha = PdfPTable(7)
            tablaCosecha.addCell(extra)
            tablaCosecha.addCell(primera)
            tablaCosecha.addCell(segunda)
            tablaCosecha.addCell(tercera)
            tablaCosecha.addCell(cuarta)
            tablaCosecha.addCell(quinta)
            tablaCosecha.addCell(madura)

            tablaCosecha.addCell(item.extra.toString())
            tablaCosecha.addCell(item.primera.toString())
            tablaCosecha.addCell(item.segunda.toString())
            tablaCosecha.addCell(item.tercera.toString())
            tablaCosecha.addCell(item.cuarta.toString())
            tablaCosecha.addCell(item.quinta.toString())
            tablaCosecha.addCell(item.madura.toString())

            documento.add(tablaInforme)
            tablaInforme.deleteLastRow()
            tablaInforme.deleteBodyRows()
            documento.add(tablaGastos)
            documento.add(tablaCosecha)
            documento.add(salto)
        }

        documento.close()
        Toast.makeText(actividad, "PDF Guardado en su Dispositivo", Toast.LENGTH_SHORT).show()
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
            .setMinYear(2022)
            .setActivatedYear(today.get(Calendar.YEAR))
            .setMaxYear(2100)
            .setTitle("Seleccione el Año")
            .showYearOnly()
            .build().show()


    }

    fun graficarBarras() {
        Utilidades.calcularBeneficioCultivo(actividad, añoSeleccionado, idCultivo)

        listaInformeMes = Utilidades.listaBeneficioCultivo!!

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
        Utilidades.calcularBeneficioCultivo(actividad, año, idCultivo)
        listaInformeMes = Utilidades.listaBeneficioCultivo!!

        var miAdaptadorInforme = AdaptadorMesCultivo()

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
    }
}