package com.example.fresaproyecto.clases

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import com.example.fresaproyecto.clases.vo.*
import kotlin.collections.ArrayList


object Utilidades {
    var listaPersonas: ArrayList<PersonaVo>? = null
    var listaCultivos: ArrayList<CultivoVo>? = null
    var listaIngresoPersonal: ArrayList<IngresoPersonalVo>? = null
    var listaGastoPersonal: ArrayList<GastoPersonalVo>? = null
    var listaBeneficioPersonal: ArrayList<BeneficioPersonalVo>? = null
    var listaBeneficioCultivo: ArrayList<BeneficioCultivoVo>? = null
    var listaJornalCultivo: ArrayList<JornalCultivoVo>? = null
    var listaInsumoCultivo: ArrayList<InsumoCultivoVo>? = null
    var listaCosechaCultivo: ArrayList<CosechaCultivoVo>? = null

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
    const val CAMPO_UNIDAD_INSUMO = "unidad_insumo"

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
        "CREATE TABLE " + TABLA_INGRESO_PERSONAL + " (" + CAMPO_ID_INGRESO + " INTEGER PRIMARY KEY, " + CAMPO_DIA_INGRESO + " INTEGER, " + CAMPO_MES_INGRESO + " INTEGER, " + CAMPO_AÑO_INGRESO + " INTEGER, " + CAMPO_CONCEPTO_INGRESO + " TEXT, " + CAMPO_PRECIO_INGRESO + " INTEGER, " + CAMPO_PERSONA_INGRESO + " INTEGER REFERENCES " + TABLA_PERSONA + "(" + CAMPO_ID_PERSONA + ") ON DELETE NO ACTION ON UPDATE CASCADE );"
    const val CREAR_TABLA_GASTOS =
        //"DROP TABLE" + TABLA_FINANZAS+";" +
        "CREATE TABLE " + TABLA_GASTO_PERSONAL + " (" + CAMPO_ID_GASTO + " INTEGER PRIMARY KEY, " + CAMPO_DIA_GASTO + " INTEGER, " + CAMPO_MES_GASTO + " INTEGER, " + CAMPO_AÑO_GASTO + " INTEGER, " + CAMPO_CONCEPTO_GASTO + " TEXT, " + CAMPO_PRECIO_GASTO + " INTEGER, " + CAMPO_PERSONA_GASTO + " INTEGER REFERENCES " + TABLA_PERSONA + "(" + CAMPO_ID_PERSONA + ") ON DELETE NO ACTION ON UPDATE CASCADE);"
    const val CREAR_TABLA_CULTIVO =
        //"DROP TABLE" + TABLA_CULTIVO+";" +
        "CREATE TABLE " + TABLA_CULTIVO + " (" + CAMPO_ID_CULTIVO + " INTEGER PRIMARY KEY, " + CAMPO_NOMBRE_CULTIVO + " TEXT, " + CAMPO_CANT_PLANTAS + " INTEGER, " + CAMPO_FOTO_CULTIVO + " BLOB);"
    const val CREAR_TABLA_JORNAL =
        //"DROP TABLE" + TABLA_JORNAL+";" +
        "CREATE TABLE " + TABLA_JORNAL + " (" + CAMPO_ID_JORNAL + " INTEGER PRIMARY KEY, " + CAMPO_DIA_JORNAL + " INTEGER, " + CAMPO_MES_JORNAL + " INTEGER, " + CAMPO_AÑO_JORNAL + " INTEGER, " + CAMPO_CANT_JORNAL + " INTEGER, " + CAMPO_ACTV_JORNAL + " TEXT, " + CAMPO_PRECIO_JORNAL + " INTEGER, " + CAMPO_CULTIVO_JORNAL + " INTEGER REFERENCES " + TABLA_CULTIVO + "(" + CAMPO_ID_CULTIVO + ") ON DELETE NO ACTION ON UPDATE CASCADE);"
    const val CREAR_TABLA_COSECHA =
        //"DROP TABLE" + TABLA_COSECHA+";" +
        "CREATE TABLE " + TABLA_COSECHA + " (" + CAMPO_ID_COSECHA + " INTEGER PRIMARY KEY, " + CAMPO_DIA_COSECHA + " INTEGER, " + CAMPO_MES_COSECHA + " INTEGER, " + CAMPO_AÑO_COSECHA + " INTEGER, " + CAMPO_LIBRAS_EXTRA + " INTEGER, " + CAMPO_PRECIO_EXTRA + " INTEGER, " + CAMPO_LIBRAS_PRIMERA + " INTEGER, " + CAMPO_PRECIO_PRIMERA + " INTEGER, " + CAMPO_LIBRAS_SEGUNDA + " INTEGER, " + CAMPO_PRECIO_SEGUNDA + " INTEGER, " +
                CAMPO_LIBRAS_TERCERA + " INTEGER, " + CAMPO_PRECIO_TERCERA + " INTEGER, " + CAMPO_LIBRAS_CUARTA + " INTEGER, " + CAMPO_PRECIO_CUARTA + " INTEGER, " + CAMPO_LIBRAS_QUINTA + " INTEGER, " + CAMPO_LIBRAS_MADURA + " INTEGER, " + CAMPO_PRECIO_MADURA + " INTEGER, " + CAMPO_PRECIO_QUINTA + " INTEGER, " + CAMPO_OBSERVACION_COSECHA + " TEXT, " + CAMPO_IMG_FACTURA + " BLOB, " + CAMPO_CULTIVO_COSECHA + " INTEGER REFERENCES " + TABLA_CULTIVO + "(" + CAMPO_ID_CULTIVO + ") ON DELETE NO ACTION ON UPDATE CASCADE);"
    const val CREAR_TABLA_INSUMOS =
        //"DROP TABLE" + TABLA_INSUMOS+";" +
        "CREATE TABLE " + TABLA_INSUMOS + " (" + CAMPO_ID_INSUMO + " INTEGER PRIMARY KEY, " + CAMPO_DIA_INSUMO + " INTEGER, " + CAMPO_MES_INSUMO + " INTEGER, " + CAMPO_AÑO_INSUMO + " INTEGER, " + CAMPO_NOMBRE_INSUMO + " TEXT, " + CAMPO_PRECIO_INSUMO + " INTEGER, " + CAMPO_CANT_INSUMO + " INTEGER, " + CAMPO_CANT_USADO_INSUMO + " INTEGER, " + CAMPO_UNIDAD_INSUMO + " TEXT, " + CAMPO_CULTIVO_INSUMO + " INTEGER REFERENCES " + TABLA_CULTIVO + "(" + CAMPO_ID_CULTIVO + ") ON DELETE NO ACTION ON UPDATE CASCADE);"


