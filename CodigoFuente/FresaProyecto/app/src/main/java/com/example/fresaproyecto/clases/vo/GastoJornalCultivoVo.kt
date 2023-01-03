package com.example.fresaproyecto.clases.vo

class GastoJornalCultivoVo {
    var id = 0
    var fecha :String? = null
    var cantidad = 0
    var actividad :String? = null
    var precio = 0

    constructor(id: Int, fecha: String, cantidad:Int, actividad:String, precio:Int) {
        this.id = id
        this.fecha = fecha
        this.cantidad = cantidad
        this.actividad = actividad
        this.precio = precio
    }

    constructor() {}
}