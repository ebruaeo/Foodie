package com.example.foodie.data

import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product
import com.example.foodie.data.entity.RemoveProductResponse
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
            CoroutineScope(Dispatchers.IO).launch {
                removeProduct(addedProduct, repository)

                repository.addProductToCart(addedProduct)
                fetchCartProducts(repository)
            }
        } else {
            CoroutineScope(Dispatchers.IO).launch {
                repository.addProductToCart(product.toCartProduct(count))
                fetchCartProducts(repository)
            }
        }
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

    suspend fun removeProduct(product: CartProduct, repository: ProductsRepository): RemoveProductResponse {
            productList.remove(product)
            return repository.removeProductFromCart(product.productId)
    }

    fun fetchCartProducts(repository: ProductsRepository) {
        CoroutineScope(Dispatchers.IO).launch {
            productList.clear()
            productList.addAll(repository.fetchCartProducts())
        }
    }

    suspend fun emptyCart(repository: ProductsRepository) {
        for (p in productList) {
            repository.removeProductFromCart(p.productId)
        }
        productList.clear()
    }

}