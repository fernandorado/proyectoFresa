package com.example.fresaproyecto.clases.vo

class CultivoVo {
    var id = 0
    var cantidad = 0
    var nombre: String? = null
    lateinit var imgCultivo : ByteArray

    constructor(id: Int, cantidad: Int, nombre: String?, imgCultivo : ByteArray) {
        this.id = id
        this.nombre = nombre
        this.cantidad = cantidad
        this.imgCultivo = imgCultivo
    }

    constructor() {}
}