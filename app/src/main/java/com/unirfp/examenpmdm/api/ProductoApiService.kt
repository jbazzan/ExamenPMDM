package com.unirfp.examenpmdm.api

import com.unirfp.examenpmdm.model.ProductoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ProductoApiService {
    @GET
    suspend fun getAllProducts(@Url url : String): Response<ProductoResponse>
}