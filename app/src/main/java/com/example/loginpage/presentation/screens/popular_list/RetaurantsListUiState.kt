package com.example.loginpage.presentation.screens.popular_list

import com.example.loginpage.data.model.Product

data class RestaurantsListUiState(
    val products: List<Product> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)
