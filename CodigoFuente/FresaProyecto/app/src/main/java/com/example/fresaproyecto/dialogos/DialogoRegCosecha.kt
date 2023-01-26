package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorCosechaMesCultivo
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DialogoRegCosecha.newInstance] factory method to
 * create an instance of this fragment.
 */
class DialogoRegCosecha : DialogFragment() {
    // TODO: Rename and change types of parameters

    var progreso: ProgressDialog? = null

    lateinit var vista: View
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnGuardar: Button
    lateinit var fabAtras: ImageButton
    lateinit var btnExtra: Button
    lateinit var btnPrimera: Button
    lateinit var btnSegunda: Button
    lateinit var btnTercera: Button
    lateinit var btnCuarta: Button
    lateinit var btnQuinta: Button
    lateinit var btnMadura: Button
    lateinit var btnOkExtra: Button
    lateinit var btnOkPrimera: Button
    lateinit var btnOkSegunda: Button
    lateinit var btnOkTercera: Button
    lateinit var btnOkCuarta: Button
    lateinit var btnOkQuinta: Button
    lateinit var btnOkMadura: Button
    lateinit var layoutExtra: LinearLayout
    lateinit var layoutPrimera: LinearLayout
    lateinit var layoutSegunda: LinearLayout
    lateinit var layoutTercera: LinearLayout
    lateinit var layoutCuarta: LinearLayout
    lateinit var layoutQuinta: LinearLayout
    lateinit var layoutMadura: LinearLayout
    lateinit var campoLibrasExtra: EditText
    lateinit var campoPrecioExtra: EditText
    lateinit var campoLibrasPrimera: EditText
    lateinit var campoPrecioPrimera: EditText
    lateinit var campoLibrasSegunda: EditText
    lateinit var campoPrecioSegunda: EditText
    lateinit var campoLibrasTercera: EditText
    lateinit var campoPrecioTercera: EditText
    lateinit var campoLibrasCuarta: EditText
    lateinit var campoPrecioCuarta: EditText
    lateinit var campoLibrasQuinta: EditText
    lateinit var campoPrecioQuinta: EditText
    lateinit var campoLibrasMadura: EditText
    lateinit var campoPrecioMadura: EditText
    lateinit var campoFecha: EditText
    lateinit var campoObservacion: EditText

    lateinit var txtExtraLibraReg: TextView
    lateinit var txtPrimeraLibraReg: TextView
    lateinit var txtSegundaLibraReg: TextView
    lateinit var txtTerceraLibraReg: TextView
    lateinit var txtCuartaLibraReg: TextView
    lateinit var txtQuintaLibraReg: TextView
    lateinit var txtMaduraLibraReg: TextView

    lateinit var txtExtraPrecioReg: TextView
    lateinit var txtPrimeraPrecioReg: TextView
    lateinit var txtSegundaPrecioReg: TextView
    lateinit var txtTerceraPrecioReg: TextView
    lateinit var txtCuartaPrecioReg: TextView
    lateinit var txtQuintaPrecioReg: TextView
    lateinit var txtMaduraPrecioReg: TextView

    lateinit var txtTotalReg: TextView
    var total: Int = 0

    lateinit var layoutExtraReg: RelativeLayout
    lateinit var layoutPrimeraReg: RelativeLayout
    lateinit var layoutSegundaReg: RelativeLayout
    lateinit var layoutTerceraReg: RelativeLayout
    lateinit var layoutCuartaReg: RelativeLayout
    lateinit var layoutQuintaReg: RelativeLayout
    lateinit var layoutMaduraReg: RelativeLayout

    lateinit var btnCamara: Button
    lateinit var imagenView: ImageView

    lateinit var cardListaReg: CardView

    //Variables para almacenar los editText
    var librasExtra: String = ""
    var precioExtra: String = ""
    var librasPrimera: String = ""
    var precioPrimera: String = ""
    var librasSegunda: String = ""
    var precioSegunda: String = ""
    var librasTercera: String = ""
    var precioTercera: String = ""
    var librasCuarta: String = ""
    var precioCuarta: String = ""
    var librasQuinta: String = ""
    var precioQuinta: String = ""
    var librasMadura: String = ""
    var precioMadura: String = ""

