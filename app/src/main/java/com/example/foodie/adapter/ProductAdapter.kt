package com.example.foodie.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.data.entity.Products
import com.example.foodie.databinding.DesigningProductBinding
import com.example.foodie.fragments.HomePageFragmentDirections

class ProductAdapter(var productList: List<Products>) :
    RecyclerView.Adapter<ProductAdapter.designingProductHolder>() {

    inner class designingProductHolder(var designing: DesigningProductBinding) :
        RecyclerView.ViewHolder(designing.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): designingProductHolder {
        val binding =
            DesigningProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return designingProductHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: designingProductHolder, position: Int) {
        val product = productList.get(position)
        val t = holder.designing
        t.productName.text = product.product_name
        t.productDescription.text = product.product_description
        t.productPrice.text="${product.product_price}"

        t.productCardView.setOnClickListener {
            val gecis =
                HomePageFragmentDirections.actionHomePageFragmentToProductDetailFragment(product = product)
            Navigation.findNavController(it).navigate(gecis)
        }
    }

}