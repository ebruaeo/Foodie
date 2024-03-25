package com.example.foodie.data

import com.example.foodie.data.entity.Product

object CartData {
    private val productList = mutableListOf<Product>()

    fun getTotalPrice(): Int {
        var totalPrice = 0
        for (p in productList) {
            totalPrice += p.product_price * p.product_count
        }
        return totalPrice
    }



    fun addProduct(product: Product) {
        if (isProductAlreadyAdded(product)) {
            val addedProduct = getProduct(product.product_id)!!
            addedProduct.product_count = product.product_count
        } else {
            productList.add(product)
        }
    }

    fun getProductList() = productList as List<Product>

    fun getProduct(productId: Int): Product? {
        for (p in productList) {
            if (p.product_id == productId)
                return p
        }
        return null
    }

    fun isProductAlreadyAdded(product: Product): Boolean {
        for (p in productList) {
            if (p.product_id == product.product_id)
                return true
        }
        return false
    }

    fun removeProduct(product: Product) {
        productList.remove(product)
    }


}