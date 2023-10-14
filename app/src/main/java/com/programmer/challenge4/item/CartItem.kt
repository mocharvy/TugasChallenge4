package com.programmer.challenge4.item

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_item")
data class CartItem(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val foodName: String,
    var totalPrice: Int,
    var price: Int,
    var quantity: Int,
    val imageResourceId: Int

)
{
    fun calculateTotalPrice(): Int {
        return quantity * price
    }
}
