package com.example.fresaproyecto.clases.vo

class BeneficioCultivoVo {
    var mes: String? = null
    var año: String? = null
    var ingresos = 0
    var gastoJornal = 0
    var gastoInsumo = 0
    var beneficio = 0

    constructor( mes:String, año:String, ingresos: Int, gastoJornal:Int,gastoInsumo:Int, beneficio:Int) {
        this.mes = mes
        this.año = año
        this.ingresos = ingresos
        this.gastoJornal = gastoJornal
        this.gastoInsumo = gastoInsumo
        this.beneficio = beneficio
    }

    constructor() {}
}