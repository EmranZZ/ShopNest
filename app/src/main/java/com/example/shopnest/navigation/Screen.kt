package com.example.shopnest.navigation

/**
 * @author EMRAN AHMED
 */

sealed class Screen(val route: String){
    object Auth: Screen("auth")
    object Login: Screen("login")
    object SignUp: Screen("signup")
    object Home: Screen("home")
    object CategoryProduct: Screen("category_product/{categoryId}")
    object ProductDetails: Screen("product_details/{productId}")
    object Checkout: Screen("checkout")
}