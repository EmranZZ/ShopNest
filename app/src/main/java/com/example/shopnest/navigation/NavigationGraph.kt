package com.example.shopnest.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shopnest.screen.AuthScreen
import com.example.shopnest.screen.LoginScreen
import com.example.shopnest.screen.SignUpScreen

/**
 * @author EMRAN AHMED
 */

@Composable
fun AppNavigation(modifier: Modifier = Modifier){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "auth"){

        composable(Screen.Auth.route) {
            AuthScreen(modifier, navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(modifier)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(modifier)
        }
    }
}