    //variables para almacenar valores de los EditText convertidos en Int
    var le: Int = 0
    var lp: Int = 0
    var ls: Int = 0
    var lt: Int = 0
    var lc: Int = 0
    var lq: Int = 0
    var lm: Int = 0
    var pe: Int = 0
    var pp: Int = 0
    var ps: Int = 0
    var pt: Int = 0
    var pc: Int = 0
    var pq: Int = 0
    var pm: Int = 0

    lateinit var recyclerCosechaMes: RecyclerView
    var dia: Int = 0
    var mes: Int = 0
    var año: Int = 0

    private val COD_SELECCIONA = 10
    private val COD_FOTO = 20
    private val CARPETA_PRINCIPAL = "misImagenesApp/" //directorio principal
    lateinit var fileImagen: File
    lateinit var bitmap: Bitmap
    lateinit var path:String
    private val CARPETA_IMAGEN = "imagenes" //carpeta donde se guardan las fotos

    private val DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN //ruta carpeta de directorios


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
        vista = inflater.inflate(R.layout.fragment_dialogo_reg_cosecha, container, false)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        btnExtra = vista.findViewById(R.id.btnExtra)
        btnPrimera = vista.findViewById(R.id.btnPrimera)
        btnSegunda = vista.findViewById(R.id.btnSegunda)
        btnTercera = vista.findViewById(R.id.btnTercera)
        btnCuarta = vista.findViewById(R.id.btnCuarta)
        btnQuinta = vista.findViewById(R.id.btnQuinta)
        btnMadura = vista.findViewById(R.id.btnMadura)
        btnOkExtra = vista.findViewById(R.id.btnOkExtra)
        btnOkPrimera = vista.findViewById(R.id.btnOkPrimera)
        btnOkSegunda = vista.findViewById(R.id.btnOkSegunda)
        btnOkTercera = vista.findViewById(R.id.btnOkTercera)
        btnOkCuarta = vista.findViewById(R.id.btnOkCuarta)
        btnOkQuinta = vista.findViewById(R.id.btnOkQuinta)
        btnOkMadura = vista.findViewById(R.id.btnOkMadura)
        layoutExtra = vista.findViewById(R.id.layoutExtra)
        layoutPrimera = vista.findViewById(R.id.layoutPrimera)
        layoutSegunda = vista.findViewById(R.id.layoutSegunda)
        layoutTercera = vista.findViewById(R.id.layoutTercera)
        layoutCuarta = vista.findViewById(R.id.layoutCuarta)
        layoutQuinta = vista.findViewById(R.id.layoutQuinta)
        layoutMadura = vista.findViewById(R.id.layoutMadura)
        campoLibrasExtra = vista.findViewById(R.id.campoCantExtra)
        campoPrecioExtra = vista.findViewById(R.id.campoPrecioExtra)
        campoLibrasPrimera = vista.findViewById(R.id.campoCantPrimera)
        campoPrecioPrimera = vista.findViewById(R.id.campoPrecioPrimera)
        campoLibrasSegunda = vista.findViewById(R.id.campoCantSegunda)
        campoPrecioSegunda = vista.findViewById(R.id.campoPrecioSegunda)
        campoLibrasTercera = vista.findViewById(R.id.campoCantTercera)
        campoPrecioTercera = vista.findViewById(R.id.campoPrecioTercera)
        campoLibrasCuarta = vista.findViewById(R.id.campoCantCuarta)
        campoPrecioCuarta = vista.findViewById(R.id.campoPrecioCuarta)
        campoLibrasQuinta = vista.findViewById(R.id.campoCantQuinta)
        campoPrecioQuinta = vista.findViewById(R.id.campoPrecioQuinta)
        campoLibrasMadura = vista.findViewById(R.id.campoCantMaduraFF)
        campoPrecioMadura = vista.findViewById(R.id.campoPrecioMaduraFF)
        campoObservacion = vista.findViewById(R.id.campoObservacion)
        campoFecha = vista.findViewById(R.id.campoFechaCosecha)
        campoFecha.setOnClickListener { showDatePickerDialog() }

        layoutExtraReg = vista.findViewById(R.id.layoutExtraReg)
        layoutPrimeraReg = vista.findViewById(R.id.layoutPrimeraReg)
        layoutSegundaReg = vista.findViewById(R.id.layoutSegundaReg)
        layoutTerceraReg = vista.findViewById(R.id.layoutTerceraReg)
        layoutCuartaReg = vista.findViewById(R.id.layoutCuartaReg)
        layoutQuintaReg = vista.findViewById(R.id.layoutQuintaReg)
        layoutMaduraReg = vista.findViewById(R.id.layoutMaduraReg)

