package com.example.foodie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.FragmentProductDetailBinding
import com.example.foodie.ui.viewmodel.ProductDetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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

        binding.productName.text = gelenProduct.product_name
        binding.totalPrice.text = gelenProduct.product_price.toString()
        setOnClickListeners(gelenProduct)

        return binding.root
    }

    private fun setOnClickListeners(gelenProduct: Product) {
        setSepeteEkleOnClickListener(gelenProduct)
        setDecrementOnClickListener(gelenProduct)
        setIncrementOnClickListener(gelenProduct)
        setBackOnClickListener()
    }

    private fun setBackOnClickListener() {
        binding.btnBack.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun setIncrementOnClickListener(gelenProduct: Product) {
        binding.btnIncrement.setOnClickListener {
            val productCount = binding.productCount.text.toString().toInt() + 1
            binding.productCount.text = productCount.toString()
            binding.totalPrice.text = "${productCount * gelenProduct.product_price}"

        }
    }

    private fun setDecrementOnClickListener(gelenProduct: Product) {
        binding.btnDecrement.setOnClickListener {
            if (binding.productCount.text.toString().toInt() == 1) {
                Snackbar.make(it, "En az 1 ürün eklemelisiniz.", Snackbar.LENGTH_SHORT).show()
            } else {
                val productCount = binding.productCount.text.toString().toInt() - 1
                binding.productCount.text = productCount.toString()
                binding.totalPrice.text = "${productCount * gelenProduct.product_price}"
            }
        }
    }

    private fun setSepeteEkleOnClickListener(gelenProduct: Product) {
        binding.btnSepeteEkle.setOnClickListener {
            val p = gelenProduct.copy()
            p.product_count = binding.productCount.text.toString().toInt()
            CartData.productList.add(p)
// TODO sepette aynı ürün varsa adeti arttır
            Snackbar.make(it, "Ürün sepete eklendi.", Snackbar.LENGTH_SHORT)
                .setAction("Sepete git") {
                    val action =
                        ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
                    Navigation.findNavController(binding.btnSepeteEkle).navigate(action)
                }.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: ProductDetailViewModel by viewModels()
        viewModel = tempViewModel
    }

}