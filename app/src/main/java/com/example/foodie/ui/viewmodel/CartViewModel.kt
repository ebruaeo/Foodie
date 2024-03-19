package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.data.entity.Product
import com.example.foodie.data.repository.ProductsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class CartViewModel @Inject constructor(var productRepo : ProductsRepository): ViewModel() {

    var productList = MutableLiveData<List<Product>>()


    fun sil(product_id: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            productRepo.sil(product_id)
            productYukle()

        }
    }

    fun productYukle() {
        CoroutineScope(Dispatchers.Main).launch {
            productList.value = productRepo.productYukle()
        }
    }

}