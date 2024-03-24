package com.example.foodie.data.entity

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Product(
    @SerializedName("yemek_id")
    var product_id: Int,

    @SerializedName("yemek_resim_adi")
    var product_pic: String,

    @SerializedName("yemek_fiyat")
    var product_price: Int,

    @SerializedName("yemek_adi")
    var product_name: String,

    var product_count: Int = 0,
    var isFavorited: Boolean = false
) : Serializable