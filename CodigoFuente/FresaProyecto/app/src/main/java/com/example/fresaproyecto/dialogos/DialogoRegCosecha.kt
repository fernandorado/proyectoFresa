package com.example.fresaproyecto.dialogos

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.ContentValues
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.ColorDrawable
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Bundle
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fresaproyecto.R
import com.example.fresaproyecto.adapters.AdaptadorCosechaMesCultivo
import com.example.fresaproyecto.clases.ConexionSQLiteHelper
import com.example.fresaproyecto.clases.DatePickerFragment
import com.example.fresaproyecto.clases.Utilidades
import com.example.fresaproyecto.clases.vo.CosechaCultivoVo
import com.example.fresaproyecto.interfaces.IComunicaFragments
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class DialogoRegCosecha : DialogFragment() {

    var progreso: ProgressDialog? = null

    var contReg: Int = 0
    var idCultivo = DialogoGesCultivo.cultivoSeleccionado.id

    lateinit var vista: View

    lateinit var idLayoutAct: LinearLayout
    lateinit var cosechaSeleccionada: CosechaCultivoVo
    lateinit var txtTituloCosecha: TextView
    lateinit var vistaImagen: View
    lateinit var imgFactura: ImageView
    lateinit var actividad: Activity
    lateinit var interfaceComunicaFragments: IComunicaFragments
    lateinit var btnGuardar: Button
    lateinit var btnActualizar: Button
    lateinit var btnCancelar: Button
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
    lateinit var txtVerFoto: TextView
    var total: Int = 0

    lateinit var layoutExtraReg: RelativeLayout
    lateinit var layoutPrimeraReg: RelativeLayout
    lateinit var layoutSegundaReg: RelativeLayout
    lateinit var layoutTerceraReg: RelativeLayout
    lateinit var layoutCuartaReg: RelativeLayout
    lateinit var layoutQuintaReg: RelativeLayout
    lateinit var layoutMaduraReg: RelativeLayout

    lateinit var btnCamara: Button

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
    lateinit var bitmap: Bitmap
    lateinit var path: String

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        //dialog.window!!.setGravity(Gravity.TOP)
        return dialog
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
        vistaImagen = inflater.inflate(R.layout.imagen, container, false)

        txtTituloCosecha = vista.findViewById(R.id.txtTituloCosecha)
        imgFactura = vistaImagen.findViewById(R.id.imgFactura)
        btnGuardar = vista.findViewById(R.id.idBtnGuardar)
        btnActualizar = vista.findViewById(R.id.idBtnActualizarCosecha)
        btnCancelar = vista.findViewById(R.id.idBtnCancelarActCosecha)
        idLayoutAct = vista.findViewById(R.id.idLayoutAct)
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

        txtVerFoto = vista.findViewById(R.id.txtVerFoto)

        btnCamara = vista.findViewById(R.id.btnCamara)

        txtTotalReg = vista.findViewById(R.id.txtTotalReg)

        cardListaReg = vista.findViewById(R.id.cardListaReg)

        fabAtras = vista.findViewById(R.id.btnIcoAtras)

        recyclerCosechaMes = vista.findViewById(R.id.recyclerCosechaRegistros)
        recyclerCosechaMes.layoutManager = LinearLayoutManager(actividad)
        recyclerCosechaMes.setHasFixedSize(true)

        var miPath: Uri? = null
        val archivo = "android.resource://" + actividad.packageName + "/" + R.drawable.sin_foto
        miPath = Uri.parse(archivo)
        bitmap = MediaStore.Images.Media.getBitmap(requireContext().contentResolver, miPath)

        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        val dateFormatD = SimpleDateFormat("dd")
        val diaActual = dateFormatD.format(Date())

        cosechaPorDia(añoActual.toInt(), mesActual.toInt(), diaActual.toInt())

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

        cosechaPorDia(año, mes, dia)
    }

    private fun eventosMenu() {
        fabAtras.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                dismiss()
            }

        })

        btnCancelar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                cancelarAct()

            }

        })

        txtVerFoto.setOnClickListener {
            val builder: android.app.AlertDialog.Builder = android.app.AlertDialog.Builder(context)
            builder.setTitle("Factura")
                .setPositiveButton("Cerrar") { dialog, _ ->
                    dialog.dismiss()
                    if (vistaImagen != null) {
                        val parent = vistaImagen!!.parent as ViewGroup
                        parent?.removeView(vistaImagen)
                    }
                }
            var titulo: android.app.AlertDialog = builder.create()
            imgFactura.setImageBitmap(bitmap)
            titulo.setView(vistaImagen)
            titulo.show()
        }
        btnGuardar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                validarRegistro()
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
                if ((campoLibrasExtra.text.toString().isEmpty() or campoLibrasExtra.text.toString()
                        .equals("0")) or (campoPrecioExtra.text.toString()
                        .isEmpty() or campoPrecioExtra.text.toString().equals("0"))
                ) {
                    if (campoLibrasExtra.text.toString()
                            .isEmpty() or campoLibrasExtra.text.toString().equals("0")
                    ) {
                        campoLibrasExtra.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioExtra.text.toString()
                            .isEmpty() or campoPrecioExtra.text.toString().equals("0")
                    ) {
                        campoPrecioExtra.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarPrimera()
                    contReg = contReg + 1
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
                if ((campoLibrasPrimera.text.toString()
                        .isEmpty() or campoLibrasPrimera.text.toString()
                        .equals("0")) or (campoPrecioPrimera.text.toString()
                        .isEmpty() or campoPrecioPrimera.text.toString().equals("0"))
                ) {
                    if (campoLibrasPrimera.text.toString()
                            .isEmpty() or campoLibrasPrimera.text.toString().equals("0")
                    ) {
                        campoLibrasPrimera.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioPrimera.text.toString()
                            .isEmpty() or campoPrecioPrimera.text.toString().equals("0")
                    ) {
                        campoPrecioPrimera.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarSegundad()
                    contReg = contReg + 1

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
                if ((campoLibrasSegunda.text.toString()
                        .isEmpty() or campoLibrasSegunda.text.toString()
                        .equals("0")) or (campoPrecioSegunda.text.toString()
                        .isEmpty() or campoPrecioSegunda.text.toString()
                        .equals("0"))
                ) {
                    if (campoLibrasSegunda.text.toString()
                            .isEmpty() or campoLibrasSegunda.text.toString().equals("0")
                    ) {
                        campoLibrasSegunda.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioSegunda.text.toString()
                            .isEmpty() or campoPrecioSegunda.text.toString().equals("0")
                    ) {
                        campoPrecioSegunda.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarTercera()
                    contReg = contReg + 1

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
                if ((campoLibrasTercera.text.toString()
                        .isEmpty() or campoLibrasTercera.text.toString()
                        .equals("0")) or (campoPrecioTercera.text.toString()
                        .isEmpty() or campoPrecioTercera.text.toString().equals("0"))
                ) {
                    if (campoLibrasTercera.text.toString()
                            .isEmpty() or campoLibrasTercera.text.toString().equals("0")
                    ) {
                        campoLibrasTercera.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioTercera.text.toString()
                            .isEmpty() or campoPrecioTercera.text.toString().equals("0")
                    ) {
                        campoPrecioTercera.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarCuarta()
                    contReg = contReg + 1

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
                if ((campoLibrasCuarta.text.toString()
                        .isEmpty() or campoLibrasCuarta.text.toString()
                        .equals("0")) or (campoPrecioCuarta.text.toString()
                        .isEmpty() or campoPrecioCuarta.text.toString().equals("0"))
                ) {
                    if (campoLibrasCuarta.text.toString()
                            .isEmpty() or campoLibrasCuarta.text.toString().equals("0")
                    ) {
                        campoLibrasCuarta.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioCuarta.text.toString()
                            .isEmpty() or campoPrecioCuarta.text.toString().equals("0")
                    ) {
                        campoPrecioCuarta.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarQuinta()
                    contReg = contReg + 1

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
                if ((campoLibrasQuinta.text.toString()
                        .isEmpty() or campoLibrasQuinta.text.toString()
                        .equals("0")) or (campoPrecioQuinta.text.toString()
                        .isEmpty() or campoPrecioQuinta.text.toString()
                        .equals("0"))
                ) {
                    if (campoLibrasQuinta.text.toString()
                            .isEmpty() or campoLibrasQuinta.text.toString()
                            .equals("0")
                    ) {
                        campoLibrasQuinta.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioQuinta.text.toString()
                            .isEmpty() or campoPrecioQuinta.text.toString()
                            .equals("0")
                    ) {
                        campoPrecioQuinta.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    mostrarMadura()
                    contReg = contReg + 1

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
                if ((campoLibrasMadura.text.toString()
                        .isEmpty() or campoLibrasMadura.text.toString()
                        .equals("0")) or (campoPrecioMadura.text.toString()
                        .isEmpty() or campoPrecioMadura.text.toString().equals("0"))
                ) {
                    if (campoLibrasMadura.text.toString()
                            .isEmpty() or campoLibrasMadura.text.toString()
                            .equals("0")
                    ) {
                        campoLibrasMadura.setError("Digite un valor mayor a cero para continuar")
                    } else if (campoPrecioMadura.text.toString()
                            .isEmpty() or campoPrecioMadura.text.toString().equals("0")
                    ) {
                        campoPrecioMadura.setError("Digite un valor mayor a cero para continuar")
                    }
                } else {
                    btnMadura.setBackgroundColor(resources.getColor(R.color.colorGrisClaro))
                    layoutMadura.visibility = View.GONE
                    contReg = contReg + 1

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
        btnExtra.setBackgroundColor(resources.getColor(R.color.colorNaranja))
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
        btnPrimera.setBackgroundColor(resources.getColor(R.color.colorNaranja))
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
        btnSegunda.setBackgroundColor(resources.getColor(R.color.colorNaranja))
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
        btnTercera.setBackgroundColor(resources.getColor(R.color.colorNaranja))
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
        btnCuarta.setBackgroundColor(resources.getColor(R.color.colorNaranja))
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
        btnQuinta.setBackgroundColor(resources.getColor(R.color.colorNaranja))
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
        btnMadura.setBackgroundColor(resources.getColor(R.color.colorNaranja))
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

    private fun mostrarDialogRegistro(): AlertDialog {
        val builder = AlertDialog.Builder(vista.context)

        builder.setTitle("Registro")
            .setMessage(
                "¿Esta seguro que el registro de cosecha está completo?" +
                        "Verifique la información.\n" +
                        "Recuerde tomar la foto a su factura."
            )
            .setPositiveButton("Si") { dialog, _ ->
                dialog.dismiss()
                if (idLayoutAct.visibility == View.VISIBLE) {
                    actualizarCosecha()
                } else {
                    registrarCosecha()
                }

            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                var imageBitmap = intent?.extras?.get("data") as Bitmap
                var ancho: Float = (600).toFloat()
                var alto: Float = (800).toFloat()
                bitmap = imageBitmap
            }

        }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {

            COD_SELECCIONA -> {
                var ancho: Float = (600).toFloat()
                var alto: Float = (800).toFloat()
                var miPath: Uri? = null

                if (data == null) {
                    Toast.makeText(
                        actividad,
                        "¡No has seleccionado una imagen.! ",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    miPath = data!!.data
                    try {
                        bitmap =
                            MediaStore.Images.Media.getBitmap(
                                requireContext().contentResolver,
                                miPath
                            )
                        bitmap = redimensionarImagen(bitmap, ancho, alto)

                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }

            }
            COD_FOTO -> {
                MediaScannerConnection.scanFile(
                    context, arrayOf<String>(path), null
                ) { path, uri -> Log.i("Path", "" + path) }
                bitmap = BitmapFactory.decodeFile(path)
            }
        }
    }

    private fun redimensionarImagen(bitmap: Bitmap, anchoNuevo: Float, altoNuevo: Float): Bitmap {
        var ancho = bitmap.width
        var alto = bitmap.height
        if (ancho > anchoNuevo || alto > altoNuevo) {
            var escalaAncho = anchoNuevo / ancho
            var escalaAlto = altoNuevo / alto

            var matrix: Matrix = Matrix()
            matrix.postScale(escalaAncho, escalaAlto)
            return Bitmap.createBitmap(bitmap, 0, 0, ancho, alto, matrix, false)
        } else {
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

    private fun validarRegistro() {
        if (campoFecha.text.toString()
                .isEmpty() or (contReg < 1)
        ) {
            Toast.makeText(actividad, "¡Verifique sus datos! ", Toast.LENGTH_SHORT).show()

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
            mostrarDialogRegistro().show()
        }
    }

    private fun registrarCosecha() {
        progreso = ProgressDialog(getContext())
        progreso!!.setMessage("Cargando...")

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
        values.put(Utilidades.CAMPO_OBSERVACION_COSECHA, campoObservacion.text.toString().trim())
        values.put(Utilidades.CAMPO_CULTIVO_COSECHA, DialogoGesCultivo.cultivoSeleccionado.id)
        var baos: ByteArrayOutputStream = ByteArrayOutputStream(20480)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
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

    private fun cosechaPorDia(año: Int, mes: Int, dia: Int) {
        Utilidades.consultarCosechaDia(actividad, mes, año, dia, idCultivo)

        var miAdaptadorCosecha = AdaptadorCosechaMesCultivo()
        miAdaptadorCosecha.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                cosechaSeleccionada = Utilidades.listaCosechaCultivo!!.get(
                    recyclerCosechaMes.getChildAdapterPosition(view!!)
                )
                mostrarDialogOpcionesEdit()
            }
        })
        recyclerCosechaMes.adapter = miAdaptadorCosecha
    }

    private fun mostrarDialogOpcionesEdit() {
        println("DIALOGO OPCIONES POR DIA")
        val opciones = arrayOf<CharSequence>("Editar", "Eliminar", "Cancelar")
        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Elige una Opción")
        builder.setItems(opciones, DialogInterface.OnClickListener { dialogInterface, i ->
            if (opciones[i] == "Editar") {
                editar()
            } else {
                if (opciones[i] == "Eliminar") {
                    dialogoEliminar().show()
                } else {
                    dialogInterface.dismiss()
                }
            }
        })
        builder.show()
    }

    fun cancelarAct() {
        campoFecha.setText("")

        campoLibrasExtra.setText("")
        campoLibrasPrimera.setText("")
        campoLibrasSegunda.setText("")
        campoLibrasTercera.setText("")
        campoLibrasCuarta.setText("")
        campoLibrasQuinta.setText("")
        campoLibrasMadura.setText("")

        campoPrecioExtra.setText("")
        campoPrecioPrimera.setText("")
        campoPrecioSegunda.setText("")
        campoPrecioTercera.setText("")
        campoPrecioCuarta.setText("")
        campoPrecioQuinta.setText("")
        campoPrecioMadura.setText("")

        txtTituloCosecha.setText("REGISTRO DE COSECHA")
        btnGuardar.visibility = View.VISIBLE
        idLayoutAct.visibility = View.GONE
    }

    fun editar() {
        dia = cosechaSeleccionada.dia
        mes = cosechaSeleccionada.mes
        año = cosechaSeleccionada.año
        campoFecha.setText("${año}-${mes}-${dia}")

        campoLibrasExtra.setText("" + cosechaSeleccionada.extra)
        campoLibrasPrimera.setText("" + cosechaSeleccionada.primera)
        campoLibrasSegunda.setText("" + cosechaSeleccionada.segunda)
        campoLibrasTercera.setText("" + cosechaSeleccionada.tercera)
        campoLibrasCuarta.setText("" + cosechaSeleccionada.cuarta)
        campoLibrasQuinta.setText("" + cosechaSeleccionada.quinta)
        campoLibrasMadura.setText("" + cosechaSeleccionada.madura)

        campoPrecioExtra.setText("" + cosechaSeleccionada.precioExtra)
        campoPrecioPrimera.setText("" + cosechaSeleccionada.precioPrimera)
        campoPrecioSegunda.setText("" + cosechaSeleccionada.precioSegunda)
        campoPrecioTercera.setText("" + cosechaSeleccionada.precioTercera)
        campoPrecioCuarta.setText("" + cosechaSeleccionada.precioCuarta)
        campoPrecioQuinta.setText("" + cosechaSeleccionada.precioQuinta)
        campoPrecioMadura.setText("" + cosechaSeleccionada.precioMadura)

        txtTituloCosecha.setText("ACTUALIZAR COSECHA")
        btnGuardar.visibility = View.GONE
        idLayoutAct.visibility = View.VISIBLE
        btnActualizar.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                // Do some work here
                validarRegistro()

            }

        })

    }

    fun dialogoEliminar(): android.app.AlertDialog {

        val builder = android.app.AlertDialog.Builder(vista.context)

        builder.setTitle("Eliminar")
            .setMessage("Esta seguro que quiere eliminar esta cosecha?")
            .setPositiveButton("Si") { dialog, _ ->

                eliminarCosecha()
                dialog.dismiss()

            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }

        return builder.create()
    }

    private fun actualizarCosecha() {

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
        values.put(Utilidades.CAMPO_OBSERVACION_COSECHA, campoObservacion.text.toString().trim())
        values.put(Utilidades.CAMPO_CULTIVO_COSECHA, DialogoGesCultivo.cultivoSeleccionado.id)
        var baos: ByteArrayOutputStream = ByteArrayOutputStream(20480)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        var blob = baos.toByteArray()
        values.put(Utilidades.CAMPO_IMG_FACTURA, blob)

        val idResultante: Number = db.update(
            Utilidades.TABLA_COSECHA,
            values,
            Utilidades.CAMPO_ID_COSECHA + "=" + cosechaSeleccionada.id,
            null
        )

        if (idResultante != -1) {
            cosechaPorDia(año, mes, dia)
            cancelarAct()
            Toast.makeText(
                context,
                "¡La cosecha se Actualizó Exitosamente!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(
                context,
                "La cosecha no se pudo Actualizar, intente nuevamente.",
                Toast.LENGTH_SHORT
            ).show()
        }
        db.close()
    }


    private fun eliminarCosecha() {

        val conexion =
            ConexionSQLiteHelper(vista.context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_COSECHA,
                Utilidades.CAMPO_ID_COSECHA + "=" + cosechaSeleccionada.id,
                null
            )

        if (idResultante != -1) {
            Toast.makeText(context, "¡La cosecha se eliminó Exitosamente!", Toast.LENGTH_SHORT)
                .show()
            cosechaPorDia(año, mes, dia)

        } else {
            Toast.makeText(context, "La cosecha no se pudo Eliminar!", Toast.LENGTH_SHORT).show()
        }
        db.close()

    }

}