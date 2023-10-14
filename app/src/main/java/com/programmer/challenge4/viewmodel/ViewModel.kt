package com.programmer.challenge4.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.programmer.challenge4.item.CartItem
import com.programmer.challenge4.repository.CartRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CartViewModel(private val repository: CartRepository) : ViewModel() {

    val allCartItems: LiveData<List<CartItem>> = repository.allCartItems


    fun insertCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.insertCartItem(cartItem)
            }
        }
    }
    fun updateCartItem(cartItem: CartItem) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCartItem(cartItem)
        }
    }

    fun deleteCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteCartItem(cartItem)
            }
        }
    }
    fun deleteAllCartItems() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                repository.deleteAllCartItems()
            }
        }
    }



}
