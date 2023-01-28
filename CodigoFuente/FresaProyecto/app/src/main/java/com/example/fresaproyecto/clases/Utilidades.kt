package com.example.fresaproyecto.clases

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.fresaproyecto.clases.vo.*
import com.example.fresaproyecto.dialogos.DialogoGesCultivo
import com.example.fresaproyecto.dialogos.DialogoGesPersona
import com.example.fresaproyecto.fragments.InformeCultivoFragment
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList


object Utilidades {
    var listaPersonas: ArrayList<PersonaVo>? = null
    var listaCultivos: ArrayList<CultivoVo>? = null
    var listaGastoIsumos: ArrayList<GastoInsumoCultivoVo>? = null
    var listaGastoJornales: ArrayList<GastoJornalCultivoVo>? = null
    var listaIngresoCosecha: ArrayList<IngresoCosechaCultivoVo>? = null
    var listaIngresoPersonal: ArrayList<IngresoPersonalVo>? = null
    var listaGastoPersonal: ArrayList<GastoPersonalVo>? = null
    var listaBeneficioPersonal: ArrayList<BeneficioPersonalVo>? = null
    var listaBeneficioCultivo: ArrayList<BeneficioCultivoVo>? = null
    var listaJornalCultivo: ArrayList<JornalCultivoVo>? = null
    var listaInsumoCultivo: ArrayList<InsumoCultivoVo>? = null
    var listaCosechaCultivo: ArrayList<CosechaCultivoVo>? = null

    var año1 = InformeCultivoFragment.añoSeleccionado
    var mes1 = InformeCultivoFragment.mesSeleccionado

    const val NOMBRE_BD = "fresa_bd"

    //Constantes campos tabla persona
    const val TABLA_PERSONA = "persona"
    const val CAMPO_ID_PERSONA = "id"
    const val CAMPO_NOMBRE_PERSONA = "nombre"

    //Constantes campos tabla registro de finanzas
    const val TABLA_INGRESO_PERSONAL = "ingreso_personal"
    const val CAMPO_ID_INGRESO = "id_ingreso"
    const val CAMPO_PERSONA_INGRESO = "id_persona"
    const val CAMPO_DIA_INGRESO = "dia_ingreso"
    const val CAMPO_MES_INGRESO = "mes_ingreso"
    const val CAMPO_AÑO_INGRESO = "año_ingreso"
    const val CAMPO_CONCEPTO_INGRESO = "concepto"
    const val CAMPO_PRECIO_INGRESO = "precio"

    //Constantes campos tabla registro de finanzas
    const val TABLA_GASTO_PERSONAL = "gasto_personal"
    const val CAMPO_ID_GASTO = "id_gasto"
    const val CAMPO_PERSONA_GASTO = "id_persona"
    const val CAMPO_DIA_GASTO = "dia_gasto"
    const val CAMPO_MES_GASTO = "mes_gasto"
    const val CAMPO_AÑO_GASTO = "año_gasto"
    const val CAMPO_CONCEPTO_GASTO = "concepto"
    const val CAMPO_PRECIO_GASTO = "precio"

    //Constantes campos tabla cultivo
    const val TABLA_CULTIVO = "cultivo"
    const val CAMPO_ID_CULTIVO = "id_cultivo"
    const val CAMPO_NOMBRE_CULTIVO = "nombre"
    const val CAMPO_CANT_PLANTAS = "cant_plantas"
    const val CAMPO_FOTO_CULTIVO = "img_cultivo"

    //Constantes campos tabla jornal
    const val TABLA_JORNAL = "jornal"
    const val CAMPO_ID_JORNAL = "id_jornal"
    const val CAMPO_CULTIVO_JORNAL = "id_cultivo"
    const val CAMPO_DIA_JORNAL = "dia_jornal"
    const val CAMPO_MES_JORNAL = "mes_jornal"
    const val CAMPO_AÑO_JORNAL = "año_jornal"
    const val CAMPO_CANT_JORNAL = "cant_jornal"
    const val CAMPO_ACTV_JORNAL = "actv_jornal"
    const val CAMPO_PRECIO_JORNAL = "precio_jornal"

    //Constantes campos tabla insumos
    const val TABLA_INSUMOS = "insumo"
    const val CAMPO_ID_INSUMO = "id_insumo"
    const val CAMPO_CULTIVO_INSUMO = "id_cultivo"
    const val CAMPO_DIA_INSUMO = "dia_insumo"
    const val CAMPO_MES_INSUMO = "mes_insumo"
    const val CAMPO_AÑO_INSUMO = "año_insumo"
    const val CAMPO_NOMBRE_INSUMO = "nombre_insumo"
    const val CAMPO_PRECIO_INSUMO = "precio_insumo"
    const val CAMPO_CANT_INSUMO = "cant_insumo"
    const val CAMPO_CANT_USADO_INSUMO = "cant_usado"

