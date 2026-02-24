package com.example.shopnest.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopnest.components.CartItemView
import com.example.shopnest.model.UserModel
import com.example.shopnest.navigation.Screen
import com.example.shopnest.utils.AppUtils
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */

@Composable
fun CartPage(navController: NavHostController, modifier: Modifier = Modifier) {

//    var cartItems by remember {
//        mutableStateOf<Map<String, Long>>(emptyMap())
//    }

    var userModel by remember {
        mutableStateOf(UserModel())
    }


    Column(
        modifier = modifier.fillMaxSize()
            .padding(16.dp)
    ) {

        DisposableEffect(Unit) {
            val listener = Firebase.firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid!!)

                .addSnapshotListener { it, _ ->
                    if (it != null && it.exists()){
                        val result = it.toObject(UserModel::class.java)

                        if (result != null) {
                            userModel = result
                        }
                    }
                }

            onDispose {
                listener.remove()
            }
        }

        Text(
            text = "Your Cart",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
        ) {
            items(
                userModel.cartItems.toList(),
                key = {
                    it.first
                }
            ) { (productId, qty) ->
                CartItemView(
                    productId = productId,
                    quantity = qty,
                    navController = navController
                )
            }
        }

        Button(
            onClick = {
                navController.navigate(Screen.Checkout.route)
            },
            Modifier.fillMaxWidth()
                .height(50.dp)
        ) {
            Text(
                text = "Checkout",
                fontSize = 20.sp
            )
        }
    }
}