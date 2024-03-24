package com.example.foodie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.data.CartData
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.FragmentCartBinding
import com.example.foodie.ui.adapter.CartListAdapter
import com.example.foodie.ui.viewmodel.CartViewModel
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


        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartListAdapter = CartListAdapter(CartData.getProductList(), viewModel)
        binding.sepetRecyclerView.adapter = cartListAdapter
        var totalPrice = CartData.getTotalPrice()
        binding.subTotal.text = "$totalPrice₺"
        binding.cartTotalPrice.text = "$totalPrice₺"
    }

}