        txtExtraLibraReg = vista.findViewById(R.id.txtExtraLibraReg)
        txtPrimeraLibraReg = vista.findViewById(R.id.txtPrimeraLibraReg)
        txtSegundaLibraReg = vista.findViewById(R.id.txtSegundaLibraReg)
        txtTerceraLibraReg = vista.findViewById(R.id.txtTerceraLibraReg)
        txtCuartaLibraReg = vista.findViewById(R.id.txtCuartaLibraReg)
        txtQuintaLibraReg = vista.findViewById(R.id.txtQuintaLibraReg)
        txtMaduraLibraReg = vista.findViewById(R.id.txtMaduraLibraReg)

        txtExtraPrecioReg = vista.findViewById(R.id.txtExtraPrecioReg)
        txtPrimeraPrecioReg = vista.findViewById(R.id.txtPrimeraPrecioReg)
        txtSegundaPrecioReg = vista.findViewById(R.id.txtSegundaPrecioReg)
        txtTerceraPrecioReg = vista.findViewById(R.id.txtTerceraPrecioReg)
        txtCuartaPrecioReg = vista.findViewById(R.id.txtCuartaPrecioReg)
        txtQuintaPrecioReg = vista.findViewById(R.id.txtQuintaPrecioReg)
        txtMaduraPrecioReg = vista.findViewById(R.id.txtMaduraPrecioReg)

        btnCamara = vista.findViewById(R.id.btnCamara)
        imagenView = vista.findViewById<ImageView>(R.id.imagenView)

        txtTotalReg = vista.findViewById(R.id.txtTotalReg)

        cardListaReg = vista.findViewById(R.id.cardListaReg)

        fabAtras = vista.findViewById(R.id.btnIcoAtras)

