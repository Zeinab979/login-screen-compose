package com.example.loginpage.domain.repository

import com.example.loginpage.data.model.ListRestaurants
import com.example.loginpage.util.DataState
import kotlinx.coroutines.flow.Flow

interface RestaurantsRepository {
    suspend fun getRestaurants():Flow<DataState<ListRestaurants>>
}