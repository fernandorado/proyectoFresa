package com.misRegistros.controllers

import android.content.Context
import com.misRegistros.clases.vo.CultivoVo
import com.misRegistros.services.CultivoServiceImpl

class CultivoRestController {

    var cultivoService: CultivoServiceImpl = CultivoServiceImpl()

    fun create(context: Context, cultivo: CultivoVo): CultivoVo? {
        var objCultivo: CultivoVo? = null
        var cultivoActual: CultivoVo? = cultivoService.findById(context, cultivo.id)
        if (cultivoActual == null) {
            objCultivo = cultivoService.save(context, cultivo)
        }
        return objCultivo
    }

    fun update(context: Context, cultivo: CultivoVo, id: Int): CultivoVo? {
        var objCultivo: CultivoVo? = null
        var cultivoActual: CultivoVo? = cultivoService.findById(context, cultivo.id)

        if (cultivoActual == null) {
            objCultivo = cultivoService.update(context, id, cultivo)
        }else{
            if (cultivo.id == id) {
                objCultivo = cultivoService.update(context, id, cultivo)
            }
        }
        return objCultivo
    }

    fun delete(context: Context, id: Int): Boolean {
        var bandera: Boolean = false
        var cultivoActual: CultivoVo? = cultivoService.findById(context, id)
        if (cultivoActual != null) {
            bandera = cultivoService.delete(context, id)
        }
        return bandera
    }
}