        recyclerCosechaMes = vista.findViewById(R.id.recyclerCosechaRegistros)
        recyclerCosechaMes.layoutManager = LinearLayoutManager(actividad)
        recyclerCosechaMes.setHasFixedSize(true)
        cosechaPorDia(1, 2023)
        eventosMenu()
        return vista
    }

    private fun showDatePickerDialog() {
        val datePicker =
            DatePickerFragment { day, month, year -> onDateSelected(year, month + 1, day) }
        datePicker.show(parentFragmentManager, "datePicker")
    }

    fun onDateSelected(day: Int, month: Int, year: Int) {
        campoFecha.setText("$year-$month-$day")
        dia = day
        mes = month
        año = year
    }

    private fun eventosMenu() {
        fabAtras.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()
            }

        })
        btnGuardar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                registrarCosecha()
            }
        })
        btnCamara.setOnClickListener {
            //startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { }
            mostrarDialogOpciones()

        }
        btnExtra.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                mostrarExtra()
            }
        })
        btnPrimera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                mostrarPrimera()
            }
        })
        btnSegunda.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                mostrarSegundad()
            }
        })
        btnTercera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                mostrarTercera()
            }
        })
        btnCuarta.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                mostrarCuarta()
            }
        })
        btnQuinta.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                mostrarQuinta()
            }
        })
        btnMadura.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                mostrarMadura()
            }
        })

        btnOkExtra.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (campoLibrasExtra.text.toString().isEmpty() or campoPrecioExtra.text.toString()
                        .isEmpty()
                ) {
                    if (campoLibrasExtra.text.toString().isEmpty()) {
                        campoLibrasExtra.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioExtra.text.toString().isEmpty()) {
                        campoPrecioExtra.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarPrimera()

                    librasExtra = campoLibrasExtra.text.toString()

                    precioExtra = campoPrecioExtra.text.toString()

                    //Variables de libras y precio por calidad
                    le = librasExtra.toInt()
                    pe = precioExtra.toInt()

                    total = (total + (le * pe))

                    // Do some work here
                    cardListaReg.visibility = View.VISIBLE
                    layoutExtraReg.visibility = View.VISIBLE
                    txtExtraLibraReg.setText(campoLibrasExtra.text.toString())
                    txtExtraPrecioReg.setText("$" + campoPrecioExtra.text.toString())

                    txtTotalReg.setText("$" + total)
                }

            }
        })



        btnOkPrimera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (campoLibrasPrimera.text.toString()
                        .isEmpty() or campoPrecioPrimera.text.toString()
                        .isEmpty()
                ) {
                    if (campoLibrasPrimera.text.toString().isEmpty()) {
                        campoLibrasPrimera.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioPrimera.text.toString().isEmpty()) {
                        campoPrecioPrimera.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarSegundad()

                    librasPrimera = campoLibrasPrimera.text.toString()
                    precioPrimera = campoPrecioPrimera.text.toString()

                    //Variables de libras y precio por calidad
                    lp = librasPrimera.toInt()
                    pp = precioPrimera.toInt()

                    total = (total + (lp * pp))

                    // Do some work here
                    cardListaReg.visibility = View.VISIBLE
                    layoutPrimeraReg.visibility = View.VISIBLE
                    txtPrimeraLibraReg.setText(campoLibrasPrimera.text.toString())
                    txtPrimeraPrecioReg.setText("$" + campoPrecioPrimera.text.toString())

                    txtTotalReg.setText("$" + total)
                }

            }
        })




        btnOkSegunda.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (campoLibrasSegunda.text.toString()
                        .isEmpty() or campoPrecioSegunda.text.toString()
                        .isEmpty()
                ) {
                    if (campoLibrasSegunda.text.toString().isEmpty()) {
                        campoLibrasSegunda.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioSegunda.text.toString().isEmpty()) {
                        campoPrecioSegunda.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarTercera()

                    librasSegunda = campoLibrasSegunda.text.toString()

                    precioSegunda = campoPrecioSegunda.text.toString()

                    //Variables de libras y precio por calidad
                    ls = librasSegunda.toInt()
                    ps = precioSegunda.toInt()

                    total = (total + (ls * ps))

                    // Do some work here
                    cardListaReg.visibility = View.VISIBLE
                    layoutSegundaReg.visibility = View.VISIBLE
                    txtSegundaLibraReg.setText(campoLibrasSegunda.text.toString())
                    txtSegundaPrecioReg.setText("$" + campoPrecioSegunda.text.toString())

                    txtTotalReg.setText("$" + total)
                }

            }
        })



        btnOkTercera.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (campoLibrasTercera.text.toString()
                        .isEmpty() or campoPrecioTercera.text.toString()
                        .isEmpty()
                ) {
                    if (campoLibrasTercera.text.toString().isEmpty()) {
                        campoLibrasTercera.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioTercera.text.toString().isEmpty()) {
                        campoPrecioTercera.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarCuarta()

                    librasTercera = campoLibrasTercera.text.toString()

                    precioTercera = campoPrecioTercera.text.toString()

                    //Variables de libras y precio por calidad
                    lt = librasTercera.toInt()
                    pt = precioTercera.toInt()

                    total = (total + (lt * pt))

                    // Do some work here
                    cardListaReg.visibility = View.VISIBLE
                    layoutTerceraReg.visibility = View.VISIBLE
                    txtTerceraLibraReg.setText(campoLibrasTercera.text.toString())
                    txtTerceraPrecioReg.setText("$" + campoPrecioTercera.text.toString())

                    txtTotalReg.setText("$" + total)

                }
            }
        })



        btnOkCuarta.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (campoLibrasCuarta.text.toString()
                        .isEmpty() or campoPrecioCuarta.text.toString()
                        .isEmpty()
                ) {
                    if (campoLibrasCuarta.text.toString().isEmpty()) {
                        campoLibrasCuarta.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioCuarta.text.toString().isEmpty()) {
                        campoPrecioCuarta.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarQuinta()

                    librasCuarta = campoLibrasCuarta.text.toString()

                    precioCuarta = campoPrecioCuarta.text.toString()

                    //Variables de libras y precio por calidad
                    lc = librasCuarta.toInt()
                    pc = precioCuarta.toInt()

                    total = (total + (lc * pc))

                    // Do some work here
                    cardListaReg.visibility = View.VISIBLE
                    layoutCuartaReg.visibility = View.VISIBLE
                    txtCuartaLibraReg.setText(campoLibrasCuarta.text.toString())
                    txtCuartaPrecioReg.setText("$" + campoPrecioCuarta.text.toString())

                    txtTotalReg.setText("$" + total)
                }

            }
        })




        btnOkQuinta.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (campoLibrasQuinta.text.toString()
                        .isEmpty() or campoPrecioQuinta.text.toString()
                        .isEmpty()
                ) {
                    if (campoLibrasQuinta.text.toString().isEmpty()) {
                        campoLibrasQuinta.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioQuinta.text.toString().isEmpty()) {
                        campoPrecioQuinta.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarMadura()

                    librasQuinta = campoLibrasQuinta.text.toString()

                    precioQuinta = campoPrecioQuinta.text.toString()

                    //Variables de libras y precio por calidad
                    lq = librasQuinta.toInt()
                    pq = precioQuinta.toInt()

                    total = (total + (lq * pq))

                    // Do some work here
                    cardListaReg.visibility = View.VISIBLE
                    layoutQuintaReg.visibility = View.VISIBLE
                    txtQuintaLibraReg.setText(campoLibrasQuinta.text.toString())
                    txtQuintaPrecioReg.setText("$" + campoPrecioQuinta.text.toString())

                    txtTotalReg.setText("$" + total)
                }
            }
        })

        btnOkMadura.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                if (campoLibrasMadura.text.toString()
                        .isEmpty() or campoPrecioMadura.text.toString().isEmpty()
                ) {
                    if (campoLibrasMadura.text.toString().isEmpty()) {
                        campoLibrasMadura.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioMadura.text.toString().isEmpty()) {
                        campoPrecioMadura.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                    layoutMadura.visibility = View.GONE

                    librasMadura = campoLibrasMadura.text.toString()
                    precioMadura = campoPrecioMadura.text.toString()

                    //Variables de libras y precio por calidad
                    lm = librasMadura.toInt()
                    pm = precioMadura.toInt()

                    total = (total + (lm * pm))

                    // Do some work here
                    cardListaReg.visibility = View.VISIBLE
                    layoutMaduraReg.visibility = View.VISIBLE
                    txtMaduraLibraReg.setText(campoLibrasMadura.text.toString())
                    txtMaduraPrecioReg.setText("$" + campoPrecioMadura.text.toString())
                    txtTotalReg.setText("$" + total)
                }
            }
        })
    }

    private fun mostrarExtra() {
        btnExtra.setBackgroundColor(resources.getColor(R.color.colorSplash))
        btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        layoutExtra.visibility = View.VISIBLE
        layoutPrimera.visibility = View.GONE
        layoutSegunda.visibility = View.GONE
        layoutTercera.visibility = View.GONE
        layoutCuarta.visibility = View.GONE
        layoutQuinta.visibility = View.GONE
        layoutMadura.visibility = View.GONE
    }

    private fun mostrarPrimera() {
        btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnPrimera.setBackgroundColor(resources.getColor(R.color.colorSplash))
        btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        layoutExtra.visibility = View.GONE
        layoutPrimera.visibility = View.VISIBLE
        layoutSegunda.visibility = View.GONE
        layoutTercera.visibility = View.GONE
        layoutCuarta.visibility = View.GONE
        layoutQuinta.visibility = View.GONE
        layoutMadura.visibility = View.GONE
    }

    private fun mostrarSegundad() {
        btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnSegunda.setBackgroundColor(resources.getColor(R.color.colorSplash))
        btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        layoutExtra.visibility = View.GONE
        layoutPrimera.visibility = View.GONE
        layoutSegunda.visibility = View.VISIBLE
        layoutTercera.visibility = View.GONE
        layoutCuarta.visibility = View.GONE
        layoutQuinta.visibility = View.GONE
        layoutMadura.visibility = View.GONE
    }

    private fun mostrarTercera() {
        btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnTercera.setBackgroundColor(resources.getColor(R.color.colorSplash))
        btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        layoutExtra.visibility = View.GONE
        layoutPrimera.visibility = View.GONE
        layoutSegunda.visibility = View.GONE
        layoutTercera.visibility = View.VISIBLE
        layoutCuarta.visibility = View.GONE
        layoutQuinta.visibility = View.GONE
        layoutMadura.visibility = View.GONE
    }

    private fun mostrarCuarta() {
        btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnCuarta.setBackgroundColor(resources.getColor(R.color.colorSplash))
        btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        layoutExtra.visibility = View.GONE
        layoutPrimera.visibility = View.GONE
        layoutSegunda.visibility = View.GONE
        layoutTercera.visibility = View.GONE
        layoutCuarta.visibility = View.VISIBLE
        layoutQuinta.visibility = View.GONE
        layoutMadura.visibility = View.GONE
    }

    private fun mostrarQuinta() {
        btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnQuinta.setBackgroundColor(resources.getColor(R.color.colorSplash))
        btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        layoutExtra.visibility = View.GONE
        layoutPrimera.visibility = View.GONE
        layoutSegunda.visibility = View.GONE
        layoutTercera.visibility = View.GONE
        layoutCuarta.visibility = View.GONE
        layoutQuinta.visibility = View.VISIBLE
        layoutMadura.visibility = View.GONE
    }

    private fun mostrarMadura() {
        btnExtra.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnPrimera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnSegunda.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnTercera.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnCuarta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnQuinta.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
        btnMadura.setBackgroundColor(resources.getColor(R.color.colorSplash))
        layoutExtra.visibility = View.GONE
        layoutPrimera.visibility = View.GONE
        layoutSegunda.visibility = View.GONE
        layoutTercera.visibility = View.GONE
        layoutCuarta.visibility = View.GONE
        layoutQuinta.visibility = View.GONE
        layoutMadura.visibility = View.VISIBLE
    }

    private fun mostrarDialogOpciones() {
        val opciones = arrayOf<CharSequence>("Tomar Foto", "Elegir de Galeria", "Cancelar")
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Elige una Opción")
        builder.setItems(opciones, DialogInterface.OnClickListener { dialogInterface, i ->
            if (opciones[i] == "Tomar Foto") {
                startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { })
            } else {
                if (opciones[i] == "Elegir de Galeria") {
                    val intent = Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                    )
                    intent.type = "image/"
                    startActivityForResult(
                        Intent.createChooser(intent, "Seleccione"),
                        COD_SELECCIONA
                    )
                } else {
                    dialogInterface.dismiss()
                }
            }
        })
        builder.show()
    }

    private fun abriCamara() {
        val miFile = File(Environment.getExternalStorageDirectory(), DIRECTORIO_IMAGEN)
        var isCreada: Boolean = miFile.exists()
        if (isCreada == false) {
            isCreada = miFile.mkdirs()
        }
        if (isCreada == true) {
            val consecutivo = System.currentTimeMillis() / 1000
            val nombre = "$consecutivo.jpg"
            //path = (Environment.getExternalStorageDirectory()+File.separator + DIRECTORIO_IMAGEN + File.separator.toString() + nombre) //indicamos la ruta de almacenamiento
            fileImagen = File(path)
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen))

            ////
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //val authorities = context!!.packageName + ".provider"
                //val imageUri: Uri = FileProvider.getUriForFile(context!!, authorities, fileImagen)
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileImagen))
            }
            startActivityForResult(intent, COD_FOTO)

            ////
        }
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                var imageBitmap = intent?.extras?.get("data") as Bitmap
                var ancho :Float = (600).toFloat()
                var alto :Float = (800).toFloat()
                bitmap = redimensionarImagen(imageBitmap,ancho ,alto )
                imagenView.setImageBitmap(bitmap)
            }

        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            COD_SELECCIONA -> {
                val miPath: Uri? = data!!.data
                imagenView.setImageURI(miPath)
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, miPath)
                    imagenView.setImageBitmap(bitmap)
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
            COD_FOTO -> {
                MediaScannerConnection.scanFile(
                    context, arrayOf<String>(path), null
                ) { path, uri -> Log.i("Path", "" + path) }
                bitmap = BitmapFactory.decodeFile(path)
            }
        }
        var ancho :Float = (600).toFloat()
        var alto :Float = (800).toFloat()
        bitmap = redimensionarImagen(bitmap, ancho, alto)
        imagenView.setImageBitmap(bitmap)
    }

    private fun redimensionarImagen (bitmap: Bitmap, anchoNuevo:Float, altoNuevo:Float) :Bitmap{
        var ancho = bitmap.width
        var alto = bitmap.height
        if(ancho>anchoNuevo || alto>altoNuevo){
            var escalaAncho= anchoNuevo/ancho
            var escalaAlto = altoNuevo/alto

            var matrix: Matrix = Matrix()
            matrix.postScale(escalaAncho, escalaAlto)
            return Bitmap.createBitmap(bitmap, 0,0,ancho,alto,matrix, false)
        }else {
            return bitmap
        }
    }
