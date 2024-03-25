package com.example.foodie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.CartProduct
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.FragmentProductDetailBinding
import com.example.foodie.retrofit.ApiUtils
import com.example.foodie.ui.viewmodel.ProductDetailViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : Fragment() {

    private lateinit var binding: FragmentProductDetailBinding
    private val viewModel: ProductDetailViewModel by viewModels()
    private var snackbar: Snackbar? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle: ProductDetailFragmentArgs by navArgs()
        val gelenProduct = bundle.product

        binding.productName.text = gelenProduct.productName
        val url = ApiUtils.constructImgUrl(gelenProduct.productImgName)
        Glide.with(requireContext())
            .load(url)
            .into(binding.productPicture)

        if (CartData.isProductAlreadyAdded(gelenProduct.productId)) {
            val addedProduct = CartData.getProduct(gelenProduct.productId)!!
            binding.totalPrice.text = getTotalPriceOf(addedProduct).toString()
            binding.productCount.text = addedProduct.productCount.toString()
            binding.btnSepeteEkle.text = "Sepeti güncelle"
        } else {
            binding.totalPrice.text = gelenProduct.productPrice.toString()

        }

        setOnClickListeners(gelenProduct)
    }

    private fun getTotalPriceOf(product: CartProduct): Int {
        return product.productPrice * product.productCount
    }

    private fun setOnClickListeners(gelenProduct: Product) {
        setSepeteEkleOnClickListener(gelenProduct)
        setDecrementOnClickListener(gelenProduct)
        setIncrementOnClickListener(gelenProduct)
        setBackOnClickListener()
    }

    private fun setBackOnClickListener() {
        binding.btnBack.setOnClickListener {
            snackbar?.dismiss()
            Navigation.findNavController(it).popBackStack()
        }
    }

    private fun setIncrementOnClickListener(gelenProduct: Product) {
        binding.btnIncrement.setOnClickListener {
            val productCount = binding.productCount.text.toString().toInt() + 1
            binding.productCount.text = productCount.toString()
            binding.totalPrice.text = "${productCount * gelenProduct.productPrice}"

        }
    }

    private fun setDecrementOnClickListener(gelenProduct: Product) {
        binding.btnDecrement.setOnClickListener {
            if (binding.productCount.text.toString().toInt() == 1) {
                Snackbar.make(it, "En az 1 ürün eklemelisiniz.", Snackbar.LENGTH_SHORT).show()
            } else {
                val productCount = binding.productCount.text.toString().toInt() - 1
                binding.productCount.text = productCount.toString()
                binding.totalPrice.text = "${productCount * gelenProduct.productPrice}"
            }
        }
    }

    private fun setSepeteEkleOnClickListener(gelenProduct: Product) {
        binding.btnSepeteEkle.setOnClickListener {
            val productCount = binding.productCount.text.toString().toInt()
            viewModel.addToCart(gelenProduct, productCount)
            snackbar = Snackbar.make(it, "Ürün sepete eklendi.", Snackbar.LENGTH_SHORT)
                .setAction("Sepete git") {
                    val action =
                        ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
                    Navigation.findNavController(binding.btnSepeteEkle).navigate(action)
                }
            snackbar?.show()
        }
    }

}