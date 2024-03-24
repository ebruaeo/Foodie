package com.example.foodie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.DesigningFavProductBinding
import com.example.foodie.ui.fragments.FavoritesFragmentDirections
import com.google.android.material.snackbar.Snackbar

class FavListAdapter(var favProductList: List<Product>) :
    RecyclerView.Adapter<FavListAdapter.DesigningFavHolder>() {

    inner class DesigningFavHolder(var designing: DesigningFavProductBinding) :
        RecyclerView.ViewHolder(designing.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DesigningFavHolder {
        val binding =
            DesigningFavProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DesigningFavHolder(binding)
    }

    override fun getItemCount(): Int {
        return favProductList.size
    }

    override fun onBindViewHolder(holder: DesigningFavHolder, position: Int) {
        val favProduct = favProductList.get(position)
        val t = holder.designing

        t.productName.text = favProduct.product_name
        t.productPrice.text = "${favProduct.product_price}"

        t.favProductCardView.setOnClickListener {
            val action =
                FavoritesFragmentDirections.actionFavoritesFragmentToProductDetailFragment(product = favProduct)
            Navigation.findNavController(it).navigate(action)
        }
        t.btnSepeteEkle.setOnClickListener {
            val p = favProduct.copy()
            p.product_count = 1
            CartData.productList.add(p)
            Snackbar.make(it, "Ürün sepete eklendi.", Snackbar.LENGTH_SHORT).show()
        }


    }


}