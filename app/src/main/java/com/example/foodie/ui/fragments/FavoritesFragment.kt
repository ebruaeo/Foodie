package com.example.foodie.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.ui.adapter.FavListAdapter
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.FragmentFavoritesBinding
import com.example.foodie.ui.viewmodel.FavoritesViewModel

class FavoritesFragment : Fragment() {

    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var viewModel: FavoritesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(inflater,container,false)

        val favProductList = listOf(
            Product(0,"",50,"Ayran","200ml"),
            Product(1,"",100,"Baklava","500gr"),
            Product(2,"",150,"Köfte","1 kilo")
        )

        val favProductAdapter = FavListAdapter(favProductList)
        binding.favRecyclerView.adapter = favProductAdapter


        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).popBackStack()
        }

        binding.cartButton.setOnClickListener {
            val action = FavoritesFragmentDirections.actionFavoritesFragmentToCartFragment()
            Navigation.findNavController(it).navigate(action)
        }


        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FavoritesViewModel by viewModels()
        viewModel=tempViewModel
    }
}