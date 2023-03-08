package com.example.fresaproyecto.fragments

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.echo.holographlibrary.Bar
import com.echo.holographlibrary.BarGraph
import com.echo.holographlibrary.PieGraph
import com.echo.holographlibrary.PieSlice
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorCosechaMesCultivo
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.BeneficioCultivoVo
import com.example.fresaproyecto.clases.vo.CosechaCultivoVo
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import kotlin.math.roundToInt

class CosechaCultivoFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var vista: View
    lateinit var recyclerCosechaMes: RecyclerView
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var pieGraphMes: PieGraph
    var listaInformeMes: List<BeneficioCultivoVo>? = null

    lateinit var txtExtraLibra: TextView
    lateinit var txtPrimeraLibra: TextView
    lateinit var txtSegundaLibra: TextView
    lateinit var txtTerceraLibra: TextView
    lateinit var txtCuartaLibra: TextView
    lateinit var txtQuintaLibra: TextView
    lateinit var txtMaduraLibra: TextView
    lateinit var layoutCalidad: LinearLayout

    var idCultivo = DialogoGesCultivo.cultivoSeleccionado.id

    var mes = InformeCultivoFragment.fecha.mes
    var año = InformeCultivoFragment.fecha.año


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
        Utilidades.consultarCosechaMes(actividad, mes, año, idCultivo)
        vista = inflater.inflate(R.layout.fragment_cosecha_cultivo, container, false)
        pieGraphMes = vista.findViewById(R.id.graphPie)

        txtExtraLibra = vista.findViewById(R.id.txtExtra)
        txtPrimeraLibra = vista.findViewById(R.id.txtPrimera)
        txtSegundaLibra = vista.findViewById(R.id.txtSegunda)
        txtTerceraLibra = vista.findViewById(R.id.txtTercera)
        txtCuartaLibra = vista.findViewById(R.id.txtCuarta)
        txtQuintaLibra = vista.findViewById(R.id.txtQuinta)
        txtMaduraLibra = vista.findViewById(R.id.txtMadura)
        layoutCalidad = vista.findViewById(R.id.layoutCalidad)
        layoutCalidad.visibility = View.GONE


        recyclerCosechaMes = vista.findViewById(R.id.recyclerCosecha)
        recyclerCosechaMes.layoutManager = LinearLayoutManager(actividad)
        recyclerCosechaMes.setHasFixedSize(true)
        cosechaPorFecha()
        graficarPie()
        return vista
    }

    fun graficarPie() {

        listaInformeMes = Utilidades.listaBeneficioCultivo!!

        mes = mes - 1
        if (listaInformeMes!![mes].ingresos > 0) {
            layoutCalidad.visibility = View.VISIBLE
        }
        for (i in 1..7) {
            var rebanada = PieSlice()
            var color = generarColorHecAleatorio()
            rebanada.color = Color.parseColor(color)
            when (i) {
                1 -> {
                    rebanada.value = listaInformeMes!![mes].extra.toString().toFloat()
                    txtExtraLibra.text = "Extra: " + listaInformeMes!![mes].extra.toString() + "lb"
                    txtExtraLibra.setBackgroundColor(Color.parseColor(color))

                }
                2 -> {
                    rebanada.value = listaInformeMes!![mes].primera.toString().toFloat()
                    txtPrimeraLibra.text =
                        "Primera: " + listaInformeMes!![mes].primera.toString() + "lb"
                    txtPrimeraLibra.setBackgroundColor(Color.parseColor(color))
                }
                3 -> {
                    rebanada.value = listaInformeMes!![mes].segunda.toString().toFloat()
                    txtSegundaLibra.text =
                        "Segunda: " + listaInformeMes!![mes].segunda.toString() + "lb"
                    txtSegundaLibra.setBackgroundColor(Color.parseColor(color))
                }
                4 -> {
                    rebanada.value = listaInformeMes!![mes].tercera.toString().toFloat()
                    txtTerceraLibra.text =
                        "Tercera: " + listaInformeMes!![mes].tercera.toString() + "lb"
                    txtTerceraLibra.setBackgroundColor(Color.parseColor(color))
                }
                5 -> {
                    rebanada.value = listaInformeMes!![mes].cuarta.toString().toFloat()
                    txtCuartaLibra.text =
                        "Cuarta: " + listaInformeMes!![mes].cuarta.toString() + "lb"
                    txtCuartaLibra.setBackgroundColor(Color.parseColor(color))
                }
                6 -> {
                    rebanada.value = listaInformeMes!![mes].quinta.toString().toFloat()
                    txtQuintaLibra.text =
                        "Quinta: " + listaInformeMes!![mes].quinta.toString() + "lb"
                    txtQuintaLibra.setBackgroundColor(Color.parseColor(color))
                }
                7 -> {
                    rebanada.value = listaInformeMes!![mes].madura.toString().toFloat()
                    txtMaduraLibra.text =
                        "Madura FF: " + listaInformeMes!![mes].madura.toString() + "lb"
                    txtMaduraLibra.setBackgroundColor(Color.parseColor(color))
                }
                else -> (0).toFloat()
            }
            pieGraphMes.addSlice(rebanada)
        }
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

    private fun cosechaPorFecha() {
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)

        var miAdaptadorCosecha = AdaptadorCosechaMesCultivo()
        miAdaptadorCosecha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerInformeMes.getChildAdapterPosition(view!!))
            }
        })

        recyclerCosechaMes.adapter = miAdaptadorCosecha
    }

}