package com.misRegistros.repositories

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.misRegistros.adapters.AdaptadorPersona
import com.misRegistros.clases.ConexionSQLiteHelper
import com.misRegistros.clases.Utilidades
import com.misRegistros.clases.vo.PersonaVo


class PersonaRepository {

    var utilidades: Utilidades = Utilidades
    fun findAll(context: Context): ArrayList<PersonaVo> {
        return utilidades.consultarListaPersonas(context)
    }

    fun findById(context: Context, id: Int): PersonaVo? {
        utilidades.consultarListaPersonas(context)
        var objPersona: PersonaVo? = null
        for (i in utilidades.listaPersonas!!) {
            if (i.id == id) {
                objPersona = i
                break
            }
        }
        return objPersona
    }

    fun save(context: Context, persona: PersonaVo?): PersonaVo {
        var objPersona: PersonaVo = PersonaVo()
        val conexion =
            ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase
        val values = ContentValues()

        values.put(Utilidades.CAMPO_ID_PERSONA, persona!!.id) //SI quito esto, le asigna los ID en orden 1,2,3...
        values.put(Utilidades.CAMPO_NOMBRE_PERSONA, persona!!.nombre)
        val idResultante: Number =
            db.insert(Utilidades.TABLA_PERSONA, Utilidades.CAMPO_ID_PERSONA, values)

        if (idResultante != -1) {
            objPersona = persona
            db.close()
        }
        return objPersona
    }

    fun update(context: Context,id:Int,persona: PersonaVo): PersonaVo?{
        var objPersona: PersonaVo? = null
        val conexion =
            ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase
        val values = ContentValues()
        values.put(Utilidades.CAMPO_ID_PERSONA,persona!!.id) //SI quito esto, le asigna los ID en orden 1,2,3...
        values.put(Utilidades.CAMPO_NOMBRE_PERSONA, persona!!.nombre)

        val idResultante: Number = db.update(
            Utilidades.TABLA_PERSONA, values, Utilidades.CAMPO_ID_PERSONA+"="+ id, null
        )

        if (idResultante != -1) {
            objPersona = persona
            db.close()
        }
        return objPersona
    }

    fun delete(context: Context,id:Int): Boolean{
        var bandera:Boolean=false
        val conexion =
            ConexionSQLiteHelper(context, Utilidades.NOMBRE_BD, null, 1)
        val db: SQLiteDatabase = conexion.writableDatabase

        val idResultante: Number =
            db.delete(
                Utilidades.TABLA_PERSONA,
                Utilidades.CAMPO_ID_PERSONA + "=" + id,
                null
            )
        if (idResultante != -1) {
            bandera=true
        }
        db.close()
        return bandera
    }
}