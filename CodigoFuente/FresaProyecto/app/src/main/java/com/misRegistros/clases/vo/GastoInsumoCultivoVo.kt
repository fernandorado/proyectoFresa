package com.misRegistros.clases.vo

class GastoInsumoCultivoVo {
    var id = 0
    var fecha :String? = null
    var nombre :String? = null
    var precio = 0
    var cantidad = 0
    var cantidadUsado = 0

    constructor(id: Int, fecha: String, nombre: String, precio:Int,cantidad:Int,cantidadUsado:Int) {
        this.id = id
        this.fecha = fecha
        this.nombre = nombre
        this.precio = precio
        this.cantidad = cantidad
        this.cantidadUsado = cantidadUsado
    }

    constructor() {}
}