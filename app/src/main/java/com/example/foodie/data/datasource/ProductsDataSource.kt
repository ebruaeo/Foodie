package com.example.foodie.data.datasource

import android.util.Log
import com.example.foodie.data.entity.Product
import com.example.foodie.retrofit.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsDataSource (var productApi: ProductApi){
    private var list = listOf<Product>()

    suspend fun sil(product_id: Int) {
        Log.e("Ürün sil", product_id.toString())
    }


    suspend fun getAllProducts(): List<Product> =
        withContext(Dispatchers.IO) {
            list = productApi.getAllProducts().products
            return@withContext list
        }


    suspend fun search(keyword: String): ArrayList<Product> =
        withContext(Dispatchers.IO) {
            var filteredList = arrayListOf<Product>()
            for (i in 0..list.size - 1) {
                if (list[i].productName.contains(keyword, true)) {
                    filteredList.add(list[i])

                }
            }

            return@withContext filteredList

        }
}