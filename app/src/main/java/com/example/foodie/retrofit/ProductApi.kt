package com.example.foodie.retrofit

import com.example.foodie.data.entity.AllProductsResponse
import retrofit2.http.GET

interface ProductApi {

    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllProducts(): AllProductsResponse
}