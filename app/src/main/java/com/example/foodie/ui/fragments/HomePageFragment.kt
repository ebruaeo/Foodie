package com.example.foodie.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.foodie.ui.adapter.ProductListAdapter
import com.example.foodie.data.entity.Product
import com.example.foodie.databinding.FragmentHomePageBinding
import com.example.foodie.ui.viewmodel.HomePageViewModel


class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private lateinit var viewModel:HomePageViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productList = listOf(
            Product(0,"",50,"Ayran","200ml"),
            Product(1,"",100,"Baklava","500gr"),
            Product(2,"",150,"Köfte","1 kilo")
        )

        val productAdapter= ProductListAdapter(productList)
        binding.productRecyclerView.adapter =productAdapter


        binding.fabCart.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToCartFragment()
            Navigation.findNavController(it).navigate(action)
        }

        binding.imageViewFavorite.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToFavoritesFragment()
            Navigation.findNavController(it).navigate(action)
        }


        binding.searchView.setOnQueryTextListener(object: OnQueryTextListener{
            override fun onQueryTextChange(newText: String): Boolean {
                ara(newText)
                return true
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                ara(query)
                return true
            }
        })



    }

fun ara (aramaKelimesi:String){
    Log.e("Ürün ara", aramaKelimesi)
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: HomePageViewModel by viewModels()
        viewModel=tempViewModel
    }
}