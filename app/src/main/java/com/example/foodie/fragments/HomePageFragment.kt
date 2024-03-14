package com.example.foodie.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import com.example.foodie.R
import com.example.foodie.adapter.ProductAdapter
import com.example.foodie.data.entity.Products
import com.example.foodie.databinding.FragmentHomePageBinding


class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
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
            Products("",50,"Ayran","200ml"),
            Products("",100,"Baklava","500gr"),
            Products("",150,"Köfte","1 kilo")
        )

        val productAdapter= ProductAdapter(productList)
        binding.productRecyclerView.adapter =productAdapter


        binding.fabCart.setOnClickListener {
            val action = HomePageFragmentDirections.actionHomePageFragmentToCartFragment()
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


}