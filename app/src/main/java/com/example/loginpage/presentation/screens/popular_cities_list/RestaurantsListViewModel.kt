package com.example.loginpage.presentation.screens.popular_cities_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.data.model.ListRestaurants
import com.example.loginpage.domain.usecase.ListRestaurantsUseCase
import com.example.loginpage.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantsListViewModel @Inject constructor(
    private val getRestaurantsUseCase: ListRestaurantsUseCase,
) : ViewModel() {

    private val _restaurantList: MutableStateFlow<DataState<ListRestaurants>> = MutableStateFlow(DataState.Idle)
    private val _listState = MutableStateFlow(RestaurantsListUiState())
    val listState : StateFlow<RestaurantsListUiState> = _listState
    init{
        fetchRestaurants()
    }
    private fun fetchRestaurants() {
        _restaurantList.value = DataState.Loading
        viewModelScope.launch {
            getRestaurantsUseCase().collect { response ->
                _restaurantList.value = response
                when(response){
                    is DataState.Success -> {
                        val products = response.data.data.flatMap { it.products }
                       _listState.update {
                            it.copy(products = products)
                        }

                    }
                    is DataState.Error ->{}
                    is DataState.Idle -> {}
                    is DataState.Loading ->{}
                }
            }
        }
    }

}
