package com.example.loginpage.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loginpage.presentation.screens.StartScreen
import com.example.loginpage.presentation.screens.coming_soon_details.ComingSoonDetails
import com.example.loginpage.presentation.screens.login.LoginScreen
import com.example.loginpage.presentation.screens.login.LoginViewModel
import com.example.loginpage.presentation.screens.popular_list.PopularList
import com.example.loginpage.presentation.screens.popular_list.RestaurantsListViewModel
import com.example.loginpage.presentation.screens.signUp.SignUpScreen
import com.example.loginpage.presentation.screens.signUp.SignUpViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = NavigationItem.StartScreen.route
    ) {
        composable(NavigationItem.StartScreen.route) {
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
                    navController.navigate(NavigationItem.PopularList.route)
                },
                viewModel = signUpViewModel,
                uiStateFlow = signUpViewModel.uiState,
                signUpStateFlow = signUpViewModel.signUpState
            )
        }
        composable(NavigationItem.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                onLoginSuccess = { navController.navigate(NavigationItem.PopularList.route) },
                onNavigateToSignUp = {
                    navController.navigate(NavigationItem.SignUp.route) {
                        navController.navigateUp()                    }
                },
                viewModel = loginViewModel,
                uiStateFlow = loginViewModel.uiState,
                loginStateFlow = loginViewModel.loginState
            )
        }
        composable(NavigationItem.PopularList.route) {
            val restaurantsListViewModel : RestaurantsListViewModel = hiltViewModel()
            PopularList(
                uiState = restaurantsListViewModel.listState,
                onItemClicked = { product ->
                    navController.navigate(NavigationItem.Details.createRoute(product.id))
                }
            )
        }
        composable(
            route = "${NavigationItem.Details.route}/{productId}",
            arguments = listOf(
                navArgument("productId") { NavType.IntType },
            )
        )
        { backStackEntry ->
            val productId = backStackEntry.arguments?.getInt("productId")
            val restaurantsListViewModel: RestaurantsListViewModel = hiltViewModel()
            if (productId != null) {
                val product = restaurantsListViewModel.getProductById(productId)
                if (product != null) {
                ComingSoonDetails(
                    product = product,
                    onBackClick = { navController.navigateUp() }
                )
            } else {
                Log.e("NavHost", "Product with ID $productId not found")
            }
            } else {
                Log.e("NavHost", "productId is null")
            }
        }
        }

}



