package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.Product
import com.example.foodie.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(private var productRepo: ProductsRepository) : ViewModel() {
    var productList = MutableLiveData<List<Product>>()
    var favoriteProductIds = setOf<String>()

    fun fetchAllProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            productList.value = productRepo.getAllProducts()
        }
    }

    fun addToCart(product: Product) {
        CartData.addProduct(product, 1, productRepo)
    }
}