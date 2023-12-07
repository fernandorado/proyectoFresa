package com.misRegistros.repositories

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.misRegistros.adapters.AdaptadorCultivo
import com.misRegistros.clases.ConexionSQLiteHelper
import com.misRegistros.clases.Utilidades
import com.misRegistros.clases.vo.CultivoVo


class CultivoRepository {

    var utilidades: Utilidades = Utilidades
    fun findAll(context: Context): ArrayList<CultivoVo> {
        return utilidades.consultarListaCultivos(context)
    }

    fun findById(context: Context, id: Int): CultivoVo? {
        utilidades.consultarListaCultivos(context)
        var objCultivo: CultivoVo? = null
        for (i in utilidades.listaCultivos!!) {
            if (i.id == id) {
                objCultivo = i
                break
            }
        }
        return objCultivo
    }

    fun save(context: Context, cultivo: CultivoVo?): CultivoVo {
        var objCultivo: CultivoVo = CultivoVo()
        val conexion =
            ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase
        val values = ContentValues()

        values.put(Utilidades.CAMPO_ID_CULTIVO, cultivo!!.id) //SI quito esto, le asigna los ID en orden 1,2,3...
        values.put(Utilidades.CAMPO_NOMBRE_CULTIVO, cultivo!!.nombre)
        values.put(Utilidades.CAMPO_FOTO_CULTIVO, cultivo!!.imgCultivo)
        val idResultante: Number =
            db.insert(Utilidades.TABLA_CULTIVO, Utilidades.CAMPO_ID_CULTIVO, values)

        if (idResultante != -1) {
            objCultivo = cultivo
            db.close()
        }
        return objCultivo
    }

    fun update(context: Context,id:Int,cultivo: CultivoVo): CultivoVo?{
        var objCultivo: CultivoVo? = null
        val conexion =
            ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase
        val values = ContentValues()
        values.put(Utilidades.CAMPO_ID_CULTIVO,cultivo!!.id) //SI quito esto, le asigna los ID en orden 1,2,3...
        values.put(Utilidades.CAMPO_NOMBRE_CULTIVO, cultivo!!.nombre)

        val idResultante: Number = db.update(
            Utilidades.TABLA_CULTIVO, values, Utilidades.CAMPO_ID_CULTIVO+"="+ id, null
        )

        if (idResultante != -1) {
            objCultivo = cultivo
            db.close()
        }
        return objCultivo
    }

    fun delete(context: Context,id:Int): Boolean{
        var adaptadorCultivo:AdaptadorCultivo= AdaptadorCultivo()
        var bandera:Boolean=false
        val conexion =
            ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_CULTIVO,
                Utilidades.CAMPO_ID_CULTIVO + "=" + id,
                null
            )
        if (idResultante != -1) {
            bandera=true
        }
        db.close()
        return bandera
    }
}