package com.misRegistros.clases.vo

class PersonaVo {
    var id = 0
    var nombre: String? = null

    constructor(id: Int, nombre: String?) {
        this.id = id
        this.nombre = nombre
    }

    constructor() {}
}