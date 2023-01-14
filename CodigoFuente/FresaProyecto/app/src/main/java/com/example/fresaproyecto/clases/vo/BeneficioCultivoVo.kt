package com.example.fresaproyecto.clases.vo

class BeneficioCultivoVo {
    var ingresos = 0
    var gastos = 0
    var beneficio = 0

    constructor(ingresos: Int, gastos:Int, beneficio:Int) {
        this.ingresos = ingresos
        this.gastos = gastos
        this.beneficio = beneficio
    }

    constructor() {}
}