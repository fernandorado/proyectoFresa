package com.example.fresaproyecto.clases.vo

class CultivoVo {
    var id = 0
    var cantidad = 0
    var nombre: String? = null

    constructor(id: Int, nombre: String?) {
        this.id = id
        this.nombre = nombre
    }

    constructor() {}
}