    //Constantes campos tabla cosecha
    const val TABLA_COSECHA = "cosecha"
    const val CAMPO_ID_COSECHA = "id_cosecha"
    const val CAMPO_CULTIVO_COSECHA = "id_cultivo"
    const val CAMPO_DIA_COSECHA = "dia_cosecha"
    const val CAMPO_MES_COSECHA = "mes_cosecha"
    const val CAMPO_AÑO_COSECHA = "año_cosecha"
    const val CAMPO_LIBRAS_EXTRA = "libras_extra"
    const val CAMPO_PRECIO_EXTRA = "precio_extra"
    const val CAMPO_LIBRAS_PRIMERA = "libras_primera"
    const val CAMPO_PRECIO_PRIMERA = "precio_primera"
    const val CAMPO_LIBRAS_SEGUNDA = "libras_segunda"
    const val CAMPO_PRECIO_SEGUNDA = "precio_segunda"
    const val CAMPO_LIBRAS_TERCERA = "libras_tercera"
    const val CAMPO_PRECIO_TERCERA = "precio_tercera"
    const val CAMPO_LIBRAS_CUARTA = "libras_cuarta"
    const val CAMPO_PRECIO_CUARTA = "precio_cuarta"
    const val CAMPO_LIBRAS_QUINTA = "libras_quinta"
    const val CAMPO_PRECIO_QUINTA = "precio_quinta"
    const val CAMPO_LIBRAS_MADURA = "libras_madura"
    const val CAMPO_PRECIO_MADURA = "precio_madura"
    const val CAMPO_OBSERVACION_COSECHA = "observacion_cosecha"
    const val CAMPO_IMG_FACTURA = "img_factura"


    const val CREAR_TABLA_PERSONA =
        //"DROP TABLE" + TABLA_PERSONA+";" +
        "CREATE TABLE " + TABLA_PERSONA + " (" + CAMPO_ID_PERSONA + " INTEGER PRIMARY KEY, " + CAMPO_NOMBRE_PERSONA + " TEXT);"
    const val CREAR_TABLA_INGRESOS =
        //"create table ingreso_personal (id_ingreso integer primary key, concepto text, dato text, precio integer);"
        "CREATE TABLE " + TABLA_INGRESO_PERSONAL + " (" + CAMPO_ID_INGRESO + " INTEGER PRIMARY KEY, " + CAMPO_DIA_INGRESO + " INTEGER, " + CAMPO_MES_INGRESO + " INTEGER, " + CAMPO_AÑO_INGRESO + " INTEGER, "+ CAMPO_CONCEPTO_INGRESO +" TEXT, " + CAMPO_PRECIO_INGRESO + " INTEGER, " + CAMPO_PERSONA_INGRESO +" INTEGER REFERENCES " + TABLA_PERSONA + "("+CAMPO_ID_PERSONA+") ON DELETE NO ACTION ON UPDATE CASCADE );"
    const val CREAR_TABLA_GASTOS =
        //"DROP TABLE" + TABLA_FINANZAS+";" +
        "CREATE TABLE " + TABLA_GASTO_PERSONAL + " (" + CAMPO_ID_GASTO + " INTEGER PRIMARY KEY, " + CAMPO_DIA_GASTO + " INTEGER, " + CAMPO_MES_GASTO + " INTEGER, " + CAMPO_AÑO_GASTO + " INTEGER, "+ CAMPO_CONCEPTO_GASTO +" TEXT, " + CAMPO_PRECIO_GASTO + " INTEGER, " + CAMPO_PERSONA_GASTO+ " INTEGER REFERENCES " + TABLA_PERSONA + "("+CAMPO_ID_PERSONA+") ON DELETE NO ACTION ON UPDATE CASCADE);"
    const val CREAR_TABLA_CULTIVO =
        //"DROP TABLE" + TABLA_CULTIVO+";" +
        "CREATE TABLE " + TABLA_CULTIVO + " (" + CAMPO_ID_CULTIVO + " INTEGER PRIMARY KEY, " + CAMPO_NOMBRE_CULTIVO + " TEXT, " +  CAMPO_CANT_PLANTAS + " INTEGER, " + CAMPO_FOTO_CULTIVO + " BLOB);"
    const val CREAR_TABLA_JORNAL =
        //"DROP TABLE" + TABLA_JORNAL+";" +
        "CREATE TABLE " + TABLA_JORNAL + " (" + CAMPO_ID_JORNAL + " INTEGER PRIMARY KEY, " + CAMPO_DIA_JORNAL + " INTEGER, " + CAMPO_MES_JORNAL + " INTEGER, " + CAMPO_AÑO_JORNAL + " INTEGER, "+ CAMPO_CANT_JORNAL + " INTEGER, " + CAMPO_ACTV_JORNAL + " TEXT, " + CAMPO_PRECIO_JORNAL + " INTEGER, " + CAMPO_CULTIVO_JORNAL+ " INTEGER REFERENCES " + TABLA_CULTIVO + "("+ CAMPO_ID_CULTIVO+") ON DELETE NO ACTION ON UPDATE CASCADE);"
    const val CREAR_TABLA_COSECHA =
        //"DROP TABLE" + TABLA_COSECHA+";" +
        "CREATE TABLE " + TABLA_COSECHA + " (" + CAMPO_ID_COSECHA + " INTEGER PRIMARY KEY, " + CAMPO_DIA_COSECHA + " INTEGER, " + CAMPO_MES_COSECHA + " INTEGER, " + CAMPO_AÑO_COSECHA + " INTEGER, "+ CAMPO_LIBRAS_EXTRA + " INTEGER, " + CAMPO_PRECIO_EXTRA + " INTEGER, " + CAMPO_LIBRAS_PRIMERA + " INTEGER, " + CAMPO_PRECIO_PRIMERA + " INTEGER, " + CAMPO_LIBRAS_SEGUNDA + " INTEGER, " + CAMPO_PRECIO_SEGUNDA + " INTEGER, " +
                CAMPO_LIBRAS_TERCERA + " INTEGER, " + CAMPO_PRECIO_TERCERA + " INTEGER, " + CAMPO_LIBRAS_CUARTA + " INTEGER, " + CAMPO_PRECIO_CUARTA + " INTEGER, " + CAMPO_LIBRAS_QUINTA + " INTEGER, " + CAMPO_LIBRAS_MADURA + " INTEGER, " + CAMPO_PRECIO_MADURA + " INTEGER, "+ CAMPO_PRECIO_QUINTA + " INTEGER, "+ CAMPO_OBSERVACION_COSECHA + " TEXT, " + CAMPO_IMG_FACTURA + " BLOB, "+ CAMPO_CULTIVO_COSECHA+ " INTEGER REFERENCES " + TABLA_CULTIVO + "("+ CAMPO_ID_CULTIVO+") ON DELETE NO ACTION ON UPDATE CASCADE);"
    const val CREAR_TABLA_INSUMOS =
        //"DROP TABLE" + TABLA_INSUMOS+";" +
        "CREATE TABLE " + TABLA_INSUMOS + " (" + CAMPO_ID_INSUMO + " INTEGER PRIMARY KEY, " + CAMPO_DIA_INSUMO + " INTEGER, " + CAMPO_MES_INSUMO + " INTEGER, " + CAMPO_AÑO_INSUMO + " INTEGER, " + CAMPO_NOMBRE_INSUMO + " TEXT, " + CAMPO_PRECIO_INSUMO + " INTEGER, " + CAMPO_CANT_INSUMO + " INTEGER, " + CAMPO_CANT_USADO_INSUMO+ " INTEGER, " + CAMPO_CULTIVO_INSUMO+ " INTEGER REFERENCES " + TABLA_CULTIVO + "("+ CAMPO_ID_CULTIVO+") ON DELETE NO ACTION ON UPDATE CASCADE);"


