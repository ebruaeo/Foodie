package com.example.foodie.data.entity

import com.example.foodie.retrofit.ApiUtils
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("yemek_id")
    var productId: Int,

    @SerializedName("yemek_resim_adi")
    var productImgName: String,

    @SerializedName("yemek_fiyat")
    var productPrice: Int,

    @SerializedName("yemek_adi")
    var productName: String,

    var isFavorited: Boolean = false
) : Serializable {
    fun toCartProduct(count: Int = 0) = CartProduct(productId, productName, productImgName, productPrice, count, ApiUtils.USERNAME)
}