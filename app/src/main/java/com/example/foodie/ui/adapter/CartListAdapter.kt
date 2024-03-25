package com.example.foodie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.DesigningCartProductsBinding
import com.example.foodie.ui.viewmodel.CartViewModel
import com.google.android.material.snackbar.Snackbar

class CartListAdapter(
    var cartProductList: List<CartProduct>,
    var viewModel: CartViewModel
) :
    RecyclerView.Adapter<CartListAdapter.DesigningCartHolder>() {

    inner class DesigningCartHolder(var designing: DesigningCartProductsBinding) :
        RecyclerView.ViewHolder(designing.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesigningCartHolder {
        val binding =
            DesigningCartProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DesigningCartHolder(binding)
    }

    override fun getItemCount(): Int {
        return cartProductList.size
    }

    override fun onBindViewHolder(holder: DesigningCartHolder, position: Int) {
        val product = cartProductList[position]
        val t = holder.designing
        t.cartProductName.text = product.productName
        t.cartProductCount.text = product.productCount.toString()
        t.totalPrice.text = "${product.productCount * product.productPrice}₺"


        t.btnDelete.setOnClickListener {
            viewModel.removeProduct(product)
            this.notifyItemRemoved(position)
            Snackbar.make(it, "Ürün sepetten kaldırıldı.", Snackbar.LENGTH_SHORT)
                .show()
        }

    }
}