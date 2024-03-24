package com.example.foodie.data.entity

import com.google.gson.annotations.SerializedName

data class AllProductsResponse(
    @SerializedName("yemekler") var products: List<Product>,
    var success: Int
)

