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
import com.example.loginpage.data.popularCities
import com.example.loginpage.presentation.screens.StartScreen
import com.example.loginpage.presentation.screens.coming_soon_details.ComingSoonDetails
import com.example.loginpage.presentation.screens.login.LoginScreen
import com.example.loginpage.presentation.screens.login.LoginViewModel
import com.example.loginpage.presentation.screens.popular_cities_list.PopularCitiesList
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
                viewModel = signUpViewModel
            )
        }
        composable(NavigationItem.Login.route) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                onLoginSuccess = { navController.navigate(NavigationItem.PopularCitiesList.route) },
                onNavigateToSignUp = {
                    navController.navigate(NavigationItem.SignUp.route) {
                        popUpTo(NavigationItem.SignUp.route) { inclusive = true }
                    }
                },
                viewModel = loginViewModel
            )
        }
        composable(NavigationItem.PopularCitiesList.route) {
            PopularCitiesList(
                onItemClicked = { index ->
                    Log.d("PopularCitiesListIndex", "Navigating to CityDetails with index: $index")
                    navController.navigate("${NavigationItem.CityDetails.route}/$index")
                }
            )
        }
        composable(
            route = "${NavigationItem.CityDetails.route}/{index}",
            arguments = listOf(navArgument("index") { NavType.IntType })
        ) { backStackEntry ->
            val index = backStackEntry.arguments?.getInt("index") ?: -1
            Log.d("NavHost", "CityDetails received index: $index")

            if (index in popularCities.indices) {
                val city = popularCities[index]
                Log.d("NavHost", "CityDetails displaying: ${city.cityName}")

                ComingSoonDetails(
                    cityDetails = city,
                    onBackClick = {navController.navigate(NavigationItem.PopularCitiesList.route) {
                        popUpTo(NavigationItem.SignUp.route) { inclusive = true }
                    }
                    }
                )
            } else {
                Log.e("NavHost", "Invalid city index: $index")
            }
        }


    }
}


