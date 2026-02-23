package com.example.shopnest.pages

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopnest.components.CartItemView
import com.example.shopnest.model.UserModel
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
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
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

        LazyColumn {
            items(
                userModel.cartItems.toList(),
                key = {
                    it.first
                }
            ) { (productId, qty) ->
                CartItemView(
                    productId = productId,
                    quantity = qty,
                    modifier = Modifier.padding(8.dp),
                    navController = navController
                )
            }
        }
    }
}