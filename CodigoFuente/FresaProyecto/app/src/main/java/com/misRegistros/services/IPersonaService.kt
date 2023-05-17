package com.misRegistros.services

import android.content.Context
import com.misRegistros.clases.vo.PersonaVo

interface IPersonaService {
    fun findAll(context: Context):ArrayList<PersonaVo>
    fun findById(context: Context,id:Int):PersonaVo?
    fun save(context: Context,persona:PersonaVo):PersonaVo
    fun update(context: Context,id:Int, persona: PersonaVo):PersonaVo
    fun delete(context: Context,id:Int):Boolean
}