package com.example.foodie.data.datasource

import android.util.Log
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product
import com.example.foodie.retrofit.ApiUtils
import com.example.foodie.retrofit.ProductApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.http.Field

class ProductsDataSource(var productApi: ProductApi) {
    private var list = listOf<Product>()


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

    suspend fun fetchCartProducts(username: String = ApiUtils.USERNAME) =
        withContext(Dispatchers.IO) {
            return@withContext try {
                productApi.fetchCartProducts(username).products
            } catch (e: Exception) {
                listOf<CartProduct>()
            }
        }

    suspend fun removeProductFromCart(
        productId: Int,
        username: String = ApiUtils.USERNAME
    ) = productApi.removeProductFromCart(productId, username)

    suspend fun addProductToCart(cartProduct: CartProduct) = productApi.addProductToCart(
        cartProduct.productName,
        cartProduct.productImgName,
        cartProduct.productPrice,
        cartProduct.productCount,
        cartProduct.username
    )
}