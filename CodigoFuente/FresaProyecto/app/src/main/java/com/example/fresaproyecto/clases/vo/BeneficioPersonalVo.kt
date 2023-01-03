package com.example.fresaproyecto.clases.vo

class BeneficioPersonalVo {
    var mes: String? = null
    var año: String? = null
    var ingresos = 0
    var gastos = 0
    var beneficio = 0

    constructor( mes:String, año:String, ingresos: Int, gastos:Int, beneficio:Int) {
        this.mes = mes
        this.año = año
        this.ingresos = ingresos
        this.gastos = gastos
        this.beneficio = beneficio
    }

    constructor() {}
}