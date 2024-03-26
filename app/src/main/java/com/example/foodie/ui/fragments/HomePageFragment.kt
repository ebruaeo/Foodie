package com.example.foodie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.R
import com.example.foodie.data.CartData
import com.example.foodie.data.FavData
import com.example.foodie.ui.adapter.ProductListAdapter
import com.example.foodie.databinding.FragmentHomePageBinding
import com.example.foodie.ui.viewmodel.HomePageViewModel
import com.example.foodie.utils.gecis
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel: HomePageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.productList.observe(viewLifecycleOwner) {
            viewModel.favoriteProductIds = FavData.getAll(requireContext())?.keys ?: setOf()
            val productAdapter = ProductListAdapter(it)
            binding.progressBar.visibility = View.GONE
            binding.productRecyclerView.adapter = productAdapter
//            addBadgeToFab(CartData.getProductList().size)

        }

        binding.progressBar.visibility = View.VISIBLE
        viewModel.fetchAllProducts()


        binding.fabCart.setOnClickListener {
            Navigation.gecis(it, R.id.action_homePageFragment_to_cartFragment)
        }

        binding.imageViewFavorite.setOnClickListener {
            Navigation.gecis(it, R.id.action_homePageFragment_to_favoritesFragment)
        }


        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.search(query)
                return true
            }
        })


    }

    @androidx.annotation.OptIn(com.google.android.material.badge.ExperimentalBadgeUtils::class)
    private fun addBadgeToFab(count: Int) {
        if (count != 0) {
            val badgeDrawable = BadgeDrawable.create(requireContext())
            badgeDrawable.number = count
            badgeDrawable.horizontalOffset = 30
            badgeDrawable.verticalOffset = 20
            BadgeUtils.attachBadgeDrawable(badgeDrawable, binding.fabCart)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }


}