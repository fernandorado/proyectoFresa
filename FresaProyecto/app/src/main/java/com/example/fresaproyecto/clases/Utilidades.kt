package com.example.fresaproyecto.clases

import android.app.Activity
import android.database.sqlite.SQLiteDatabase
import com.example.fresaproyecto.clases.vo.CultivoVo
import com.example.fresaproyecto.clases.vo.PersonaVo


object Utilidades {
    //permite tener la referencia del avatar seleccionado para el momento del registro en la BD
    var avatarIdSeleccion = 0
    var correctas = 0
    var incorrectas = 0
    var puntaje = 0
    var nivelJuego = 0
    var listaPersonas: ArrayList<PersonaVo>? = null
    var listaCultivos: ArrayList<CultivoVo>? = null

    const val NOMBRE_BD = "fresa_bd"

    //Constantes campos tabla persona
    const val TABLA_PERSONA = "persona"
    const val CAMPO_ID_PERSONA = "id"
    const val CAMPO_NOMBRE_PERSONA = "nombre"

    //Constantes campos tabla registro de finanzas
    const val TABLA_FINANZAS = "finanzas"
    const val CAMPO_ID_FINANZAS = "id"
    const val CAMPO_INGRESO_GASTO = "ingreso_gasto"
    const val CAMPO_CONCEPTO = "concepto"
    const val CAMPO_PRECIO = "precio_finanzas"
    const val CAMPO_FECHA = "fecha"

    //Constantes campos tabla cultivo
    const val TABLA_CULTIVO = "cultivo"
    const val CAMPO_ID_CULTIVO = "id_cultivo"
    const val CAMPO_NOMBRE_CULTIVO = "nombre"
    const val CAMPO_CANT_PLANTAS = "cant_plantas"

    //Constantes campos tabla jornal
    const val TABLA_JORNAL = "jornal"
    const val CAMPO_ID_JORNAL = "id_cultivo"
    const val CAMPO_CANT_JORNAL = "cant_jornal"
    const val CAMPO_PRECIO_JORNAL = "precio_jornal"

    //Constantes campos tabla insumos
    const val TABLA_INSUMOS = "insumo"
    const val CAMPO_ID_INSUMO = "id_cultivo"
    const val CAMPO_NOMBRE_INSUMO = "nombre_insumo"
    const val CAMPO_CANT_INSUMO = "cant_insumo"
    const val CAMPO_PRECIO_INSUMO = "precio_inusmo"


    //Constantes campos tabla cosecha
    const val TABLA_COSECHA = "cosecha"
    const val CAMPO_ID_COSECHA = "id_cultivo"
    const val CAMPO_CANT_COSECHA = "cant_cosecha"
    const val CAMPO_PRECIO_COSECHA = "precio_cosecha"

    const val CREAR_TABLA_PERSONA =
        //"DROP TABLE" + TABLA_PERSONA+";" +
        "CREATE TABLE " + TABLA_PERSONA + " (" + CAMPO_ID_PERSONA + " INTEGER PRIMARY KEY, " + CAMPO_NOMBRE_PERSONA + " TEXT);"
    const val CREAR_TABLA_FINANZAS =
        //"DROP TABLE" + TABLA_FINANZAS+";" +
        "CREATE TABLE " + TABLA_FINANZAS + " (" + CAMPO_ID_FINANZAS + " INTEGER, " + CAMPO_INGRESO_GASTO + " TEXT," + CAMPO_CONCEPTO +" TEXT," + CAMPO_PRECIO + " INTEGER," + CAMPO_FECHA + " TEXT)"
    const val CREAR_TABLA_CULTIVO =
        //"DROP TABLE" + TABLA_CULTIVO+";" +
        "CREATE TABLE " + TABLA_CULTIVO + " (" + CAMPO_ID_CULTIVO + " INTEGER PRIMARY KEY, " + CAMPO_NOMBRE_CULTIVO + " TEXT," + CAMPO_CANT_PLANTAS + " INTEGER)"
    const val CREAR_TABLA_JORNAL =
        //"DROP TABLE" + TABLA_JORNAL+";" +
        "CREATE TABLE " + TABLA_JORNAL + " (" + CAMPO_ID_JORNAL + " INTEGER PRIMARY KEY, " + CAMPO_CANT_JORNAL + " INTEGER," + CAMPO_PRECIO_JORNAL + " INTEGER)"
    const val CREAR_TABLA_COSECHA =
        //"DROP TABLE" + TABLA_COSECHA+";" +
        "CREATE TABLE " + TABLA_COSECHA + " (" + CAMPO_ID_COSECHA + " INTEGER PRIMARY KEY, " + CAMPO_CANT_COSECHA + " INTEGER," + CAMPO_PRECIO_COSECHA + " INTEGER)"
    const val CREAR_TABLA_INSUMOS =
        //"DROP TABLE" + TABLA_INSUMOS+";" +
        "CREATE TABLE " + TABLA_INSUMOS + " (" + CAMPO_ID_INSUMO + " INTEGER PRIMARY KEY, "+ CAMPO_NOMBRE_INSUMO + " TEXT," + CAMPO_CANT_INSUMO + " INTEGER," + CAMPO_PRECIO_INSUMO + " INTEGER)"



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
            listaCultivos!!.add(cultivo)

        }/*
        for (item in listaCultivos!!) {
            println("ID: "+item.id + "Nombre: " + item.nombre)
        }*/
        db.close()
    }

/*
    fun calcularGanancias(actividad:Activity?){
        val conn: ConexionSQLiteHelper = ConexionSQLiteHelper(actividad, NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conn.getReadableDatabase()
    }*/

}