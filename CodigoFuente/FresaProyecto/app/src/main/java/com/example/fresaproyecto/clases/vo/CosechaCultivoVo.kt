package com.example.fresaproyecto.clases.vo

import java.io.ByteArrayInputStream

class CosechaCultivoVo {

    var id = 0
    var extra = 0
    var precioExtra = 0
    var primera = 0
    var precioPrimera = 0
    var segunda = 0
    var precioSegunda = 0
    var tercera = 0
    var precioTercera = 0
    var cuarta = 0
    var precioCuarta = 0
    var quinta = 0
    var precioQuinta = 0
    var madura = 0
    var precioMadura = 0
    var dineroTotal = 0
    var dia = 0
    var mes = 0
    var año = 0
    lateinit var imgFactura : ByteArray

    constructor(extra:Int,precioExtra:Int,primera:Int,precioPrimera:Int, segunda:Int,precioSegunda:Int,
                tercera:Int,precioTercera:Int, cuarta:Int,precioCuarta:Int, quinta:Int,precioQuinta:Int,
                madura:Int,precioMadura:Int,dineroTotal:Int,dia:Int,mes:Int, año:Int, imgFactura : ByteArray, id:Int
    ) {
        this.id = id
        this.extra = extra
        this.precioExtra = precioExtra
        this.primera = primera
        this.precioPrimera = precioPrimera
        this.segunda = segunda
        this.precioSegunda = precioSegunda
        this.tercera = tercera
        this.precioTercera = precioTercera
        this.cuarta = cuarta
        this.precioCuarta = precioCuarta
        this.quinta = quinta
        this.precioQuinta = precioQuinta
        this.madura = madura
        this.precioMadura = precioMadura
        this.dineroTotal = dineroTotal
        this.dia = dia
        this.mes = mes
        this.año = año
        this.imgFactura = imgFactura
    }


    constructor() {}


}