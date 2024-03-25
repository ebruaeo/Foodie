package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product
import com.example.foodie.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(var productRepo: ProductsRepository) : ViewModel() {

    var productList = MutableLiveData<List<CartProduct>>()

    fun removeProduct(product: CartProduct) {
        CartData.removeProduct(product, productRepo)
        productList.value = CartData.getProductList()
    }

    fun emptyCart() {
        viewModelScope.launch {
            CartData.emptyCart(productRepo)
            productList.value = CartData.getProductList()
        }

    }

}