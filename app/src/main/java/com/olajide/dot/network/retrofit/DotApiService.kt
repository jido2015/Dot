package com.olajide.dot.network.retrofit

import com.olajide.dot.list.data.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface DotApiService {

    @GET("api/v1/products.json")
    suspend fun getProducts(): Response<Product>

}