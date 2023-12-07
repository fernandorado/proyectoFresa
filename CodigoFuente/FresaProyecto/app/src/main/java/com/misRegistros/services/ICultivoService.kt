package com.misRegistros.services

import android.content.Context
import com.misRegistros.clases.vo.CultivoVo

interface ICultivoService {
    fun findAll(context: Context):ArrayList<CultivoVo>
    fun findById(context: Context,id:Int):CultivoVo?
    fun save(context: Context,persona:CultivoVo):CultivoVo
    fun update(context: Context,id:Int, persona: CultivoVo):CultivoVo
    fun delete(context: Context,id:Int):Boolean
}