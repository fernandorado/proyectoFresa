package com.misRegistros.adapters

import com.misRegistros.clases.vo.CultivoVo
import com.misRegistros.clases.vo.PersonaVo

interface OnClickListenerPersona {
    fun onClick(personaVo: PersonaVo)
}

interface OnClickListenerCultivo {
    fun onClick(cultivoVo: CultivoVo)
}