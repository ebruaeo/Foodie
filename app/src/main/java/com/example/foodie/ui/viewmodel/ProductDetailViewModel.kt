package com.example.foodie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
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

    val screenState = MutableLiveData(ScreenState.ADD)
    fun addToCart(product: Product, productCount: Int) {
        CartData.addProduct(product, productCount, repository)
    }

    fun screenStateToUpdate() {
        screenState.value = ScreenState.UPDATE
    }
    
    fun screenStateToAdd() {
        screenState.value = ScreenState.ADD
    }

    enum class ScreenState(val btnText: String, val snackbarText: String) {
        ADD("Sepete Ekle", "Ürün sepete eklendi"),
        UPDATE("Sepeti Güncelle", "Sepet Güncellendi")
    }
}