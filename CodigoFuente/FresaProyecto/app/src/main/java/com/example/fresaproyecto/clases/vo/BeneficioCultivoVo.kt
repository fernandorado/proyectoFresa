package com.example.fresaproyecto.clases.vo

import java.io.ByteArrayInputStream

class BeneficioCultivoVo {

    var gastoJornal = 0
    var gastoInsumo = 0
    var ingresos = 0
    var gastos = 0
    var beneficio = 0
    var extra = 0
    var primera = 0
    var segunda = 0
    var tercera = 0
    var cuarta = 0
    var quinta = 0
    var madura = 0
    var mes = 0
    var año = 0


    constructor(ingresos: Int,gastoJornal:Int,gastoInsumo:Int,gastos:Int, beneficio:Int, extra:Int,
                primera:Int, segunda:Int, tercera:Int, cuarta:Int, quinta:Int, madura:Int,
                mes:Int, año:Int) {
        this.ingresos = ingresos
        this.gastos = gastos
        this.gastoJornal = gastoJornal
        this.gastoInsumo = gastoInsumo
        this.beneficio = beneficio
        this.extra = extra
        this.primera = primera
        this.segunda = segunda
        this.tercera = tercera
        this.cuarta = cuarta
        this.quinta = quinta
        this.madura = madura
        this.mes = mes
        this.año = año
    }

    constructor() {}
}