    fun consultarListaPersonas(actividad: Activity) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var persona: PersonaVo
        listaPersonas = ArrayList<PersonaVo>()

        //select * from usuarios
        val cursor = db.rawQuery("SELECT * FROM " + TABLA_PERSONA, null)
        while (cursor.moveToNext()) {
            persona = PersonaVo()
            persona.id= cursor.getInt(0)
            persona.nombre = cursor.getString(1)
            listaPersonas!!.add(persona)
        }
        db.close()
    }

    fun consultarListaCultivos(actividad: Activity) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var cultivo: CultivoVo
        listaCultivos = ArrayList<CultivoVo>()

        //select * from usuarios
        val cursor = db.rawQuery("SELECT * FROM " + TABLA_CULTIVO, null)
        while (cursor.moveToNext()) {
            cultivo = CultivoVo()
            cultivo.id= cursor.getInt(0)
            cultivo.nombre = cursor.getString(1)
            cultivo.cantidad= cursor.getInt(2)
            cultivo.imgCultivo= cursor.getBlob(3)
            listaCultivos!!.add(cultivo)

        }/*
        for (item in listaCultivos!!) {
            println("ID: "+item.id + "Nombre: " + item.nombre)
        }*/
        db.close()
    }

    //llenar lista de Ingresos Personales
    fun consultarListaIngresoPersonal(actividad: Activity) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var ingreso: IngresoPersonalVo
        listaIngresoPersonal = ArrayList<IngresoPersonalVo>()

        //select * from usuarios
        val cursor = db.rawQuery("select mes_ingreso, año_ingreso, concepto, sum(ingreso_personal.precio) as ingresos " +
                " from ingreso_personal " +
                " WHERE id_persona is 1 " +
                " GROUP by año_ingreso, mes_ingreso", null)
        while (cursor.moveToNext()) {
            ingreso = IngresoPersonalVo()
            //ingreso.id= cursor.getInt(0)
            ingreso.mes = cursor.getString(0)
            ingreso.año = cursor.getString(1)
            ingreso.concepto= cursor.getString(2)
            ingreso.precio= cursor.getInt(3)
            listaIngresoPersonal!!.add(ingreso)

        }/*
        for (item in listaCultivos!!) {
            println("ID: "+item.id + "Nombre: " + item.nombre)
        }*/
        db.close()
    }


    //llenar lista de Gastos Personales
    fun consultarListaGastoPersonal(actividad: Activity) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var gasto: GastoPersonalVo
        listaGastoPersonal = ArrayList<GastoPersonalVo>()

        //select * from usuarios
        val cursor = db.rawQuery("select mes_gasto, año_gasto, concepto, sum(gasto_personal.precio) as gastos " +
                " from gasto_personal " +
                " WHERE id_persona is 1 " +
                " GROUP by año_gasto, mes_gasto", null)
        while (cursor.moveToNext()) {
            gasto = GastoPersonalVo()
            gasto.mes = cursor.getString(0)
            gasto.año = cursor.getString(1)
            gasto.concepto= cursor.getString(2)
            gasto.precio= cursor.getInt(3)
            listaGastoPersonal!!.add(gasto)

        }/*
        for (item in listaCultivos!!) {
            println("ID: "+item.id + "Nombre: " + item.nombre)
        }*/
        db.close()
    }


    //Beneficio Personal por Mes
    fun calcularBeneficioPersonal(actividad: Activity) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var beneficio: BeneficioPersonalVo

        /*
        //Prueba 1
        /*val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val fechaActual = LocalDateTime.now().format(formato) //comparar fecha actual con la de la base de datos*/
        //Prueba 2
        val formato = SimpleDateFormat("yyyy-MM-dd")
        val date = Date()
        val fechaActual = formato.format(date) //comparar fecha actual con la de la base de datos
        //Prueba 3
        val calendar = Calendar.getInstance()
        val mesActual = Calendar.MONTH
        println("Mes Actual")
        println(mesActual)
