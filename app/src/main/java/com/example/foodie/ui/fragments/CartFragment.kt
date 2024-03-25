package com.example.foodie.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.CartProduct
import com.example.foodie.databinding.FragmentCartBinding
import com.example.foodie.ui.adapter.CartListAdapter
import com.example.foodie.ui.viewmodel.CartViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private val viewModel: CartViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        val cartListAdapter = CartListAdapter(CartData.getProductList(), viewModel)
        binding.sepetRecyclerView.adapter = cartListAdapter
        setTotalPrice()
        switchNoItemFoundText(cartListAdapter.cartProductList)

        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        binding.btnEmptyCart.setOnClickListener {
            viewModel.emptyCart()
            Snackbar.make(binding.btnEmptyCart, "Sepet boşaltıldı", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        viewModel.productList.observe(viewLifecycleOwner) {
            binding.sepetRecyclerView.adapter?.notifyDataSetChanged()
            setTotalPrice()
            switchNoItemFoundText(it)
        }
    }

    private fun switchNoItemFoundText(productList: List<CartProduct>) {
        if (productList.isEmpty()) {
            binding.noItemFoundText.visibility = View.VISIBLE
        } else {
            binding.noItemFoundText.visibility = View.GONE
        }
    }

    private fun setTotalPrice() {
        var totalPrice = CartData.getTotalPrice()
        binding.subTotal.text = "$totalPrice₺"
        binding.cartTotalPrice.text = "$totalPrice₺"
    }


}