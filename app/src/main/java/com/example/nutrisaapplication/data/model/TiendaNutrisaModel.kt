package com.example.nutrisaapplication.data.model


import com.google.gson.annotations.SerializedName

data class TiendaNutrisaModel(
    @SerializedName("Tienda Nutrisa")
    val tiendaNutrisa: List<TiendaMod>
)