/*
    public fun guardarImagen(id:Long, bitmap:Bitmap ){
        // tamaño del baos depende del tamaño de tus imagenes en promedio
        var baos: ByteArrayOutputStream = ByteArrayOutputStream(20480)
        bitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos)
        var blob = baos.toByteArray()
        // aqui tenemos el byte[] con el imagen comprimido, ahora lo guardemos en SQLite
        insert.clearBindings();
        insert.bindLong(1, id));
        insert.bindBlob(2, blob);
        insert.executeInsert();
        db.close();
    }*/

    private fun registrarCosecha() {
        progreso= ProgressDialog(getContext())
        progreso!!.setMessage("Cargando...")


        //if((campoCantidad.text.toString()!=null && !campoCantidad.text.toString().trim().equals("")) and (campoPrecio.text.toString()!=null && !campoPrecio.text.toString().trim().equals(""))){
        if (campoFecha.text.toString()
                .isEmpty() or (le < 1) or (pe < 1) or (lp < 1) or (pp < 1) or (ls < 1) or (ps < 1) or (lt < 1) or (pt < 1) or (lc < 1) or (pc < 1) or (lq < 1) or (pq < 1) or (lm < 1) or (pm < 1)
        ) {
            if (campoFecha.text.toString().isEmpty()) {
                campoFecha.setError("Este campo no puede quedar vacio")
            } else if ((le < 1) or (pe < 1)) {
                mostrarExtra()
                if ((le < 1)) {
                    campoLibrasExtra.setError("Digite un valor mayor a cero para continuar")
                } else if ((pe < 1)) {
                    campoPrecioExtra.setError("Digite un valor mayor a cero para continuar")
                }
            } else if ((lp < 1) or (pp < 1)) {
                mostrarPrimera()
                if ((lp < 1)) {
                    campoLibrasPrimera.setError("Digite un valor mayor a cero para continuar")
                } else if ((pp < 1)) {
                    campoPrecioPrimera.setError("Digite un valor mayor a cero para continuar")
                }
            } else if ((ls < 1) or (ps < 1)) {
                mostrarSegundad()
                if ((ls < 1)) {
                    campoLibrasSegunda.setError("Digite un valor mayor a cero para continuar")
                } else if ((ps < 1)) {
                    campoPrecioSegunda.setError("Digite un valor mayor a cero para continuar")
                }
            } else if ((lt < 1) or (pt < 1)) {
                mostrarTercera()
                if ((lt < 1)) {
                    campoLibrasTercera.setError("Digite un valor mayor a cero para continuar")
                } else if ((pt < 1)) {
                    campoPrecioTercera.setError("Digite un valor mayor a cero para continuar")
                }
            } else if ((lc < 1) or (pc < 1)) {
                mostrarCuarta()
                if ((lc < 1)) {
                    campoLibrasCuarta.setError("Digite un valor mayor a cero para continuar")
                } else if ((pc < 1)) {
                    campoPrecioCuarta.setError("Digite un valor mayor a cero para continuar")
                }
            } else if ((lq < 1) or (pq < 1)) {
                mostrarQuinta()
                if ((lq < 1)) {
                    campoLibrasQuinta.setError("Digite un valor mayor a cero para continuar")
                } else if ((pq < 1)) {
                    campoPrecioQuinta.setError("Digite un valor mayor a cero para continuar")
                }
            } else if ((lm < 1) or (pm < 1)) {
                mostrarMadura()
                if ((lm < 1)) {
                    campoLibrasMadura.setError("Digite un valor mayor a cero para continuar")
                } else if ((pm < 1)) {
                    campoPrecioMadura.setError("Digite un valor mayor a cero para continuar")
                }
            }
        } else {
            /*var registro= "Extra: "+campoLibrasExtra.text.toString()+ "   Precio: "+ campoPrecioExtra.text.toString() +"\n"
            registro += "Primera: "+campoLibrasPrimera.text.toString()+ "   Precio: "+ campoPrecioPrimera.text.toString() +"\n"
            registro += "Segunda: "+campoLibrasSegunda.text.toString()+ "   Precio: "+ campoPrecioSegunda.text.toString() +"\n"
            registro += "Tercera: "+campoLibrasTercera.text.toString()+ "   Precio: "+ campoPrecioTercera.text.toString() +"\n"
            registro += "Cuarta: "+campoLibrasCuarta.text.toString()+ "   Precio: "+ campoPrecioCuarta.text.toString() +"\n"
            registro += "Quinta: "+campoLibrasQuinta.text.toString()+ "   Precio: "+ campoPrecioQuinta.text.toString() +"\n"
            registro += "Madura FF: "+campoLibrasMadura.text.toString()+ "   Precio: "+ campoPrecioMadura.text.toString() +"\n"

            print("Registrar:  "+registro)
            Toast.makeText(actividad, "REGISTRAR:\n"+registro, Toast.LENGTH_LONG).show()

             */
            //La linea sigueinte deberia ir dentro de un IF que verifique si la consulta SQL es correcta
            progreso!!.show()
            //conexion con la base de datos
            val conexion = ConexionSQLiteHelper(actividad, Utilidades.NOMBRE_BD, null, 1)
            val db: SQLiteDatabase = conexion.writableDatabase
            var values = ContentValues()

            //valores para agregar a la tabla de cultivos
            //values.put(Utilidades.CAMPO_ID_CULTIVO, .text.toString()) //SI quito esto, le asigna los ID en orden 1,2,3...
            values.put(Utilidades.CAMPO_DIA_COSECHA, dia)
            values.put(Utilidades.CAMPO_MES_COSECHA, mes)
            values.put(Utilidades.CAMPO_AÑO_COSECHA, año)
            values.put(Utilidades.CAMPO_LIBRAS_EXTRA, campoLibrasExtra.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_EXTRA, campoPrecioExtra.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_PRIMERA, campoLibrasPrimera.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_PRIMERA, campoPrecioPrimera.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_SEGUNDA, campoLibrasSegunda.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_SEGUNDA, campoPrecioSegunda.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_TERCERA, campoLibrasTercera.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_TERCERA, campoPrecioTercera.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_CUARTA, campoLibrasCuarta.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_CUARTA, campoPrecioCuarta.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_QUINTA, campoLibrasQuinta.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_QUINTA, campoPrecioQuinta.text.toString())
            values.put(Utilidades.CAMPO_LIBRAS_MADURA, campoLibrasMadura.text.toString())
            values.put(Utilidades.CAMPO_PRECIO_MADURA, campoPrecioMadura.text.toString())
            values.put(Utilidades.CAMPO_OBSERVACION_COSECHA, campoObservacion.text.toString())
            values.put(Utilidades.CAMPO_CULTIVO_COSECHA, DialogoGesCultivo.cultivoSeleccionado.id)
            var baos: ByteArrayOutputStream = ByteArrayOutputStream(20480)
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 , baos)
            var blob = baos.toByteArray()
            values.put(Utilidades.CAMPO_IMG_FACTURA, blob)

            //values.put(Utilidades.CAMPO_IMG_FACTURA)
            val idResultante: Number =
                db.insert(Utilidades.TABLA_COSECHA, Utilidades.CAMPO_ID_CULTIVO, values)

            if (idResultante != -1) {
                Toast.makeText(actividad, "¡Registro Éxitoso! ", Toast.LENGTH_SHORT).show()
                progreso!!.hide()
                dismiss()
                //Utilidades.calcularBeneficioCultivo(actividad)
            } else {
                Toast.makeText(actividad, "Verifique los datos de Registro!", Toast.LENGTH_SHORT)
                    .show()
            }
            db.close()
        }
    }

    private fun cosechaPorDia(año: Int, mes: Int) {
        //Utilidades.calcularBeneficioCultivo(actividad,mes,año)
        Utilidades.consultarCosechaDia(actividad, 1, 2023, 23, 1)

        var miAdaptadorCosecha = AdaptadorCosechaMesCultivo(Utilidades.listaCosechaCultivo!!)
        miAdaptadorCosecha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                //mesSeleccionado = Utilidades.listaBeneficioCultivo!!.get(recyclerInformeMes.getChildAdapterPosition(view!!))
            }
        })

        recyclerCosechaMes.adapter = miAdaptadorCosecha
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DialogoRegCosecha.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DialogoRegCosecha().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}