package com.example.foodie.data.entity

import com.google.gson.annotations.SerializedName

data class CartProduct(
    @SerializedName("sepet_yemek_id")
    val productId: Int,

    @SerializedName("yemek_adi")
    val productName: String,

    @SerializedName("yemek_resim_adi")
    val productImgName: String,

    @SerializedName("yemek_fiyat")
    val productPrice: Int,

    @SerializedName("yemek_siparis_adet")
    var productCount: Int,

    @SerializedName("kullanici_adi")
    val username: String,


)
