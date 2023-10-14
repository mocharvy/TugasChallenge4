package com.programmer.challenge4.item

data class MenuItem(
    val name: String,
    val price: String,
    val description: String,
    val imageRes: Int,
    val restaurantAddress: String,
    val googleMapsUrl: String = ""
)