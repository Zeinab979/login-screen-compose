package com.example.loginpage.domain.usecase

import com.example.loginpage.data.model.ListRestaurants
import com.example.loginpage.domain.repository.RestaurantsRepository
import com.example.loginpage.util.DataState
import kotlinx.coroutines.flow.Flow

class ListRestaurantsUseCase(private val repository: RestaurantsRepository ) {
   suspend operator fun invoke(): Flow<DataState<ListRestaurants>> = repository.getRestaurants()
}
