package com.example.foodie.data.repository

import com.example.foodie.data.datasource.ProductsDataSource
import com.example.foodie.data.entity.Product

class ProductsRepository(var productDS:ProductsDataSource) {

    suspend fun sil(product_id: Int) = productDS.sil(product_id)

    suspend fun productYukle(): List<Product> = productDS.productYukle()

    suspend fun ara(aramaKelimesi: String): List<Product> = productDS.ara(aramaKelimesi)
}