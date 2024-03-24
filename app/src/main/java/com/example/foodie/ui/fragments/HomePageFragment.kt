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
import com.example.foodie.ui.adapter.ProductListAdapter
import com.example.foodie.databinding.FragmentHomePageBinding
import com.example.foodie.ui.viewmodel.HomePageViewModel
import com.example.foodie.utils.gecis
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
            val productAdapter = ProductListAdapter(it)
            binding.productRecyclerView.adapter = productAdapter
        }

        viewModel.productYukle()


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel = tempViewModel
    }

}