*/
        //Beneficio Personal
        listaBeneficioPersonal = ArrayList<BeneficioPersonalVo>()
        beneficio = BeneficioPersonalVo()


        //Consulta para registros de ingresos y gastos de personas
        val cursor = db.rawQuery("SELECT *\n" +
                "FROM (SELECT mes_ingreso, mes_gasto, año_ingreso, año_gasto, ifnull(ingresos,0) as ingreso, ifnull(gastos,0) as gasto, (ifnull(ingresos,0))-(ifnull(gastos,0))as beneficio\n" +
                "from (\n" +
                "  (select sum(ingreso_personal.precio) as ingresos, año_ingreso, mes_ingreso\n" +
                "\tfrom ingreso_personal\n" +
                "\tWHERE id_persona is \n" + DialogoGesPersona.personaSeleccionada.id+
                "\tGROUP by año_ingreso, mes_ingreso) \n" +
                "  LEFT JOIN\n" +
                "  (select sum(gasto_personal.precio) as gastos, año_gasto, mes_gasto \n" +
                "\tfrom gasto_personal\n" +
                "\tWHERE id_persona is \n" + DialogoGesPersona.personaSeleccionada.id+
                "\tGROUP by año_gasto, mes_gasto) \n" +
                "  ON año_ingreso = año_gasto and mes_ingreso=mes_gasto)\n" +
                "  UNION\n" +
                "  SELECT mes_ingreso, mes_gasto, año_ingreso, año_gasto, ifnull(ingresos,0) as ingreso, ifnull(gastos,0) as gasto, (ifnull(ingresos,0))-(ifnull(gastos,0))as beneficio\n" +
                "from ((select sum(gasto_personal.precio) as gastos, año_gasto, mes_gasto\n" +
                "\tfrom gasto_personal\n" +
                "\tWHERE id_persona is \n" + DialogoGesPersona.personaSeleccionada.id+
                "\tGROUP by año_gasto, mes_gasto) \n" +
                "  LEFT JOIN \n" +
                "  (select sum(ingreso_personal.precio) as ingresos, año_ingreso, mes_ingreso\n" +
                "\tfrom ingreso_personal\n" +
                "\tWHERE id_persona is \n" + DialogoGesPersona.personaSeleccionada.id+
                "\tGROUP by año_ingreso, mes_ingreso) \n" +
                "  ON año_ingreso = año_gasto and mes_ingreso=mes_gasto))\n" +
                "  ORDER BY año_ingreso, mes_gasto,año_gasto,mes_ingreso", null)
        while (cursor.moveToNext()) {

            beneficio = BeneficioPersonalVo()
            //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
            // y le asigno la respuesta cuando sea diferente de nulo
            if (cursor.getString(0) != null){
                beneficio.mes = cursor.getString(0)
            }else if(cursor.getString(1) != null){
                beneficio.mes = cursor.getString(1)
            }
            if (cursor.getString(2) != null){
                beneficio.año= cursor.getString(2)
            }else if(cursor.getString(3) != null){
                beneficio.año= cursor.getString(3)
            }
            beneficio.ingresos= cursor.getInt(4)
            beneficio.gastos= cursor.getInt(5)
            beneficio.beneficio= cursor.getInt(6)
            listaBeneficioPersonal!!.add(beneficio)


            /*consultarListaIngresoPersonal(actividad)
            consultarListaGastoPersonal(actividad)


                for (iIn in listaIngresoPersonal!!){
                    println("Lista de mes: ")
                    println(iIn.mes +" "+ iIn.año + " "+ iIn.precio)
                    for (iG in listaGastoPersonal!!){
                        println("Gasto: ")
                        println(iG.mes +" "+ iG.año + " "+ iG.precio)
                        if (iIn.año == iG.año && iIn.mes == iG.mes){
                            if (iIn.mes != null){
                                beneficio.mes = iIn.mes
                            }else if(iG.mes != null){
                                beneficio.mes = iG.mes
                            }
                            if (iIn.año != null){
                                beneficio.año = iIn.año
                            }else if(iG.año != null){
                                beneficio.año = iG.año
                            }
                            beneficio.ingresos= iIn.precio
                            beneficio.gastos= iG.precio
                            beneficio.beneficio= iIn.precio-iG.precio
                            listaBeneficioPersonal!!.add(beneficio)
                        }
                    }
                }*/

            println("Beneficio: ")
            for (i in listaBeneficioPersonal!!){
                println("------------------")
                println(i.mes + " "+ i.año + " "+i.ingresos +" "+ i.gastos+ " "+i.beneficio)
            }



        }/*
        for (item in listaCultivos!!) {
            println("ID: "+item.id + "Nombre: " + item.nombre)
        }*/
        db.close()
    }

    fun calcularBeneficioPersonalAct(actividad: Activity) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var beneficio: BeneficioPersonalVo


        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        println("Mes Actual: $mesActual")
        println("Año Actual: $añoActual")
        listaBeneficioPersonal = ArrayList<BeneficioPersonalVo>()
        beneficio = BeneficioPersonalVo()

        val cursor = db.rawQuery("SELECT *\n" +
                "FROM (SELECT mes_ingreso, mes_gasto, año_ingreso, año_gasto, ifnull(ingresos,0) as ingreso, ifnull(gastos,0) as gasto, (ifnull(ingresos,0))-(ifnull(gastos,0))as beneficio\n" +
                "from (\n" +
                "  (select sum(ingreso_personal.precio) as ingresos, año_ingreso, mes_ingreso\n" +
                "\tfrom ingreso_personal\n" +
                "\tWHERE id_persona is \n" + DialogoGesPersona.personaSeleccionada.id+
                "\tGROUP by año_ingreso, mes_ingreso) \n" +
                "  LEFT JOIN\n" +
                "  (select sum(gasto_personal.precio) as gastos, año_gasto, mes_gasto \n" +
                "\tfrom gasto_personal\n" +
                "\tWHERE id_persona is \n" + DialogoGesPersona.personaSeleccionada.id+
                "\tGROUP by año_gasto, mes_gasto) \n" +
                "  ON año_ingreso = año_gasto and mes_ingreso=mes_gasto)\n" +
                "  UNION\n" +
                "  SELECT mes_ingreso, mes_gasto, año_ingreso, año_gasto, ifnull(ingresos,0) as ingreso, ifnull(gastos,0) as gasto, (ifnull(ingresos,0))-(ifnull(gastos,0))as beneficio\n" +
                "from ((select sum(gasto_personal.precio) as gastos, año_gasto, mes_gasto\n" +
                "\tfrom gasto_personal\n" +
                "\tWHERE id_persona is \n" + DialogoGesPersona.personaSeleccionada.id+
                "\tGROUP by año_gasto, mes_gasto) \n" +
                "  LEFT JOIN \n" +
                "  (select sum(ingreso_personal.precio) as ingresos, año_ingreso, mes_ingreso\n" +
                "\tfrom ingreso_personal\n" +
                "\tWHERE id_persona is \n" + DialogoGesPersona.personaSeleccionada.id+
                "\tGROUP by año_ingreso, mes_ingreso) \n" +
                "  ON año_ingreso = año_gasto and mes_ingreso=mes_gasto))\n" +
                " WHERE año_gasto IS " + añoActual +" and mes_gasto is " +mesActual+" or año_ingreso IS " + añoActual +" and mes_ingreso is " +mesActual+
                "  ORDER BY año_ingreso, mes_gasto,año_gasto,mes_ingreso", null)
        while (cursor.moveToNext()) {

            beneficio = BeneficioPersonalVo()
            //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
            // y le asigno la respuesta cuando sea diferente de nulo
            if (cursor.getString(0) != null){
                beneficio.mes = cursor.getString(0)
            }else if(cursor.getString(1) != null){
                beneficio.mes = cursor.getString(1)
            }
            if (cursor.getString(2) != null){
                beneficio.año= cursor.getString(2)
            }else if(cursor.getString(3) != null){
                beneficio.año= cursor.getString(3)
            }
            beneficio.ingresos= cursor.getInt(4)
            beneficio.gastos= cursor.getInt(5)
            beneficio.beneficio= cursor.getInt(6)
            listaBeneficioPersonal!!.add(beneficio)

            println("Beneficio: ")
            for (i in listaBeneficioPersonal!!){
                println("------------------")
                println(i.mes + " "+ i.año + " "+i.ingresos +" "+ i.gastos+ " "+i.beneficio)
            }
        }
        db.close()
    }

    /*fun calcularBeneficioCultivoAct(actividad: Activity) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var beneficioCultivo: BeneficioCultivoVo

        //Beneficio de Cultivo
        listaBeneficioCultivo = ArrayList<BeneficioCultivoVo>()
        beneficioCultivo = BeneficioCultivoVo()

        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        println("Mes Actual: $mesActual")
        println("Año Actual: $añoActual")

        val cursor = db.rawQuery("SELECT *\n" +
                "FROM(\n" +
                "  SELECT mes_cosecha, mes_jornal, mes_insumo, año_cosecha, año_jornal, año_insumo, ifnull(gasto_jornal,0) as gasto_jornal, ifnull(gasto_insumo,0) as gasto_insumo, ingreso_cosecha, (ifnull(ingreso_cosecha,0))-((ifnull(gasto_jornal,0))+(ifnull(gasto_insumo,0)))as beneficio\n" +
                "from (\n" +
                "  (select sum(jornal.precio_jornal*jornal.cant_jornal) as gasto_jornal, año_jornal, mes_jornal\n" +
                "\tfrom jornal\n" +
                "\tWHERE id_cultivo is "+ DialogoGesCultivo.cultivoSeleccionado.id+" \n" +
                "\tGROUP by  mes_jornal)\n" +
                "  \n" +
                "  LEFT JOIN\n" +
                "  (\n" +
                "    \n" +
                "    (select sum(cosecha.precio_cosecha*cosecha.libras_cosecha) as ingreso_cosecha, año_cosecha, mes_cosecha\n" +
                "\tfrom cosecha\n" +
                "\tWHERE id_cultivo is "+ DialogoGesCultivo.cultivoSeleccionado.id+" \n" +
                "\tGROUP by cosecha.año_cosecha, cosecha.mes_cosecha)\n" +
                "    \n" +
                "  \n" +
                "    LEFT JOIN\n" +
                "  \n" +
                "  (select sum((insumo.precio_insumo/insumo.cant_insumo)*insumo.cant_usado) as gasto_insumo, año_insumo, mes_insumo\n" +
                "\tfrom insumo\n" +
                "\tWHERE id_cultivo is "+ DialogoGesCultivo.cultivoSeleccionado.id+" \n" +
                "\tGROUP by año_insumo, mes_insumo)\n" +
                "  )\n" +
                "  ON año_insumo = año_cosecha and mes_insumo=mes_cosecha\n" +
                ")\n" +
                "  UNION\n" +
                "  \n" +
                "  SELECT mes_cosecha, mes_jornal, mes_insumo, año_cosecha, año_jornal, año_insumo, ifnull(gasto_jornal,0) as gasto_jornal, ifnull(gasto_insumo,0) as gasto_insumo, ingreso_cosecha, (ifnull(ingreso_cosecha,0))-((ifnull(gasto_jornal,0))+(ifnull(gasto_insumo,0)))as beneficio\n" +
                "from (\n" +
                "  (select sum(cosecha.precio_cosecha*cosecha.libras_cosecha) as ingreso_cosecha, año_cosecha, mes_cosecha\n" +
                "\tfrom cosecha\n" +
                "\tWHERE id_cultivo is "+ DialogoGesCultivo.cultivoSeleccionado.id+" \n" +
                "\tGROUP by cosecha.año_cosecha, cosecha.mes_cosecha)\n" +
                "  LEFT JOIN\n" +
                "  (\n" +
                "    (select sum(jornal.precio_jornal*jornal.cant_jornal) as gasto_jornal, año_jornal,año_jornal, mes_jornal\n" +
                "\tfrom jornal\n" +
                "\tWHERE id_cultivo is "+ DialogoGesCultivo.cultivoSeleccionado.id+" \n" +
                "\tGROUP by año_jornal, mes_jornal)\n" +
                "  \n" +
                "    LEFT JOIN\n" +
                "  \n" +
                "  (select sum((insumo.precio_insumo/insumo.cant_insumo)*insumo.cant_usado) as gasto_insumo, año_insumo, mes_insumo\n" +
                "\tfrom insumo\n" +
                "\tWHERE id_cultivo is "+ DialogoGesCultivo.cultivoSeleccionado.id+" \n" +
                "\tGROUP by año_insumo, mes_insumo)\n" +
                "    ON año_insumo = año_jornal and mes_insumo=mes_jornal\n" +
                "  )\n" +
                "  ON año_insumo = año_cosecha and mes_insumo=mes_cosecha\n" +
                ")\n" +
                ")\n" +
                "WHERE año_insumo IS " + añoActual + " and mes_insumo IS " +mesActual+" OR año_cosecha IS " + añoActual + " and mes_cosecha IS " +mesActual+"  OR  año_jornal IS " + añoActual +" and mes_jornal IS " +mesActual+" \n"
            , null)
        while (cursor.moveToNext()) {

            beneficioCultivo = BeneficioCultivoVo()
            //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
            // y le asigno la respuesta cuando sea diferente de nulo
            if (cursor.getString(0) != null){
                beneficioCultivo.mes = cursor.getString(0)
            }else if(cursor.getString(1) != null){
                beneficioCultivo.mes = cursor.getString(1)
            }else if(cursor.getString(2) != null){
                beneficioCultivo.mes = cursor.getString(2)
            }
            if (cursor.getString(3) != null){
                beneficioCultivo.año= cursor.getString(3)
            }else if(cursor.getString(4) != null){
                beneficioCultivo.año= cursor.getString(4)
            }else if(cursor.getString(5) != null){
                beneficioCultivo.año= cursor.getString(5)
            }
            beneficioCultivo.gastoJornal= cursor.getInt(6)
            beneficioCultivo.gastoInsumo= cursor.getInt(7)
            beneficioCultivo.ingresos= cursor.getInt(8)
            beneficioCultivo.beneficio= cursor.getInt(9)
            listaBeneficioCultivo!!.add(beneficioCultivo)

            println("Beneficio: ")
            for (i in listaBeneficioCultivo!!){
                println("------------------")
                println(i.mes + " "+ i.año + " "+i.ingresos +" "+ i.gastoJornal+ " "+ i.gastoInsumo+ " "+i.beneficio)
            }
        }
        db.close()

    }

     */


    fun calcularBeneficioCultivo(actividad: Activity, mes: Int, año: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var beneficioCultivo: BeneficioCultivoVo

        //Beneficio de Cultivo

        listaBeneficioCultivo = ArrayList<BeneficioCultivoVo>()

        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        println("Mes Actual: $mesActual")
        println("Año Actual: $añoActual")

        for (i in 1..12){
            val cursor = db.rawQuery("SELECT Gasto_jornal,Gasto_insumo,(Gasto_jornal+Gasto_insumo) Gasto,Ingreso,ifnull((ingreso-(gasto_jornal+gasto_insumo)),0) as Beneficio,Extra,Primera,Segunda,Tercera,Cuarta,Quinta,Madura\n" +
                    "FROM(\n" +
                    "(SELECT ifnull(sum(jornal.precio_jornal*jornal.cant_jornal),0) as Gasto_jornal\n" +
                    "from jornal\n" +
                    "WHERE año_jornal = "+año+" and mes_jornal = "+i+")\n" +
                    ",\n" +
                    "(SELECT ifnull(sum((insumo.precio_insumo/insumo.cant_insumo)*insumo.cant_usado),0) as Gasto_insumo\n" +
                    "FROM insumo\n" +
                    "WHERE año_insumo = "+año+" and mes_insumo = "+i+")\n" +
                    ",\n" +
                    "(select ifnull((sum(libras_extra*precio_extra)+sum(libras_primera*precio_primera) +\n" +
                    "        sum(libras_segunda*precio_segunda) +sum(libras_tercera*precio_tercera)+\n" +
                    "sum(libras_cuarta*precio_cuarta)+sum(libras_quinta*precio_quinta)+\n" +
                    "sum(libras_madura*precio_madura)),0) AS Ingreso, sum(libras_extra) Extra, sum(libras_primera) Primera,\n" +
                    " sum(libras_segunda) Segunda, sum(libras_tercera) Tercera, sum(libras_cuarta) Cuarta, sum(libras_quinta) Quinta, \n" +
                    " sum(libras_madura) Madura\n" +
                    "from cosecha\n" +
                    "WHERE año_cosecha = "+año+" and mes_cosecha = "+i+")\n" +
                    ")", null)
            while (cursor.moveToNext()) {

                beneficioCultivo = BeneficioCultivoVo()
                //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
                // y le asigno la respuesta cuando sea diferente de nulo

                beneficioCultivo.gastoJornal= cursor.getInt(0)
                beneficioCultivo.gastoInsumo= cursor.getInt(1)
                beneficioCultivo.gastos= cursor.getInt(2)
                beneficioCultivo.ingresos= cursor.getInt(3)
                beneficioCultivo.beneficio= cursor.getInt(4)
                beneficioCultivo.extra= cursor.getInt(5)
                beneficioCultivo.primera= cursor.getInt(6)
                beneficioCultivo.segunda= cursor.getInt(7)
                beneficioCultivo.tercera= cursor.getInt(8)
                beneficioCultivo.cuarta= cursor.getInt(9)
                beneficioCultivo.quinta= cursor.getInt(10)
                beneficioCultivo.madura= cursor.getInt(11)
                beneficioCultivo.año= año
                beneficioCultivo.mes= i
                listaBeneficioCultivo!!.add(beneficioCultivo)

            }
        }

        println("Beneficio: ")
        for (i in listaBeneficioCultivo!!){
            println("------------------")
            println("Lista de Beneficios: "+i.ingresos +" "+ i.gastos+ " "+i.beneficio)
        }
        db.close()
    }

    fun consultarJornalesMes(actividad: Activity, mes: Int, año: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var jornalCultivo: JornalCultivoVo

        //Beneficio de Cultivo
        listaJornalCultivo = ArrayList<JornalCultivoVo>()


        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        println("Mes Actual: $mesActual")
        println("Año Actual: $añoActual")

        val cursor = db.rawQuery("select dia_jornal, mes_jornal, año_jornal,actv_jornal, cant_jornal, precio_jornal, (cant_jornal*precio_jornal)as Gasto_Total\n" +
                "from jornal\n" +
                "where mes_jornal = "+mes+" and año_jornal= "+año, null)
        while (cursor.moveToNext()) {
            jornalCultivo = JornalCultivoVo()

            //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
            // y le asigno la respuesta cuando sea diferente de nulo

            jornalCultivo.dia= cursor.getInt(0)
            jornalCultivo.mes= cursor.getInt(1)
            jornalCultivo.año= cursor.getInt(2)
            jornalCultivo.actividad= cursor.getString(3)
            jornalCultivo.cantidadJornal= cursor.getInt(4)
            jornalCultivo.precioJornal= cursor.getInt(5)
            jornalCultivo.gastoTotalJornal= cursor.getInt(6)

            listaJornalCultivo!!.add(jornalCultivo)
        }
        db.close()
    }

    fun consultarInsumosMes(actividad: Activity, mes: Int, año: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var insumoCultivo: InsumoCultivoVo

        //Beneficio de Cultivo
        listaInsumoCultivo = ArrayList<InsumoCultivoVo>()


        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        println("Mes Actual: $mesActual")
        println("Año Actual: $añoActual")

        val cursor = db.rawQuery("select dia_insumo, mes_insumo, año_insumo,nombre_insumo, precio_insumo, cant_usado, ((insumo.precio_insumo/insumo.cant_insumo)*insumo.cant_usado)as Gasto_Total\n" +
                "from insumo\n" +
                "where mes_insumo = "+mes+" and año_insumo= "+año, null)
        while (cursor.moveToNext()) {
            insumoCultivo = InsumoCultivoVo()

            //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
            // y le asigno la respuesta cuando sea diferente de nulo

            insumoCultivo.dia= cursor.getInt(0)
            insumoCultivo.mes= cursor.getInt(1)
            insumoCultivo.año= cursor.getInt(2)
            insumoCultivo.nombreInsumo= cursor.getString(3)
            insumoCultivo.precioInsumo= cursor.getInt(4)
            insumoCultivo.gastoTotalInsumo= cursor.getInt(5)

            listaInsumoCultivo!!.add(insumoCultivo)
        }
        db.close()
    }

    fun consultarCosechaMes(actividad: Activity, mes: Int, año: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var cosechaCultivo: CosechaCultivoVo

        //Beneficio de Cultivo
        listaCosechaCultivo = ArrayList<CosechaCultivoVo>()

        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        println("Mes Actual: $mesActual")
        println("Año Actual: $añoActual")

        val cursor = db.rawQuery("select  dia_cosecha, mes_cosecha, año_cosecha,libras_extra Extra, libras_primera Primera,libras_segunda Segunda, libras_tercera Tercera, libras_cuarta Cuarta, libras_quinta Quinta,libras_madura Madura,precio_extra, precio_primera,precio_segunda, precio_tercera, precio_cuarta, precio_quinta, \n" +
                " precio_madura, ((libras_extra*precio_extra)+(libras_primera*precio_primera) +(libras_segunda*precio_segunda) +(libras_tercera*precio_tercera)+(libras_cuarta*precio_cuarta)+(libras_quinta*precio_quinta)+(libras_madura*precio_madura)) AS TotalCosecha , img_factura \n" +
                "from cosecha\n" +
                "where mes_cosecha = "+mes+" and año_cosecha = "+año, null)
        while (cursor.moveToNext()) {
            cosechaCultivo = CosechaCultivoVo()

            //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
            // y le asigno la respuesta cuando sea diferente de nulo

            cosechaCultivo.dia= cursor.getInt(0)
            cosechaCultivo.mes= cursor.getInt(1)
            cosechaCultivo.año= cursor.getInt(2)
            cosechaCultivo.extra= cursor.getInt(3)
            cosechaCultivo.primera= cursor.getInt(4)
            cosechaCultivo.segunda= cursor.getInt(5)
            cosechaCultivo.tercera= cursor.getInt(6)
            cosechaCultivo.cuarta= cursor.getInt(7)
            cosechaCultivo.quinta= cursor.getInt(8)
            cosechaCultivo.madura= cursor.getInt(9)
            cosechaCultivo.precioExtra= cursor.getInt(10)
            cosechaCultivo.precioPrimera= cursor.getInt(11)
            cosechaCultivo.precioPrimera= cursor.getInt(12)
            cosechaCultivo.precioTercera= cursor.getInt(13)
            cosechaCultivo.precioCuarta= cursor.getInt(14)
            cosechaCultivo.precioQuinta= cursor.getInt(15)
            cosechaCultivo.precioMadura= cursor.getInt(16)
            cosechaCultivo.dineroTotal= cursor.getInt(17)
            cosechaCultivo.imgFactura = cursor.getBlob(18)

            listaCosechaCultivo!!.add(cosechaCultivo)
        }
        db.close()
    }

    fun consultarCosechaDia(actividad: Activity, mes: Int, año: Int, dia: Int, idCultivo:Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var cosechaCultivo: CosechaCultivoVo

        //Beneficio de Cultivo
        listaCosechaCultivo = ArrayList<CosechaCultivoVo>()

        val dateFormat = SimpleDateFormat("MM")
        val mesActual = dateFormat.format(Date())

        val dateFormatY = SimpleDateFormat("yyyy")
        val añoActual = dateFormatY.format(Date())

        println("Mes Actual: $mesActual")
        println("Año Actual: $añoActual")

        val cursor = db.rawQuery("select dia_cosecha, mes_cosecha, año_cosecha,libras_extra Extra, libras_primera Primera,libras_segunda Segunda, libras_tercera Tercera, libras_cuarta Cuarta, libras_quinta Quinta,libras_madura Madura,precio_extra, precio_primera,precio_segunda, precio_tercera, precio_cuarta, precio_quinta,\n" +
                "precio_madura, ((libras_extra*precio_extra)+(libras_primera*precio_primera) +(libras_segunda*precio_segunda) +(libras_tercera*precio_tercera)+(libras_cuarta*precio_cuarta)+(libras_quinta*precio_quinta)+(libras_madura*precio_madura)) AS TotalCosecha\n" +
                "from cosecha \n" +
                "WHERE año_cosecha = "+año+" and mes_cosecha = "+mes+" and dia_cosecha = "+dia+" and id_cultivo = " + idCultivo, null)
        while (cursor.moveToNext()) {
            cosechaCultivo = CosechaCultivoVo()

            //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
            // y le asigno la respuesta cuando sea diferente de nulo

            cosechaCultivo.dia= cursor.getInt(0)
            cosechaCultivo.mes= cursor.getInt(1)
            cosechaCultivo.año= cursor.getInt(2)
            cosechaCultivo.extra= cursor.getInt(3)
            cosechaCultivo.primera= cursor.getInt(4)
            cosechaCultivo.segunda= cursor.getInt(5)
            cosechaCultivo.tercera= cursor.getInt(6)
            cosechaCultivo.cuarta= cursor.getInt(7)
            cosechaCultivo.quinta= cursor.getInt(8)
            cosechaCultivo.madura= cursor.getInt(9)
            cosechaCultivo.precioExtra= cursor.getInt(10)
            cosechaCultivo.precioPrimera= cursor.getInt(11)
            cosechaCultivo.precioPrimera= cursor.getInt(12)
            cosechaCultivo.precioTercera= cursor.getInt(13)
            cosechaCultivo.precioCuarta= cursor.getInt(14)
            cosechaCultivo.precioQuinta= cursor.getInt(15)
            cosechaCultivo.precioMadura= cursor.getInt(16)
            cosechaCultivo.dineroTotal= cursor.getInt(17)

            listaCosechaCultivo!!.add(cosechaCultivo)
        }
        db.close()
    }

}