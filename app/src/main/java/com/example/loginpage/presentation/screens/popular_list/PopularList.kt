package com.example.loginpage.presentation.screens.popular_list

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import com.example.loginpage.R
import com.example.loginpage.data.model.Product
import kotlinx.coroutines.flow.StateFlow

@Composable
fun PopularList(
    onItemClicked: (Product) -> Unit,
    uiState: StateFlow<RestaurantsListUiState>
) {
    val state by uiState.collectAsStateWithLifecycle()

    when {
        state.isLoading -> {
            Text("Loading...", modifier = Modifier.fillMaxSize())
        }
        state.error != null -> {
            Text("Error: ${state.error}", modifier = Modifier.fillMaxSize())
        }
        state.products.isEmpty() -> {
            Text("No products available", modifier = Modifier.fillMaxSize())
        }
        else -> {
            LazyColumn {
                item {
                    Column(
                        horizontalAlignment = Alignment.Start,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 14.dp)
                    ) {
                        Image(
                            painter = rememberAsyncImagePainter(model = R.drawable.logo),
                            contentDescription = "logo",
                            modifier = Modifier
                                .padding(horizontal = 117.dp, vertical = 34.dp)
                                .size(135.dp, 116.dp)
                        )
                        Text(
                            text = stringResource(id = R.string.popular_cities),
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(bottom = 19.dp)
                        )
                    }
                }
                itemsIndexed(state.products) { _, product ->
                    ItemDetails(
                        uiState = product,
                        onItemClicked = {
                            Log.d("PopularList", "Navigating to CityDetails with productId: ${product.id}")
                            onItemClicked(product)
                        }
                    )
                }
            }
        }
    }
}

