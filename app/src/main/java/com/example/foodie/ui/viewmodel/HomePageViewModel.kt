package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodie.data.entity.Product
import com.example.foodie.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class HomePageViewModel @Inject constructor(var productRepo: ProductsRepository) : ViewModel() {

    var productList = MutableLiveData<List<Product>>()

    var favoriteProductIds = setOf<String>()
        set(value) {
            field = value
            updateProductListFavs()
        }

    private fun updateProductListFavs() {
        productList.value?.let {
            for (p in it) {
                if (favoriteProductIds.contains(p.productId.toString())) {
                    p.isFavorited = true
                }
            }
        }

    }

    fun fetchAllProducts() {
        viewModelScope.launch {
            productList.value = productRepo.getAllProducts()
        }

    }

    fun search(keyword: String) {
        CoroutineScope(Dispatchers.Main).launch {
            productList.value = productRepo.search(keyword)
        }
    }

}