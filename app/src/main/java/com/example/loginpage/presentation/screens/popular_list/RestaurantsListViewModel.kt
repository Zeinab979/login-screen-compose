package com.example.loginpage.presentation.screens.popular_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginpage.data.model.Product
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

    private val _listState = MutableStateFlow(RestaurantsListUiState())
    val listState : StateFlow<RestaurantsListUiState> = _listState

    init {
        fetchRestaurants()
    }
    fun getProductById(id: Int): Product? {
        return _listState.value.products.find { it.id == id }
    }

    private fun fetchRestaurants() {
        _listState.update { it.copy(isLoading = true) }  // Set loading state to true
        viewModelScope.launch {
            getRestaurantsUseCase().collect { response ->
                when(response) {
                    is DataState.Success -> {
                        val products = response.data.data.flatMap { it.products }
                        _listState.update {
                            it.copy(products = products, isLoading = false, error = null)
                        }
                    }
                    is DataState.Error -> {
                        _listState.update {
                            it.copy(isLoading = false, error = response.message)
                        }
                    }
                    is DataState.Loading -> {
                    }
                    is DataState.Idle -> {
                    }
                }
            }
        }
    }
}
