package com.example.fresaproyecto.clases.vo

class GastoPersonalVo {
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

        this.dia = dia
        this.mes = mes
        this.año = año
        this.id = id
        this.concepto = concepto
        this.precio = precio
    }

    constructor() {}
}