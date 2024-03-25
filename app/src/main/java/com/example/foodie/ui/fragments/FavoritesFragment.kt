package com.example.foodie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.data.FavData
import com.example.foodie.ui.adapter.FavListAdapter
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.FragmentFavoritesBinding
import com.example.foodie.ui.viewmodel.FavoritesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {


    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel: FavoritesViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        viewModel.fetchAllProducts()
        viewModel.favoriteProductIds = FavData.getAll(requireContext())?.keys ?: setOf()

        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        binding.cartButton.setOnClickListener {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToCartFragment()
            Navigation.findNavController(it).navigate(action)
        }


    }

    private fun observeViewModel() {
        viewModel.productList.observe(viewLifecycleOwner) { productList ->
            val adapterData = mutableListOf<Product>()
            val favDataIds = FavData.getAll(requireContext())?.keys
            for (p in productList) {
                if (favDataIds?.contains(p.productId.toString()) == true) {
                    adapterData.add(p)
                }
            }
            switchNoItemFoundText(adapterData)
            val favProductAdapter = FavListAdapter(adapterData, viewModel)
            binding.favRecyclerView.adapter = favProductAdapter
        }
    }

    private fun switchNoItemFoundText(adapterData: MutableList<Product>) {
        if (adapterData.isEmpty()) {
            binding.noItemFoundText.visibility = View.VISIBLE
        } else {
            binding.noItemFoundText.visibility = View.GONE
        }
    }

}