package com.programmer.challenge4.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmer.challenge4.databinding.MenuItemBinding
import com.programmer.challenge4.item.CartItem


class ConfirmOrderAdapter :
    ListAdapter<CartItem, ConfirmOrderAdapter.ViewHolder>(CartItemDiffCallback()) {

    inner class ViewHolder(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            binding.imgMakanan.setImageResource(cartItem.imageResourceId)
            binding.txtCartItem.text = cartItem.foodName
            "Rp. ${cartItem.totalPrice}".also { binding.txtItemPrice.text = it }
            binding.txtQuantity.text = cartItem.quantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MenuItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bind(cartItem)
    }

    private class CartItemDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }
}

