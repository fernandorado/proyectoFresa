package com.misRegistros.clases.vo

class IngresoCosechaCultivoVo {
    var id = 0
    var ingresos = 0
    var gastos = 0
    var beneficio = 0

    constructor(id: Int, ingresos: Int, gastos:Int, beneficio:Int) {
        this.id = id
        this.ingresos = ingresos
        this.gastos = gastos
        this.beneficio = beneficio
    }

    constructor() {}
}