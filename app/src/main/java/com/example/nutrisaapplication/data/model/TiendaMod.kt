package com.example.nutrisaapplication.data.model


import com.google.gson.annotations.SerializedName

data class TiendaMod(
    @SerializedName("datos_distrito")
    val datosDistrito: DatosDistrito,
    @SerializedName("datos_region")
    val datosRegion: DatosRegion,
    @SerializedName("domicilio")
    val domicilio: String, // Av. Vallarta # 3959 Col. Don Bosco CP. 44540 Del. o Mpio. Zapopan
    @SerializedName("email_tienda")
    val emailTienda: String, // suc325@nutrisa.com
    @SerializedName("inversion")
    val inversion: String, // Propia
    @SerializedName("nombre_tienda")
    val nombreTienda: String, // Gran Plaza Guadalajara
    @SerializedName("numero_herdez")
    val numeroHerdez: Int, // 12325
    @SerializedName("numero_tienda")
    val numeroTienda: Int // 325
) {
    data class DatosDistrito(
        @SerializedName("distrital")
        val distrital: String, // Rafael Robles
        @SerializedName("distrito")
        val distrito: String, // GUADALAJARA 1
        @SerializedName("email_distrital")
        val emailDistrital: String, // rrobles@herdez.com
        @SerializedName("estado")
        val estado: String, // Jalisco
        @SerializedName("municipio")
        val municipio: String, // Zapopan
        @SerializedName("telefono_distrital")
        val telefonoDistrital: String, // 55 4258 9113
        @SerializedName("telefono_tienda")
        val telefonoTienda: Long // 7626272287
    )


}