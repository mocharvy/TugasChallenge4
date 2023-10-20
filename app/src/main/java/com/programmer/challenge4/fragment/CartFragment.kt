package com.programmer.challenge4.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.programmer.challenge4.adapter.CartAdapter
import com.programmer.challenge4.databinding.FragmentCartBinding
import com.programmer.challenge4.viewmodel.CartViewModel
import com.programmer.challenge4.viewmodel.ViewModelFactory


class CartFragment : Fragment() {

    private lateinit var binding: FragmentCartBinding
    private lateinit var viewModel: CartViewModel
    private lateinit var cartAdapter: CartAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, ViewModelFactory(requireActivity().application)).get(CartViewModel::class.java)


        setupRecyclerView()
        observeCartItems()

        binding.btnpesan.setOnClickListener {
            val navController = findNavController()
            val action = CartFragmentDirections.actionCartFragmentToConfirmOrderActivity()
            navController.navigate(action)
        }

        return binding.root
    }
    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(viewModel) { cartItem ->

            viewModel.deleteCartItem(cartItem)

        }

        binding.rvMenuMakanan.apply {
            adapter = cartAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeCartItems() =
        viewModel.allCartItems.observe(viewLifecycleOwner, Observer { cartItems ->
            cartAdapter.submitList(cartItems)
            val totalPrice = cartAdapter.calculateTotalPrice()
            binding.txtTotalPrice.text = "Total Price: Rp. $totalPrice"
        })


}