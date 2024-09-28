package com.example.loginpage.presentation.screens.popular_cities_list

import com.example.loginpage.data.model.Product

data class RestaurantsListUiState(
    val products: List<Product> = emptyList()
)
