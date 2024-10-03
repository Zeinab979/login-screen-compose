package com.example.loginpage.data.repository

import android.util.Log
import com.example.loginpage.data.data_source.ApiService
import com.example.loginpage.data.model.ListRestaurants
import com.example.loginpage.data.model.Product
import com.example.loginpage.domain.repository.RestaurantsRepository
import com.example.loginpage.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RestaurantsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : RestaurantsRepository {

    override suspend fun getRestaurants(): Flow<DataState<ListRestaurants>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.getRestaurants()
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    Log.d("RestaurantsRepository", "Fetch restaurants successful: $data")
                    emit(DataState.Success(data))
                } ?: run {
                    emit(DataState.Error("Fetch failed: Empty response body"))
                }
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Fetch failed: Unknown error"
                Log.e("RestaurantsRepository", errorMessage)
                emit(DataState.Error(errorMessage))
            }
        } catch (e: Exception) {
            Log.e("RestaurantsRepository", "Fetch failed: ${e.localizedMessage ?: e.message}")
            emit(DataState.Error("Fetch failed: ${e.localizedMessage ?: e.message}"))
        }
    }.flowOn(Dispatchers.IO)

    override suspend fun getRestaurantsDetails(id: Int): Flow<DataState<Product>> = flow {
        emit(DataState.Loading)
        try {
            val response = apiService.getRestaurantDetails(id)
            if (response.isSuccessful) {
                response.body()?.let { data ->
                    Log.d("RestaurantsRepository", "Fetch restaurant details successful: $data")
                    emit(DataState.Success(data))
                } ?: run {
                    emit(DataState.Error("Fetch failed: Empty response body"))
                }
            } else {
                val errorMessage = response.errorBody()?.string() ?: "Fetch failed: Unknown error"
                Log.e("RestaurantsRepository", errorMessage)
                emit(DataState.Error(errorMessage))
            }
        } catch (e: Exception) {
            Log.e("RestaurantsRepository", "Fetch failed: ${e.localizedMessage ?: e.message}")
            emit(DataState.Error("Fetch failed: ${e.localizedMessage ?: e.message}"))
        }
    }.flowOn(Dispatchers.IO)
}
