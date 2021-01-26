package com.example.nutrisaapplication.data.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.PropertyName
import org.jetbrains.annotations.PropertyKey

data class FirebaseData(
    @PropertyName("Consecutivo")
    val Consecutivo: Int,
    @PropertyName("Numero")
    val numero:Int?,
    @PropertyName("Numero_herdez")
    val Numero_herdez:Int?,
    @PropertyName("Nombre de tienda")
    val nombreTienda:String,
    @PropertyName("Estatus")
    val estatus:String?,
    @PropertyName("Inversion")
    val inversion:String?,
    @PropertyName("Formato")
    val formato:String?,
    @PropertyName("Fecha Apertura")
    val fechaApertura:Int?,
    @PropertyName("Email_tienda")
    val email_tienda:String?,
    @PropertyName("Region")
    val region:String?,
    @PropertyName("Regional")
    val regional:String?,
    @PropertyName("Telefono_region")
    val telefono_region:Any?,
    @PropertyName("Email_region")
    val email_region:String?,
    @PropertyName("Distrito")
    val distrito:String?,
    @PropertyName("Distrital")
    val distrital:String?,
    @PropertyName("Telefono_distrital")
    val telefono_distrital:Any?,
    @PropertyName("Email_distrital")
    val email_distrital:String?,
    @PropertyName("Telefono de tienda")
    val telefonoOficina:Any?,
    @PropertyName("Estado")
    val estado:String?,
    @PropertyName("Municipio")
    val municipio:String?,
    @PropertyName("Domicilio")
    val domicilio:String?)
{
    constructor() : this(0,0, 0, "", "", "", "",null,"","","",null,"","","","","",0,"","","")
}
