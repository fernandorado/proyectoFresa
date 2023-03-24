package com.misRegistros.clases.vo

class IngresoPersonalVo {
    //var id = 0
    //var idPersona = 0
    var id = 0
    var dia = 0
    var mes = 0
    var año = 0
    var concepto: String = ""
    var precio = 0

    constructor(dia:Int,mes:Int, año:Int, id:Int, concepto: String, precio:Int) {
        //this.id = id
        //this.idPersona = idPersona
        this.id = id
        this.dia = dia
        this.mes = mes
        this.año = año
        this.concepto = concepto
        this.precio = precio
    }

    constructor() {}
}