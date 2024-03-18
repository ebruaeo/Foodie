package com.example.foodie.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.foodie.data.repository.ProductsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel: ViewModel() {
    var productRepo = ProductsRepository()

   fun sil(product_id: Int) {
       CoroutineScope(Dispatchers.Main).launch {
           productRepo.sil(product_id)
       }
   }

}