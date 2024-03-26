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
import com.example.foodie.R
import com.example.foodie.data.CartData
import com.example.foodie.data.FavData
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
    val bundle: ProductDetailFragmentArgs by navArgs()
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
        observeViewModel()
        val gelenProduct = bundle.product

        binding.productName.text = gelenProduct.productName
        setFavBtnBackground(gelenProduct)
        setProductImage(gelenProduct)
        setOnClickListeners(gelenProduct)
    }

    override fun onResume() {
        super.onResume()
        prepareScreen(bundle.product)
    }

    private fun observeViewModel() {
        viewModel.screenState.observe(viewLifecycleOwner) {
            binding.btnSepeteEkle.text = it.btnText
        }
    }

    private fun prepareScreen(gelenProduct: Product) {
        if (CartData.isProductAlreadyAdded(gelenProduct.productName)) {
            viewModel.screenStateToUpdate()
            val addedProduct = CartData.getProduct(gelenProduct.productName)!!
            binding.totalPrice.text = getTotalPriceOf(addedProduct).toString()
            binding.productCount.text = addedProduct.productCount.toString()
        } else {
            viewModel.screenStateToAdd()
            binding.totalPrice.text = gelenProduct.productPrice.toString()
        }
    }

    private fun setProductImage(gelenProduct: Product) {
        val url = ApiUtils.constructImgUrl(gelenProduct.productImgName)
        Glide.with(requireContext())
            .load(url)
            .into(binding.productPicture)
    }

    private fun setFavBtnOnClickListener(gelenProduct: Product) {
        binding.btnFav.setOnClickListener {
            if (gelenProduct.isFavorited) {
                binding.btnFav.setImageResource(R.drawable.red_favorite_icon)
                gelenProduct.isFavorited = false
                FavData.remove(gelenProduct, requireContext())
            } else {
                binding.btnFav.setImageResource(R.drawable.ic_fav_filled)
                gelenProduct.isFavorited = true
                FavData.save(gelenProduct, requireContext())
            }
        }
    }

    private fun setFavBtnBackground(gelenProduct: Product) {
        if (gelenProduct.isFavorited) {
            binding.btnFav.setImageResource(R.drawable.ic_fav_filled)
        } else {
            binding.btnFav.setImageResource(R.drawable.red_favorite_icon)
        }
    }

    private fun getTotalPriceOf(product: CartProduct): Int {
        return product.productPrice * product.productCount
    }

    private fun setOnClickListeners(gelenProduct: Product) {
        setSepeteEkleOnClickListener(gelenProduct)
        setFavBtnOnClickListener(gelenProduct)
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
            snackbar = Snackbar.make(it, viewModel.screenState.value!!.snackbarText, Snackbar.LENGTH_SHORT)
                .setAction("Sepete git") {
                    val action =
                        ProductDetailFragmentDirections.actionProductDetailFragmentToCartFragment()
                    Navigation.findNavController(binding.btnSepeteEkle).navigate(action)
                }
            snackbar?.show()
            viewModel.screenStateToUpdate()
        }
    }

}