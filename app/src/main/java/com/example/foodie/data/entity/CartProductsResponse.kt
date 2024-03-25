package com.example.foodie.data.entity

import com.google.gson.annotations.SerializedName

data class CartProductsResponse(
    @SerializedName("sepet_yemekler")
    val products: List<CartProduct>,
    val success: Int)
