package com.misRegistros.clases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper


class ConexionSQLiteHelper(context: Context, name: String?, factory: CursorFactory?, version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    override fun onCreate(db: SQLiteDatabase) {
        /*db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_PERSONA)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_GASTO_PERSONAL)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_INGRESO_PERSONAL)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_CULTIVO)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_JORNAL)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_COSECHA)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_INSUMOS)*/ //USAR CUANDO LE HAGA AJUSTES A LA BASE DE DATOS

        db.execSQL(Utilidades.CREAR_TABLA_PERSONA)
        db.execSQL(Utilidades.CREAR_TABLA_GASTOS)
        db.execSQL(Utilidades.CREAR_TABLA_INGRESOS)
        db.execSQL(Utilidades.CREAR_TABLA_CULTIVO)
        db.execSQL(Utilidades.CREAR_TABLA_JORNAL)
        db.execSQL(Utilidades.CREAR_TABLA_COSECHA)
        db.execSQL(Utilidades.CREAR_TABLA_INSUMOS)
    }

    override fun onUpgrade(db: SQLiteDatabase, versionAntigua: Int, versionNueva: Int) {

        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_PERSONA)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_GASTO_PERSONAL)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_INGRESO_PERSONAL)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_CULTIVO)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_JORNAL)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_COSECHA)
        db.execSQL("DROP TABLE IF EXISTS " + Utilidades.TABLA_INSUMOS)
        onCreate(db)
    }
}