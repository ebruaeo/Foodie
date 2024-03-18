package com.example.foodie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodie.R
import com.example.foodie.data.CartData
import com.example.foodie.databinding.FragmentProductDetailBinding
import com.example.foodie.ui.viewmodel.FavoritesViewModel
import com.example.foodie.ui.viewmodel.ProductDetailViewModel
import com.google.android.material.snackbar.Snackbar

class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private lateinit var viewModel: ProductDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        val bundle: ProductDetailFragmentArgs by navArgs()
        val gelenProduct = bundle.product

        binding.productName.setText(gelenProduct.product_name)
        binding.productDescription.setText(gelenProduct.product_description)



        binding.btnSepeteEkle.setOnClickListener {
            val p = gelenProduct.copy()
            p.product_count = binding.productCount.text.toString().toInt()
            CartData.productList.add(p)

            Snackbar.make(it, "Ürün sepete eklendi.", Snackbar.LENGTH_SHORT)
                .setAction("Sepete git") {
                    val action =
                        ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
                    Navigation.findNavController(binding.btnSepeteEkle).navigate(action)
                }.show()
        }

        binding.btnDecrement.setOnClickListener {
            if (binding.productCount.text.toString().toInt() == 1) {
                Snackbar.make(it, "En az 1 ürün eklemelisiniz.", Snackbar.LENGTH_SHORT).show()
            } else {
                binding.productCount.text =
                    (binding.productCount.text.toString().toInt() - 1).toString()
                binding.totalPrice.text = (binding.totalPrice.text.toString()
                    .toInt() - gelenProduct.product_price).toString()


            }
        }

        binding.btnIncrement.setOnClickListener {
            binding.productCount.text =
                (binding.productCount.text.toString().toInt() + 1).toString()
            binding.totalPrice.text =
                (binding.totalPrice.text.toString().toInt() + gelenProduct.product_price).toString()
        }





        binding.btnBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}