package com.example.foodie.data.repository

import com.example.foodie.data.datasource.ProductsDataSource

class ProductsRepository {

    var productDS = ProductsDataSource()
    suspend fun sil(product_id: Int) = productDS.sil(product_id )
}