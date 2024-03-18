package com.example.foodie.data.entity

import java.io.Serializable

data class Product(
    var product_id: Int,
    var product_pic: String,
    var product_price: Int,
    var product_name: String,
    var product_description: String,
    var product_count: Int=0
) :
    Serializable {
}