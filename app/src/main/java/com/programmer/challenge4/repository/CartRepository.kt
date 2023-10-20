package com.programmer.challenge4.repository

import androidx.lifecycle.LiveData
import com.programmer.challenge4.database.CartItemDao
import com.programmer.challenge4.item.CartItem

class CartRepository(private val cartDao: CartItemDao) {

    val allCartItems: LiveData<List<CartItem>> = cartDao.getAllCartItems()

    fun insertCartItem(cartItem: CartItem) {
        cartDao.insertCartItem(cartItem)
    }

    fun deleteCartItem(cartItem: CartItem) {
        cartDao.deleteCartItem(cartItem)
    }

    fun updateCartItem(cartItem: CartItem) {
        cartDao.updateCartItem(cartItem)
    }

    fun deleteAllCartItems() {
        cartDao.deleteAllCartItems()
    }
}
