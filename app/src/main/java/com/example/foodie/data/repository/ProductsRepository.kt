package com.example.foodie.data.repository

import com.example.foodie.data.datasource.ProductsDataSource
import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product
import com.example.foodie.retrofit.ApiUtils

class ProductsRepository(var productDS: ProductsDataSource) {

    suspend fun getAllProducts(): List<Product> = productDS.getAllProducts()

    suspend fun search(keyword: String): List<Product> = productDS.search(keyword)

    suspend fun fetchCartProducts(): List<CartProduct> = productDS.fetchCartProducts()

    suspend fun removeProductFromCart(
        productId: Int,
        username: String = ApiUtils.USERNAME
    ) = productDS.removeProductFromCart(productId, username)

    suspend fun addProductToCart(cartProduct: CartProduct) = productDS.addProductToCart(cartProduct)
}