package com.programmer.challenge4.adapter


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.programmer.challenge4.databinding.CartItemBinding
import com.programmer.challenge4.item.CartItem
import com.programmer.challenge4.viewmodel.CartViewModel

class CartAdapter(private val viewModel: CartViewModel) :
    ListAdapter<CartItem, CartAdapter.CartViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }


    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val currentItem = getItem(position)

        holder.bind(currentItem)

        holder.binding.btnPlus.setOnClickListener {
            // Menambah jumlah item
            val quantity = currentItem.quantity++
            updateCartItem(currentItem)
            currentItem.totalPrice = currentItem.calculateTotalPrice()
            notifyItemChanged(holder.bindingAdapterPosition)
        }

        holder.binding.btnMinus.setOnClickListener {
            // Mengurangi jumlah item
            currentItem.quantity--
            currentItem.totalPrice = currentItem.calculateTotalPrice()
            if (currentItem.quantity < 1) {

                showDeleteConfirmationDialog(holder)
            }
            updateCartItem(currentItem)
            notifyItemChanged(holder.bindingAdapterPosition)
        }

        holder.binding.ivDelete.setOnClickListener {
            showDeleteConfirmationDialog(holder)
        }
    }
    fun calculateTotalPrice(): Int {
        var totalPrice = 0
        currentList.forEach { cartItem ->
            totalPrice += cartItem.totalPrice
        }

        return totalPrice
    }

    private fun updateCartItem(cartItem: CartItem) {
        viewModel.updateCartItem(cartItem)
    }


    inner class CartViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cartItem: CartItem) {
            binding.ivFood.setImageResource(cartItem.imageResourceId)
            binding.tvDesc.text = cartItem.foodName
            binding.tvPrice.text = "Rp. ${cartItem.totalPrice}"
            binding.tvNumber.text = cartItem.quantity.toString()

        }
    }
    private fun showDeleteConfirmationDialog(holder: CartViewHolder) {
        val position = holder.bindingAdapterPosition
        if (position != RecyclerView.NO_POSITION) {
            getItem(position)

            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Delete Item")
                .setMessage("Anda yakin ingin menghapus Item ini?")
                .setPositiveButton("Delete") { _, _ ->
                    onItemClick()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }
    }

    private fun onItemClick() {

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CartItem>() {
            override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem.foodName == newItem.foodName
            }

            override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
