package com.example.foodie.retrofit

class ApiUtils {
    companion object {
        const val BASE_URL = "http://kasimadalan.pe.hu/"
        const val YEMEK_IMG_ENDPOINT = "yemekler/resimler/"
        const val USERNAME = "ebruaeo"

        fun getProductApi(): ProductApi {
            return RetrofitClient.getClient(BASE_URL).create(ProductApi::class.java)
        }

        fun constructImgUrl(imgName: String) = BASE_URL + YEMEK_IMG_ENDPOINT + imgName
    }
}