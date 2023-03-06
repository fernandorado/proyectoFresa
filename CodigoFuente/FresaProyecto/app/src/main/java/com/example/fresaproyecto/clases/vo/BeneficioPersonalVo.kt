package com.example.fresaproyecto.clases.vo

class BeneficioPersonalVo {
    var mes: Int = 0
    var año: Int = 0
    var ingresos = 0
    var gastos = 0
    var beneficio = 0

    constructor( mes:Int, año:Int, ingresos: Int, gastos:Int, beneficio:Int) {
        this.mes = mes
        this.año = año
        this.ingresos = ingresos
        this.gastos = gastos
        this.beneficio = beneficio
    }

    constructor() {}
}