package com.example.foodie.data.datasource

import android.util.Log

class ProductsDataSource {

    suspend fun sil(product_id: Int) {
        Log.e("Ürün sil", product_id.toString())
    }
}