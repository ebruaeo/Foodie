package com.example.foodie.data.entity

import java.io.Serializable

data class Products(
    var product_pic: String,
    var product_price: Int,
    var product_name: String,
    var product_description: String
) :
    Serializable {
}