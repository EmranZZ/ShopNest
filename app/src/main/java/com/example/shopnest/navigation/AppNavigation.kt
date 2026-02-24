package com.example.shopnest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopnest.pages.CheckoutPage
import com.example.shopnest.pages.ProductDetailsPage
import com.example.shopnest.screen.AuthScreen
import com.example.shopnest.screen.HomeScreen
import com.example.shopnest.screen.LoginScreen
import com.example.shopnest.screen.ProductCategoriesScreen
import com.example.shopnest.screen.SignUpScreen
import com.google.firebase.auth.FirebaseAuth

/**
 * @author EMRAN AHMED
 */

@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()

    val isLoggedIn = FirebaseAuth.getInstance().currentUser != null
    val firstPage = if(isLoggedIn) Screen.Home.route else Screen.Auth.route


    NavHost(navController = navController, startDestination = firstPage ){

        composable(Screen.Auth.route) {
            AuthScreen(modifier, navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(modifier, navController = navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(modifier, navController = navController)
        }
        composable(Screen.Home.route) {
            HomeScreen( navController = navController)
        }

        composable(Screen.CategoryProduct.route) {
            val categoryId = it.arguments?.getString("categoryId") ?: ""
            ProductCategoriesScreen(categoryId, modifier, navController )
        }

        composable(Screen.ProductDetails.route){
            val productId = it.arguments?.getString("productId") ?: ""
            ProductDetailsPage(productId, modifier, navController)
        }

        composable(Screen.Checkout.route) {
            CheckoutPage(navController, modifier)
        }
    }
}