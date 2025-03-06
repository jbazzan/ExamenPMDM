package com.unirfp.examenpmdm.model

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val nombre: String,
    @SerializedName("description") val descripcion: String,
    @SerializedName("price") val precio: Double,
    @SerializedName("category") val categoria: String,
    @SerializedName("image") val imagen: String,
    @SerializedName("active") val activo: Boolean
)
