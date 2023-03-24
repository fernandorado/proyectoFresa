package com.misRegistros.clases.vo

class JornalCultivoVo {

    var actividad :String = ""
    var cantidadJornal = 0
    var precioJornal = 0
    var gastoTotalJornal = 0
    var dia = 0
    var id = 0
    var mes = 0
    var año = 0

    constructor(actividad: String,cantidadJornal:Int,precioJornal:Int,gastoTotalJornal:Int,
                mes:Int,dia:Int, año:Int, id:Int) {
        this.actividad = actividad
        this.cantidadJornal = cantidadJornal
        this.precioJornal = precioJornal
        this.gastoTotalJornal = gastoTotalJornal
        this.dia = dia
        this.id = id
        this.mes = mes
        this.año = año
    }



    constructor() {}

}