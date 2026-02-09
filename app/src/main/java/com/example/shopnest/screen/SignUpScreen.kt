package com.example.shopnest.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.shopnest.R
import com.example.shopnest.navigation.Screen
import com.example.shopnest.utils.AppUtils
import com.example.shopnest.viewmodel.AuthViewModel

/**
 * @author EMRAN AHMED
 */
@Composable
fun SignUpScreen(modifier: Modifier = Modifier, viewModel: AuthViewModel = viewModel(), navController: NavHostController){
    var email by remember {
        mutableStateOf("")
    }

    var name by remember{
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var isLoading by remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    Column (
        modifier = modifier.fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( text = "Hello there!",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(Modifier.height(10.dp))

        Text( text = "Create an account",
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily.Monospace
            )
        )
        Spacer(Modifier.height(20.dp))

        Image(painter = painterResource(R.drawable.shop_nest_banner),
            contentDescription = "Banner",
            Modifier.fillMaxWidth()
                .height(200.dp))

        Spacer(Modifier.height(20.dp))
        OutlinedTextField(value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text("Full Name")
            },
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(10.dp))
        OutlinedTextField(value = email,
            onValueChange = {
                email = it
            },
            label = {
                Text("Email")
            },
            modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(10.dp))
        OutlinedTextField(value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text("Password")
            },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation())
        Spacer(Modifier.height(20.dp))

        Button(onClick = {
            isLoading = true
            viewModel.signup(name, email, password){ success, errorMessage ->
                if(success){
                    isLoading = false

                    navController.navigate(Screen.Home.route){
                        popUpTo(Screen.Auth.route){
                            inclusive = true
                        }
                    }

                } else{
                    AppUtils.showText(context, errorMessage?:"Something went wrong")
                    isLoading = false
                }
            }
        },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth()
                .height(60.dp)) {
            Text(if(isLoading) "Creating Account" else "Sign Up", fontSize = 22.sp)
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview(){
//    SignUpScreen()
//}