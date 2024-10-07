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
            PopularList(
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
            val productId = backStackEntry.arguments?.getInt("productId")
            val restaurantsListViewModel: RestaurantsListViewModel = hiltViewModel()

            val product = productId?.let { restaurantsListViewModel.getProductById(it) }

            if (product != null) {
                ComingSoonDetails(
                    product = product,
                    onBackClick = { navController.navigateUp() }
                )
            } else {
                Log.e("NavHost", "Product with ID $productId not found")
            }
        }



    }
}