    fun consultarListaPersonas(actividad: Activity) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var persona: PersonaVo
        listaPersonas = ArrayList<PersonaVo>()

        //select * from usuarios
        val cursor = db.rawQuery("SELECT * FROM " + TABLA_PERSONA, null)
        while (cursor.moveToNext()) {
            persona = PersonaVo()
            persona.id = cursor.getInt(0)
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
            cultivo.id = cursor.getInt(0)
            cultivo.nombre = cursor.getString(1)
            cultivo.cantidad = cursor.getInt(2)
            cultivo.imgCultivo = cursor.getBlob(3)
            listaCultivos!!.add(cultivo)

        }
        db.close()
    }

    //llenar lista de Ingresos Personales
    fun consultarListaIngresoPersonal(actividad: Activity, mes: Int, año: Int, idPersona: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var ingreso: IngresoPersonalVo
        listaIngresoPersonal = ArrayList<IngresoPersonalVo>()

        //select * from usuarios
        val cursor = db.rawQuery(
            "select dia_ingreso, mes_ingreso, año_ingreso, concepto, precio " +
                    " from ingreso_personal " +
                    " WHERE año_ingreso = " + año + " and mes_ingreso = " + mes + " and id_persona = " + idPersona , null
        )
        while (cursor.moveToNext()) {
            ingreso = IngresoPersonalVo()
            //ingreso.id= cursor.getInt(0)
            ingreso.dia = cursor.getInt(0)
            ingreso.mes = cursor.getInt(1)
            ingreso.año = cursor.getInt(2)
            ingreso.concepto = cursor.getString(3)
            ingreso.precio = cursor.getInt(4)
            listaIngresoPersonal!!.add(ingreso)

        }
        db.close()
    }

    fun consultaIngresoPersonalDia(actividad: Activity,dia: Int, mes: Int, año: Int, idPersona: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var ingreso: IngresoPersonalVo
        listaIngresoPersonal = ArrayList<IngresoPersonalVo>()

        //select * from usuarios
        val cursor = db.rawQuery(
            "select dia_ingreso, mes_ingreso, año_ingreso, concepto, precio, id_ingreso " +
                    " from ingreso_personal " +
                    " WHERE dia_ingreso = " + dia + " and año_ingreso = " + año + " and mes_ingreso = " + mes + " and id_persona = " + idPersona , null
        )
        while (cursor.moveToNext()) {
            ingreso = IngresoPersonalVo()
            //ingreso.id= cursor.getInt(0)
            ingreso.dia = cursor.getInt(0)
            ingreso.mes = cursor.getInt(1)
            ingreso.año = cursor.getInt(2)
            ingreso.concepto = cursor.getString(3)
            ingreso.precio = cursor.getInt(4)
            ingreso.id = cursor.getInt(5)
            listaIngresoPersonal!!.add(ingreso)

        }
        db.close()
    }


    //llenar lista de Gastos Personales
    fun consultarListaGastoPersonal(actividad: Activity, mes: Int, año: Int, idPersona: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var gasto: GastoPersonalVo
        listaGastoPersonal = ArrayList<GastoPersonalVo>()

        //select * from usuarios
        val cursor = db.rawQuery(
            "select dia_gasto, mes_gasto, año_gasto, concepto, precio " +
                    " from gasto_personal " +
                    " WHERE año_gasto = " + año + " and mes_gasto = " + mes + " and id_persona = " + idPersona,
            null
        )
        while (cursor.moveToNext()) {
            gasto = GastoPersonalVo()

            gasto.dia = cursor.getInt(0)
            gasto.mes = cursor.getInt(1)
            gasto.año = cursor.getInt(2)
            gasto.concepto = cursor.getString(3)
            gasto.precio = cursor.getInt(4)
            listaGastoPersonal!!.add(gasto)

        }
        db.close()
    }

    fun consultarGastoPersonalDia(actividad: Activity, dia: Int, mes: Int, año: Int, idPersona: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var gasto: GastoPersonalVo
        listaGastoPersonal = ArrayList<GastoPersonalVo>()

        //select * from usuarios
        val cursor = db.rawQuery(
            "select dia_gasto, mes_gasto, año_gasto, concepto, precio, id_gasto " +
                    " from gasto_personal " +
                    " WHERE dia_gasto = " + dia + " and año_gasto = " + año + " and mes_gasto = " + mes + " and id_persona = " + idPersona,
            null
        )
        while (cursor.moveToNext()) {
            gasto = GastoPersonalVo()

            gasto.dia = cursor.getInt(0)
            gasto.mes = cursor.getInt(1)
            gasto.año = cursor.getInt(2)
            gasto.concepto = cursor.getString(3)
            gasto.precio = cursor.getInt(4)
            gasto.id = cursor.getInt(5)
            listaGastoPersonal!!.add(gasto)

        }
        db.close()
    }


    //Beneficio Personal por Mes
    fun calcularBeneficioPersonal(actividad: Activity, año: Int, idPersona: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var beneficioPersonal: BeneficioPersonalVo

        //Beneficio Personal
        listaBeneficioPersonal = ArrayList<BeneficioPersonalVo>()

        //Consulta para registros de ingresos y gastos de personas
        for (i in 1..12) {
            val cursor = db.rawQuery(
                "SELECT Ingreso_Total,Gasto_Total,ifnull((Ingreso_Total-Gasto_Total),0) as Beneficio\n" +
                        "FROM(\n" +
                        "(SELECT ifnull(sum(precio),0) as Ingreso_Total, concepto\n" +
                        "from ingreso_personal\n" +
                        "WHERE año_ingreso = " + año + " and mes_ingreso = " + i + " and id_persona = " + idPersona + ")\n" +
                        ",\n" +
                        "(SELECT ifnull(sum(precio),0) as Gasto_Total, concepto\n" +
                        "FROM gasto_personal\n" +
                        "WHERE año_gasto = " + año + " and mes_gasto = " + i + " and id_persona = " + idPersona + "))",
                null
            )
            while (cursor.moveToNext()) {

                beneficioPersonal = BeneficioPersonalVo()
                //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
                // y le asigno la respuesta cuando sea diferente de nulo
                beneficioPersonal.año = año
                beneficioPersonal.mes = i
                beneficioPersonal.ingresos = cursor.getInt(0)
                beneficioPersonal.gastos = cursor.getInt(1)
                beneficioPersonal.beneficio = cursor.getInt(2)
                listaBeneficioPersonal!!.add(beneficioPersonal)

            }
        }
        db.close()
    }

    fun calcularBeneficioCultivo(actividad: Activity, año: Int, idCultivo: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var beneficioCultivo: BeneficioCultivoVo

        //Beneficio de Cultivo

        listaBeneficioCultivo = ArrayList<BeneficioCultivoVo>()

        for (i in 1..12) {
            val cursor = db.rawQuery(
                "SELECT Gasto_jornal,Gasto_insumo,(Gasto_jornal+Gasto_insumo) Gasto,Ingreso,ifnull((ingreso-(gasto_jornal+gasto_insumo)),0) as Beneficio,Extra,Primera,Segunda,Tercera,Cuarta,Quinta,Madura\n" +
                        "FROM(\n" +
                        "(SELECT ifnull(sum(jornal.precio_jornal*jornal.cant_jornal),0) as Gasto_jornal\n" +
                        "from jornal\n" +
                        "WHERE año_jornal = " + año + " and mes_jornal = " + i + " and id_cultivo = " + idCultivo + ")\n" +
                        ",\n" +
                        "(SELECT ifnull(sum((insumo.precio_insumo/insumo.cant_insumo)*insumo.cant_usado),0) as Gasto_insumo\n" +
                        "FROM insumo\n" +
                        "WHERE año_insumo = " + año + " and mes_insumo = " + i + " and id_cultivo = " + idCultivo + ")\n" +
                        ",\n" +
                        "(select ifnull((sum(libras_extra*precio_extra)+sum(libras_primera*precio_primera) +\n" +
                        "        sum(libras_segunda*precio_segunda) +sum(libras_tercera*precio_tercera)+\n" +
                        "sum(libras_cuarta*precio_cuarta)+sum(libras_quinta*precio_quinta)+\n" +
                        "sum(libras_madura*precio_madura)),0) AS Ingreso, sum(libras_extra) Extra, sum(libras_primera) Primera,\n" +
                        " sum(libras_segunda) Segunda, sum(libras_tercera) Tercera, sum(libras_cuarta) Cuarta, sum(libras_quinta) Quinta, \n" +
                        " sum(libras_madura) Madura\n" +
                        "from cosecha\n" +
                        "WHERE año_cosecha = " + año + " and mes_cosecha = " + i + " and id_cultivo = " + idCultivo + " )\n" +
                        ")", null
            )
            while (cursor.moveToNext()) {

                beneficioCultivo = BeneficioCultivoVo()

                beneficioCultivo.gastoJornal = cursor.getInt(0)
                beneficioCultivo.gastoInsumo = cursor.getInt(1)
                beneficioCultivo.gastos = cursor.getInt(2)
                beneficioCultivo.ingresos = cursor.getInt(3)
                beneficioCultivo.beneficio = cursor.getInt(4)
                beneficioCultivo.extra = cursor.getInt(5)
                beneficioCultivo.primera = cursor.getInt(6)
                beneficioCultivo.segunda = cursor.getInt(7)
                beneficioCultivo.tercera = cursor.getInt(8)
                beneficioCultivo.cuarta = cursor.getInt(9)
                beneficioCultivo.quinta = cursor.getInt(10)
                beneficioCultivo.madura = cursor.getInt(11)
                beneficioCultivo.año = año
                beneficioCultivo.mes = i
                listaBeneficioCultivo!!.add(beneficioCultivo)

            }
        }
        db.close()
    }

    fun consultarJornalesMes(actividad: Activity, mes: Int, año: Int, idCultivo: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var jornalCultivo: JornalCultivoVo

        //Beneficio de Cultivo
        listaJornalCultivo = ArrayList<JornalCultivoVo>()

        val cursor = db.rawQuery(
            "select dia_jornal, mes_jornal, año_jornal,actv_jornal, cant_jornal, precio_jornal, (cant_jornal*precio_jornal)as Gasto_Total\n" +
                    "from jornal\n" +
                    "where mes_jornal = " + mes + " and año_jornal= " + año + " and id_cultivo = " + idCultivo,
            null
        )
        while (cursor.moveToNext()) {
            jornalCultivo = JornalCultivoVo()

            jornalCultivo.dia = cursor.getInt(0)
            jornalCultivo.mes = cursor.getInt(1)
            jornalCultivo.año = cursor.getInt(2)
            jornalCultivo.actividad = cursor.getString(3)
            jornalCultivo.cantidadJornal = cursor.getInt(4)
            jornalCultivo.precioJornal = cursor.getInt(5)
            jornalCultivo.gastoTotalJornal = cursor.getInt(6)

            listaJornalCultivo!!.add(jornalCultivo)
        }
        db.close()
    }

    fun consultarInsumosMes(actividad: Activity, mes: Int, año: Int, idCultivo: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var insumoCultivo: InsumoCultivoVo

        //Beneficio de Cultivo
        listaInsumoCultivo = ArrayList<InsumoCultivoVo>()

        val cursor = db.rawQuery(
            "select dia_insumo, mes_insumo, año_insumo,nombre_insumo, precio_insumo, cant_usado, unidad_insumo, ((insumo.precio_insumo/insumo.cant_insumo)*insumo.cant_usado)as Gasto_Total\n" +
                    "from insumo\n" +
                    "where mes_insumo = " + mes + " and año_insumo= " + año + " and id_cultivo = " + idCultivo,
            null
        )
        while (cursor.moveToNext()) {
            insumoCultivo = InsumoCultivoVo()

            insumoCultivo.dia = cursor.getInt(0)
            insumoCultivo.mes = cursor.getInt(1)
            insumoCultivo.año = cursor.getInt(2)
            insumoCultivo.nombreInsumo = cursor.getString(3)
            insumoCultivo.precioInsumo = cursor.getInt(4)
            insumoCultivo.cantidadInsumo = cursor.getInt(5)
            insumoCultivo.unidadInsumo = cursor.getString(6)
            insumoCultivo.gastoTotalInsumo = cursor.getInt(7)


            listaInsumoCultivo!!.add(insumoCultivo)
        }
        db.close()
    }

    fun consultarCosechaMes(actividad: Activity, mes: Int, año: Int, idCultivo: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var cosechaCultivo: CosechaCultivoVo

        //Beneficio de Cultivo
        listaCosechaCultivo = ArrayList<CosechaCultivoVo>()

        val cursor = db.rawQuery(
            "select  dia_cosecha, mes_cosecha, año_cosecha,libras_extra Extra, libras_primera Primera,libras_segunda Segunda, libras_tercera Tercera, libras_cuarta Cuarta, libras_quinta Quinta,libras_madura Madura,precio_extra, precio_primera,precio_segunda, precio_tercera, precio_cuarta, precio_quinta, \n" +
                    " precio_madura, ((libras_extra*precio_extra)+(libras_primera*precio_primera) +(libras_segunda*precio_segunda) +(libras_tercera*precio_tercera)+(libras_cuarta*precio_cuarta)+(libras_quinta*precio_quinta)+(libras_madura*precio_madura)) AS TotalCosecha , img_factura \n" +
                    "from cosecha\n" +
                    "where mes_cosecha = " + mes + " and año_cosecha = " + año + " and id_cultivo = " + idCultivo,
            null
        )
        while (cursor.moveToNext()) {
            cosechaCultivo = CosechaCultivoVo()

            cosechaCultivo.dia = cursor.getInt(0)
            cosechaCultivo.mes = cursor.getInt(1)
            cosechaCultivo.año = cursor.getInt(2)
            cosechaCultivo.extra = cursor.getInt(3)
            cosechaCultivo.primera = cursor.getInt(4)
            cosechaCultivo.segunda = cursor.getInt(5)
            cosechaCultivo.tercera = cursor.getInt(6)
            cosechaCultivo.cuarta = cursor.getInt(7)
            cosechaCultivo.quinta = cursor.getInt(8)
            cosechaCultivo.madura = cursor.getInt(9)
            cosechaCultivo.precioExtra = cursor.getInt(10)
            cosechaCultivo.precioPrimera = cursor.getInt(11)
            cosechaCultivo.precioSegunda = cursor.getInt(12)
            cosechaCultivo.precioTercera = cursor.getInt(13)
            cosechaCultivo.precioCuarta = cursor.getInt(14)
            cosechaCultivo.precioQuinta = cursor.getInt(15)
            cosechaCultivo.precioMadura = cursor.getInt(16)
            cosechaCultivo.dineroTotal = cursor.getInt(17)
            cosechaCultivo.imgFactura = cursor.getBlob(18)

            listaCosechaCultivo!!.add(cosechaCultivo)
        }
        db.close()
    }

    fun consultarCosechaDia(actividad: Activity, mes: Int, año: Int, dia: Int, idCultivo: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var cosechaCultivo: CosechaCultivoVo

        //Beneficio de Cultivo
        listaCosechaCultivo = ArrayList<CosechaCultivoVo>()

        val cursor = db.rawQuery(
            "select dia_cosecha, mes_cosecha, año_cosecha,libras_extra Extra, libras_primera Primera,libras_segunda Segunda, libras_tercera Tercera, libras_cuarta Cuarta, libras_quinta Quinta,libras_madura Madura,precio_extra, precio_primera,precio_segunda, precio_tercera, precio_cuarta, precio_quinta,\n" +
                    "precio_madura, ((libras_extra*precio_extra)+(libras_primera*precio_primera) +(libras_segunda*precio_segunda) +(libras_tercera*precio_tercera)+(libras_cuarta*precio_cuarta)+(libras_quinta*precio_quinta)+(libras_madura*precio_madura)) AS TotalCosecha, img_factura, id_cosecha \n" +
                    "from cosecha \n" +
                    "WHERE año_cosecha = " + año + " and mes_cosecha = " + mes + " and dia_cosecha = " + dia + " and id_cultivo = " + idCultivo,
            null
        )
        while (cursor.moveToNext()) {
            cosechaCultivo = CosechaCultivoVo()

            cosechaCultivo.dia = cursor.getInt(0)
            cosechaCultivo.mes = cursor.getInt(1)
            cosechaCultivo.año = cursor.getInt(2)
            cosechaCultivo.extra = cursor.getInt(3)
            cosechaCultivo.primera = cursor.getInt(4)
            cosechaCultivo.segunda = cursor.getInt(5)
            cosechaCultivo.tercera = cursor.getInt(6)
            cosechaCultivo.cuarta = cursor.getInt(7)
            cosechaCultivo.quinta = cursor.getInt(8)
            cosechaCultivo.madura = cursor.getInt(9)
            cosechaCultivo.precioExtra = cursor.getInt(10)
            cosechaCultivo.precioPrimera = cursor.getInt(11)
            cosechaCultivo.precioPrimera = cursor.getInt(12)
            cosechaCultivo.precioTercera = cursor.getInt(13)
            cosechaCultivo.precioCuarta = cursor.getInt(14)
            cosechaCultivo.precioQuinta = cursor.getInt(15)
            cosechaCultivo.precioMadura = cursor.getInt(16)
            cosechaCultivo.dineroTotal = cursor.getInt(17)
            cosechaCultivo.imgFactura = cursor.getBlob(18)
            cosechaCultivo.id = cursor.getInt(19)

            listaCosechaCultivo!!.add(cosechaCultivo)
        }
        db.close()
    }

    fun consultarJornalDia(actividad: Activity, mes: Int, año: Int, dia: Int, idCultivo: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var jornalCultivo: JornalCultivoVo

        //Beneficio de Cultivo
        listaJornalCultivo = ArrayList<JornalCultivoVo>()

        val cursor = db.rawQuery(
            "select dia_jornal, mes_jornal, año_jornal,actv_jornal, cant_jornal, precio_jornal, (cant_jornal*precio_jornal)as Gasto_Total, id_jornal\n" +
                    "from jornal\n" +
                    "where mes_jornal = " + mes + " and año_jornal= " + año + " and dia_jornal = " + dia + " and id_cultivo = " + idCultivo,
            null
        )
        while (cursor.moveToNext()) {
            jornalCultivo = JornalCultivoVo()

            jornalCultivo.dia = cursor.getInt(0)
            jornalCultivo.mes = cursor.getInt(1)
            jornalCultivo.año = cursor.getInt(2)
            jornalCultivo.actividad = cursor.getString(3)
            jornalCultivo.cantidadJornal = cursor.getInt(4)
            jornalCultivo.precioJornal = cursor.getInt(5)
            jornalCultivo.gastoTotalJornal = cursor.getInt(6)
            jornalCultivo.id = cursor.getInt(7)

            listaJornalCultivo!!.add(jornalCultivo)
        }
        db.close()
    }

    fun consultarInsumosDia(actividad: Activity, mes: Int, año: Int, dia: Int, idCultivo: Int) {
        val conn = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
        var insumoCultivo: InsumoCultivoVo

        //Beneficio de Cultivo
        listaInsumoCultivo = ArrayList<InsumoCultivoVo>()

        val cursor = db.rawQuery(
            "select dia_insumo, mes_insumo, año_insumo,nombre_insumo, precio_insumo, cant_insumo, cant_usado, unidad_insumo, ((insumo.precio_insumo/insumo.cant_insumo)*insumo.cant_usado)as Gasto_Total, id_insumo\n" +
                    "from insumo\n" +
                    "where mes_insumo = " + mes + " and año_insumo= " + año + " and dia_insumo = " + dia + " and id_cultivo = " + idCultivo,
            null
        )
        while (cursor.moveToNext()) {
            insumoCultivo = InsumoCultivoVo()

            //Comparo si la respuesta de la consulta, el mes_ingreso es nulo, o el mes_gasto es nulo
            // y le asigno la respuesta cuando sea diferente de nulo

            insumoCultivo.dia = cursor.getInt(0)
            insumoCultivo.mes = cursor.getInt(1)
            insumoCultivo.año = cursor.getInt(2)
            insumoCultivo.nombreInsumo = cursor.getString(3)
            insumoCultivo.precioInsumo = cursor.getInt(4)
            insumoCultivo.cantidadInsumo = cursor.getInt(5)
            insumoCultivo.cantidadUsado = cursor.getInt(6)
            insumoCultivo.unidadInsumo = cursor.getString(7)
            insumoCultivo.gastoTotalInsumo = cursor.getInt(8)
            insumoCultivo.id = cursor.getInt(9)

            listaInsumoCultivo!!.add(insumoCultivo)
        }
        db.close()
    }

}