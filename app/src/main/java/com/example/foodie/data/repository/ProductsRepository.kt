package com.example.foodie.data.repository

import com.example.foodie.data.datasource.ProductsDataSource
import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product

class ProductsRepository(var productDS:ProductsDataSource) {

    suspend fun sil(product_id: Int) = productDS.sil(product_id)

    suspend fun getAllProducts(): List<Product> = productDS.getAllProducts()

    suspend fun search(keyword: String): List<Product> = productDS.search(keyword)

    suspend fun fetchCartProducts(): List<CartProduct> = productDS.fetchCartProducts()

}