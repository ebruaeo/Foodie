package com.example.foodie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.DesigningCartProductsBinding

class CartListAdapter(var cartProductList: List<Product>) :
    RecyclerView.Adapter<CartListAdapter.DesigningCartHolder>() {

    inner class DesigningCartHolder(var designing: DesigningCartProductsBinding) :
        RecyclerView.ViewHolder(designing.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesigningCartHolder {
       val binding = DesigningCartProductsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return DesigningCartHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartProductList.size
    }

    override fun onBindViewHolder(holder: DesigningCartHolder, position: Int) {
       val product = cartProductList.get(position)
        val t = holder.designing
        t.cartProductName.text = product.product_name
        t.cartProductCount.text =product.product_count.toString()



    }


}