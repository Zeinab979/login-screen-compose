package com.example.loginpage.domain.usecase

data class RestaurantUseCase(
    val listRestaurantsUseCase: ListRestaurantsUseCase,
    val restaurantDetailsUseCase: RestaurantDetailsUseCase
)
