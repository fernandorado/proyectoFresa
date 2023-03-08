package com.example.fresaproyecto.clases.vo

class InsumoCultivoVo {
    var nombreInsumo:String = ""
    var gastoTotalInsumo = 0
    var cantidadInsumo:Int = 0
    var cantidadUsado:Int = 0
    var precioInsumo:Int = 0
    var unidadInsumo:String = ""
    var id = 0
    var dia = 0
    var mes = 0
    var año = 0

    constructor(nombreInsumo:String,gastoTotalInsumo:Int,cantidadInsumo:Int,cantidadUsado:Int, unidadInsumo:String, precioInsumo:Int,
                mes:Int,dia:Int, año:Int, id:Int) {
        this.nombreInsumo = nombreInsumo
        this.gastoTotalInsumo = gastoTotalInsumo
        this.cantidadInsumo = cantidadInsumo
        this.cantidadUsado = cantidadUsado
        this.precioInsumo = precioInsumo
        this.unidadInsumo = unidadInsumo
        this.id = id
        this.dia = dia
        this.mes = mes
        this.año = año
    }



    constructor() {}
}