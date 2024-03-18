package com.example.foodie.data

import com.example.foodie.data.entity.Product

object CartData {
    val productList = mutableListOf<Product>(
        Product(0, "", 50, "Ayran", "200ml"),
        Product(1, "", 100, "Baklava", "500gr"),
        Product(2, "", 150, "Köfte", "1 kilo")
    )
}