package com.example.nutrisaapplication.data.model

import com.google.gson.annotations.SerializedName

/**
DELL
05/10/2020
NutrisaApplication
 */
data class DatosRegion(
    @SerializedName("email_regional")
    val emailRegional: String, // ldcervanteso@herdez.com
    @SerializedName("region")
    val region: String, // PACIFICO
    @SerializedName("regional")
    val regional: String, // Daniel Cervantes
    @SerializedName("telefono_regional")
    val telefonoRegional: Long // 3318935165
)