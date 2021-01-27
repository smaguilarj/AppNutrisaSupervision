package com.example.nutrisaapplication.data.model

import com.google.firebase.database.Exclude
import com.google.firebase.database.PropertyName
import org.jetbrains.annotations.PropertyKey

data class FirebaseData(
    @PropertyName("Consecutivo")
    val Consecutivo: String,
    @PropertyName("Numero")
    val numero:String?,
    @PropertyName("Numero_herdez")
    val Numero_herdez:String?,
    @PropertyName("Nombre_tienda")
    val Nombre_tienda:String?,
    @PropertyName("Estatus")
    val estatus:String?,
    @PropertyName("Inversion")
    val inversion:String?,
    @PropertyName("Formato")
    val formato:String?,
    @PropertyName("Email_tienda")
    val email_tienda:String?,
    @PropertyName("Region")
    val region:String?,
    @PropertyName("Regional")
    val regional:String?,
    @PropertyName("Telefono_region")
    val telefono_region:String?,
    @PropertyName("Email_region")
    val email_region:String?,
    @PropertyName("Distrito")
    val distrito:String?,
    @PropertyName("Distrital")
    val distrital:String?,
    @PropertyName("Telefono_distrital")
    val telefono_distrital:String?,
    @PropertyName("Email_distrital")
    val email_distrital:String?,
    @PropertyName("Telefono_tiendaRegional")
    val Telefono_tiendaRegional:String?,
    @PropertyName("Estado")
    val estado:String?,
    @PropertyName("Municipio")
    val municipio:String?,
    @PropertyName("Domicilio")
    val domicilio:String?)
{
    constructor() : this("", "", "", "", "", "","","","","","","","","","","","","","","")
}
