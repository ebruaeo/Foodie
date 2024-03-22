package com.example.foodie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.R
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.DesigningProductBinding
import com.example.foodie.ui.fragments.HomePageFragmentDirections
import com.example.foodie.utils.gecis

class ProductListAdapter(var productList: List<Product>) :
    RecyclerView.Adapter<ProductListAdapter.DesigningProductHolder>() {

    inner class DesigningProductHolder(var designing: DesigningProductBinding) :
        RecyclerView.ViewHolder(designing.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesigningProductHolder {
        val binding =
            DesigningProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DesigningProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: DesigningProductHolder, position: Int) {
        val product = productList.get(position)
        val t = holder.designing
        t.productName.text = product.product_name
        t.productDescription.text = product.product_description
        t.productPrice.text = "${product.product_price}"

        t.favButton.setOnClickListener {
            if (product.isFavorited) {
                t.favButton.setImageResource(R.drawable.red_favorite_icon)
                product.isFavorited = false
            } else {
                t.favButton.setImageResource(R.drawable.ic_fav_filled)
                product.isFavorited = true

            }
        }

        t.productCardView.setOnClickListener {
            val gecis =
                HomePageFragmentDirections.actionHomePageFragmentToProductDetailFragment(product = product)
            Navigation.gecis(it, gecis)
        }

    }

}