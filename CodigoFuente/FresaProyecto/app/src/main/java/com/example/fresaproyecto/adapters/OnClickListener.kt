package com.example.fresaproyecto.adapters

import com.example.fresaproyecto.clases.vo.CultivoVo
import com.example.fresaproyecto.clases.vo.PersonaVo

interface OnClickListenerPersona {
    fun onClick(personaVo: PersonaVo)
}

interface OnClickListenerCultivo {
    fun onClick(cultivoVo: CultivoVo)
}