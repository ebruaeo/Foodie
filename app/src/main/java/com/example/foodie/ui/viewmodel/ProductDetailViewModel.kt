package com.example.foodie.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.Product
import com.example.foodie.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val repository: ProductsRepository
) : ViewModel() {

    fun addToCart(product: Product, productCount: Int) {
        CartData.addProduct(product, productCount, repository)
    }

}