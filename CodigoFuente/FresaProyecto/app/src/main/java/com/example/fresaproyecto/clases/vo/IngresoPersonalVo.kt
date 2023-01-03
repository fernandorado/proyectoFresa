package com.example.fresaproyecto.clases.vo

class IngresoPersonalVo {
    //var id = 0
    //var idPersona = 0
    var mes: String? = null
    var año: String? = null
    var concepto: String = ""
    var precio = 0

    constructor(mes:String, año:String, concepto: String, precio:Int) {
        //this.id = id
        //this.idPersona = idPersona
        this.mes = mes
        this.año = año
        this.concepto = concepto
        this.precio = precio
    }

    constructor() {}
}