package com.example.foodie.data

import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product
import com.example.foodie.data.repository.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CartData {

    private val productList = mutableListOf<CartProduct>()

    fun getTotalPrice(): Int {
        var totalPrice = 0
        for (p in productList) {
            totalPrice += p.productPrice * p.productCount
        }
        return totalPrice
    }


    fun addProduct(product: Product, count: Int, repository: ProductsRepository) {
        if (isProductAlreadyAdded(product.productName)) {
            val addedProduct = getProduct(product.productName)!!
            addedProduct.productCount = count
        } else {
            productList.add(product.toCartProduct(count))
        }
        updateCart(repository)
    }

    fun getProductList() = productList as List<CartProduct>

    fun getProduct(productName: String): CartProduct? {
        for (p in productList) {
            if (p.productName == productName)
                return p
        }
        return null
    }

    fun isProductAlreadyAdded(productName: String): Boolean {
        for (p in productList) {
            if (p.productName == productName)
                return true
        }
        return false
    }

    fun removeProduct(product: CartProduct, repository: ProductsRepository) {
        productList.remove(product)
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeProductFromCart(product.productId)
        }
    }

    fun fetchCartProducts(repository: ProductsRepository) {
        CoroutineScope(Dispatchers.IO).launch {
            productList.addAll(repository.fetchCartProducts())
        }
    }

    private fun updateCart(repository: ProductsRepository) {
        CoroutineScope(Dispatchers.IO).launch {
            removeAllCartProducts(repository)
            addAllCartProducts(repository)
        }
    }

    private suspend fun removeAllCartProducts(repository: ProductsRepository) {
        for (p in productList) {
            repository.removeProductFromCart(p.productId)
        }
    }

    private suspend fun addAllCartProducts(repository: ProductsRepository) {
        for (p in productList) {
            repository.addProductToCart(p)
        }
    }

    suspend fun emptyCart(repository: ProductsRepository) {
            removeAllCartProducts(repository)
            productList.clear()
    }

}