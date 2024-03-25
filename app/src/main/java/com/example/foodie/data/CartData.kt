package com.example.foodie.data

import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product

object CartData {
    private val productList = mutableListOf<CartProduct>()

    fun getTotalPrice(): Int {
        var totalPrice = 0
        for (p in productList) {
            totalPrice += p.productPrice * p.productCount
        }
        return totalPrice
    }


    fun addProduct(product: Product, count: Int) {
        if (isProductAlreadyAdded(product.productId)) {
            val addedProduct = getProduct(product.productId)!!
            addedProduct.productCount = count
        } else {
            productList.add(product.toCartProduct(count))
        }
    }

//    fun addProduct(cartProduct: CartProduct) {
//
//    }

    fun getProductList() = productList as List<CartProduct>
    fun setProductList(productList: List<CartProduct>) {
        this.productList.addAll(productList)
    }

    fun getProduct(productId: Int): CartProduct? {
        for (p in productList) {
            if (p.productId == productId)
                return p
        }
        return null
    }

    fun isProductAlreadyAdded(productId: Int): Boolean {
        for (p in productList) {
            if (p.productId == productId)
                return true
        }
        return false
    }

    fun removeProduct(product: CartProduct) {
        productList.remove(product)
    }


}