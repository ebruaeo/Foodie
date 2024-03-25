package com.example.foodie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.example.foodie.data.CartData
import com.example.foodie.data.repository.ProductsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var repository: ProductsRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        CartData.fetchCartProducts(repository)
    }
}