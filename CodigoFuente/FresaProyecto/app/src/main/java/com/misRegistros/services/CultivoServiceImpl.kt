package com.misRegistros.services

import android.content.Context
import com.misRegistros.clases.vo.CultivoVo
import com.misRegistros.repositories.CultivoRepository

class CultivoServiceImpl : ICultivoService {

    var cultivoRepository : CultivoRepository = CultivoRepository()
    override fun findAll(context: Context): ArrayList<CultivoVo> {
        return cultivoRepository.findAll(context)
    }

    override fun findById(context: Context,id: Int): CultivoVo? {
        return cultivoRepository.findById(context,id)
    }

    override fun save(context: Context, cultivo: CultivoVo): CultivoVo {
        return cultivoRepository.save(context,cultivo)!!
    }

    override fun update(context: Context,id: Int, cultivo: CultivoVo): CultivoVo {
        return cultivoRepository.update(context,id,cultivo)!!
    }

    override fun delete(context: Context,id: Int): Boolean {
        return cultivoRepository.delete(context,id)
    }

}