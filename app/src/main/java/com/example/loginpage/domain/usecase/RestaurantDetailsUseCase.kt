package com.example.loginpage.domain.usecase

import com.example.loginpage.domain.repository.RestaurantsRepository

class RestaurantDetailsUseCase(private val repository: RestaurantsRepository) {
    suspend operator fun invoke(id : Int) = repository.getRestaurantsDetails(id)
}