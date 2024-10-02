package com.example.loginpage.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loginpage.presentation.screens.StartScreen
import com.example.loginpage.presentation.screens.coming_soon_details.ComingSoonDetails
import com.example.loginpage.presentation.screens.coming_soon_details.RestaurantDetailsViewModel
import com.example.loginpage.presentation.screens.login.LoginScreen
import com.example.loginpage.presentation.screens.login.LoginViewModel
import com.example.loginpage.presentation.screens.popular_cities_list.PopularCitiesList
import com.example.loginpage.presentation.screens.popular_cities_list.RestaurantsListViewModel
import com.example.loginpage.presentation.screens.signUp.SignUpScreen
import com.example.loginpage.presentation.screens.signUp.SignUpViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    startDestination: String = NavigationItem.SplashScreen.route,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.SplashScreen.route) {
            StartScreen(
                navigateTo = { navController.navigate(NavigationItem.SignUp.route) }
            )
        }
        composable(NavigationItem.SignUp.route) {
            val signUpViewModel: SignUpViewModel = hiltViewModel()
            SignUpScreen(
                onNavigateToLogin = { navController.navigate(NavigationItem.Login.route) },
                onSignUpSuccess = {
                    signUpViewModel.signUp()
                },
                viewModel = signUpViewModel,
                uiStateFlow = signUpViewModel.uiState,
                signUpStateFlow = signUpViewModel.signUpState
            )
        }
        composable(NavigationItem.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                onLoginSuccess = { navController.navigate(NavigationItem.PopularCitiesList.route) },
                onNavigateToSignUp = {
                    navController.navigate(NavigationItem.SignUp.route) {
                        navController.navigateUp()                    }
                },
                viewModel = loginViewModel,
                uiStateFlow = loginViewModel.uiState,
                loginStateFlow = loginViewModel.loginState
            )
        }
        composable(NavigationItem.PopularCitiesList.route) {
            val restaurantsListViewModel : RestaurantsListViewModel = hiltViewModel()
            PopularCitiesList(
                uiState = restaurantsListViewModel.listState,
                onItemClicked = { productId ->
                    navController.navigate("${NavigationItem.CityDetails.route}/$productId")
                }
            )
        }
        composable(
            route = "${NavigationItem.CityDetails.route}/{productId}",
            arguments = listOf(navArgument("productId") { NavType.IntType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId") ?: -1
            Log.d("NavHost", "CityDetails received index: $productId")

            if (productId != -1) {
                val detailsViewModel : RestaurantDetailsViewModel = hiltViewModel()
                detailsViewModel.fetchRestaurantDetails(productId)
                val productDetails = detailsViewModel.restaurantDetails.collectAsStateWithLifecycle().value

                ComingSoonDetails(
                    productDetails = productDetails,
                    onBackClick = { navController.navigateUp()}
                )
            } else {
                Log.e("NavHost", "Invalid city index: $productId")
            }
        }


    }
}


