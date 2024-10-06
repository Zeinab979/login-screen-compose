package com.example.loginpage.presentation.screens.coming_soon_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.data.model.Product
import com.example.loginpage.domain.usecase.RestaurantDetailsUseCase
import com.example.loginpage.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantDetailsViewModel @Inject constructor(
    private val getRestaurantDetailsUseCase: RestaurantDetailsUseCase
) : ViewModel() {

    private val _restaurantDetails: MutableStateFlow<DataState<Product>> = MutableStateFlow(DataState.Idle)
    val restaurantDetails: StateFlow<DataState<Product>> = _restaurantDetails


    fun fetchRestaurantDetails(id: Int) {
        viewModelScope.launch {
          getRestaurantDetailsUseCase(id).collect{
              _restaurantDetails.value = it
          }
        }
    }
}
