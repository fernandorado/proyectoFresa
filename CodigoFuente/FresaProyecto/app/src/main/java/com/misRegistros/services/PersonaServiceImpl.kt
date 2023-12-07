package com.misRegistros.services

import android.content.Context
import com.misRegistros.clases.vo.PersonaVo
import com.misRegistros.repositories.PersonaRepository

class PersonaServiceImpl : IPersonaService {

    var personaRepository : PersonaRepository = PersonaRepository()
    override fun findAll(context: Context): ArrayList<PersonaVo> {
        return personaRepository.findAll(context)
    }

    override fun findById(context: Context,id: Int): PersonaVo? {
        return personaRepository.findById(context,id)
    }

    override fun save(context: Context, persona: PersonaVo): PersonaVo {
        return personaRepository.save(context,persona)!!
    }

    override fun update(context: Context,id: Int, persona: PersonaVo): PersonaVo {
        return personaRepository.update(context,id,persona)!!
    }

    override fun delete(context: Context,id: Int): Boolean {
        return personaRepository.delete(context,id)
    }

}