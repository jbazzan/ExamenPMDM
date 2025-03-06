package com.unirfp.examenpmdm.model

import com.google.gson.annotations.SerializedName

data class ProductoResponse(
    @SerializedName("page") val pagina: Int,
    @SerializedName("per_page") val porPagina: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("total_pages") val totalPaginas: Int,
    @SerializedName("results") val resultados: List<Producto>
)
