package com.example.foodie.data

import android.content.Context
import com.example.foodie.data.entity.Product

object FavData {
    fun save(product: Product, context: Context) {
        context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        ).edit().run {
            putString(product.productId.toString(), product.productName)
            apply()
        }
    }

    fun remove(product: Product, context: Context) {
        context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        ).edit().run {
            remove(product.productId.toString())
            apply()
        }
    }


    fun getAll(context: Context): MutableMap<String, *>? {
        return context.getSharedPreferences(
            context.packageName,
            Context.MODE_PRIVATE
        ).all
    }
}
