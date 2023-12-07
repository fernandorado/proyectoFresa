package com.misRegistros.controllers

import android.content.Context
import com.misRegistros.clases.vo.PersonaVo
import com.misRegistros.services.PersonaServiceImpl

class PersonaRestController {

    var personaService: PersonaServiceImpl = PersonaServiceImpl()

    fun create(context: Context, persona: PersonaVo): PersonaVo? {
        var objPersona: PersonaVo? = null
        var personaActual: PersonaVo? = personaService.findById(context, persona.id)
        if (personaActual == null) {
            objPersona = personaService.save(context, persona)
        }
        return objPersona
    }

    fun update(context: Context, persona: PersonaVo, id: Int): PersonaVo? {
        var objPersona: PersonaVo? = null
        var personaActual: PersonaVo? = personaService.findById(context, persona.id)

        if (personaActual == null) {
            objPersona = personaService.update(context, id, persona)
        }else{
            if (persona.id == id) {
                objPersona = personaService.update(context, id, persona)
            }
        }
        return objPersona
    }

    fun delete(context: Context, id: Int): Boolean {
        var bandera: Boolean = false
        var personaActual: PersonaVo? = personaService.findById(context, id)
        if (personaActual != null) {
            bandera = personaService.delete(context, id)
        }
        return bandera
    }
}