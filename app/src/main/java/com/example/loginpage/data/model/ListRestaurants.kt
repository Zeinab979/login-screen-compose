package com.example.loginpage.data.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListRestaurants(
    val status: Boolean,
    val message: String,
    @SerialName("data")
    val `data`: List<Data>,
    val count: Int
)
@Serializable
data class Data(
    val id: Int,
    val name: String,
    val address: String,
    @SerialName("restaurant_lat")
    val restaurantLat: String,
    @SerialName("restaurant_long")
    val restaurantLong: String,
    val image: String,
    val products: List<Product>
)
@Serializable
data class Product(
    val id: Int,
    val name: String,
    val price: Int,
    val quantity: Int,
    @SerialName("restaurant_id")
    val restaurantId: Int,
    val image: String
)