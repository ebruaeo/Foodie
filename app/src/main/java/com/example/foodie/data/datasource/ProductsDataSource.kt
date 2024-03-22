package com.example.foodie.data.datasource

import android.util.Log
import com.example.foodie.data.entity.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductsDataSource {
    var list = listOf<Product>()

    suspend fun sil(product_id: Int) {
        Log.e("Ürün sil", product_id.toString())
    }


    suspend fun productYukle(): List<Product> =
        withContext(Dispatchers.IO) {

            list = listOf(
                Product(0, "", 50, "Ayran", "200ml"),
                Product(1, "", 100, "Baklava", "500gr"),
                Product(2, "", 150, "Köfte", "1 kilo")
            )

            return@withContext list

        }


    suspend fun ara(aramaKelimesi: String): ArrayList<Product> =
        withContext(Dispatchers.IO) {
            var filteredList = arrayListOf<Product>()
            for (i in 0..list.size - 1) {
                if (list[i].product_name.contains(aramaKelimesi, true)) {
                    filteredList.add(list[i])

                }
            }

            return@withContext filteredList

        }
}