package com.example.foodie.retrofit

class ApiUtils {
    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getProductApi(): ProductApi {
            return RetrofitClient.getClient(BASE_URL).create(ProductApi::class.java)
        }
    }
}