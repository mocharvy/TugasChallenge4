package com.programmer.challenge4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.programmer.challenge4.R
import com.programmer.challenge4.item.MenuItem

class MenuAdapter(private val menuItems: MutableList<MenuItem>, private val onItemClick: (MenuItem) -> Unit) :
    RecyclerView.Adapter<MenuAdapter.MenuViewHolder>() {

    class MenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.txtFoodName)
        val itemPrice: TextView = itemView.findViewById(R.id.txtFoodPrice)
        val itemImage: ImageView = itemView.findViewById(R.id.imgFood)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.menu_item, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val menuItem = menuItems[position]

        holder.itemName.text = menuItem.name
        holder.itemPrice.text = menuItem.price
        holder.itemImage.setImageResource(menuItem.imageRes)

        holder.itemView.setOnClickListener {
            onItemClick(menuItem)
        }
    }

    override fun getItemCount(): Int {
        return menuItems.size
    }
}
