package com.example.foodie.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodie.R
import com.example.foodie.data.CartData
import com.example.foodie.data.FavData
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.DesigningFavProductBinding
import com.example.foodie.retrofit.ApiUtils
import com.example.foodie.ui.fragments.FavoritesFragmentDirections
import com.example.foodie.ui.viewmodel.FavoritesViewModel
import com.google.android.material.snackbar.Snackbar

class FavListAdapter(
    private val favProductList: List<Product>,
    private val viewModel: FavoritesViewModel
) :
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

        t.productName.text = favProduct.productName
        t.productPrice.text = "${favProduct.productPrice}₺"
        val url = ApiUtils.constructImgUrl(favProduct.productImgName)
        Glide.with(t.productImage.context)
            .load(url)
            .override(500, 500)
            .into(t.productImage)

        t.favProductCardView.setOnClickListener {
            val action =
                FavoritesFragmentDirections.actionFavoritesFragmentToProductDetailFragment(product = favProduct)
            Navigation.findNavController(it).navigate(action)
        }

        t.btnSepeteEkle.setOnClickListener {
            viewModel.addToCart(favProduct)
            Snackbar.make(it, "Ürün sepete eklendi.", Snackbar.LENGTH_SHORT).show()
        }

        t.favButton.setOnClickListener {
            if (favProduct.isFavorited) {
                t.favButton.setImageResource(R.drawable.red_favorite_icon)
                favProduct.isFavorited = false
                FavData.remove(favProduct, it.context)
            } else {
                t.favButton.setImageResource(R.drawable.ic_fav_filled)
                favProduct.isFavorited = true
                FavData.save(favProduct, it.context)
            }
        }

    }


}