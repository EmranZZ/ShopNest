package com.example.shopnest.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopnest.R

/**
 * @author EMRAN AHMED
 */
@Composable
fun SignUpScreen(modifier: Modifier = Modifier){
    var email by remember {
        mutableStateOf("")
    }

    Column (
        modifier = modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text( text = "Hello there!",
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(10.dp))

        Text( text = "Create an account",
            style = TextStyle(
                fontSize = 22.sp,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(20.dp))

        Image(painter = painterResource(R.drawable.signup_banner),
            contentDescription = "Banner",
            Modifier.fillMaxWidth()
                .height(150.dp))

        OutlinedTextField(value = email,
            onValueChange = {
                email = it
            })
    }
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview(){
    SignUpScreen()
}