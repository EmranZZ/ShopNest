package com.example.shopnest.pages

import android.R.attr.fontWeight
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.shopnest.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */

@Composable
fun CartPage(navController: NavHostController, modifier: Modifier = Modifier){

    var cartItems by remember {
        mutableStateOf<Map<String, Long>>(emptyMap())
    }

    var product by remember {
        mutableStateOf<List<ProductModel>>(emptyList())
    }

    var productName by remember {
        mutableStateOf<String>("")
    }

    Column (
        modifier = modifier.fillMaxSize()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LaunchedEffect(Unit) {
            Firebase.firestore.collection("users")
                .document(FirebaseAuth.getInstance().currentUser?.uid!!)
                .get()
                .addOnCompleteListener { userInfo ->
                    if(userInfo.isSuccessful){
                     val cartData = userInfo.result.get("cartItems") as? Map<String, Long> ?: emptyMap()

                        if(cartData.isNotEmpty()){
                            cartItems = cartData
                        }
                    }
                }

        }

        cartItems.forEach {(productId, quantity) ->

            Firebase.firestore.collection("data")
                .document("stock")
                .collection("products")
                .get()
                .addOnCompleteListener { productInfo ->
                    if(productInfo.isSuccessful){

                        val result = productInfo.result.documents.mapNotNull {
                            it.toObject(ProductModel::class.java)
                        }
                        product = result

                        //val title = productInfo.result.get("title") as? String ?: "Unknown Product"
                        //productName = title
                    }
                }

            LazyColumn {

            }

            Card (
                modifier = Modifier.fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .clickable{},
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 6.dp
                ),
                shape = RoundedCornerShape(12.dp)
            ) {

//                Row(
//                    Modifier.fillMaxWidth()
//                ) {
//                    AsyncImage(
//                        model = product.images.firstOrNull(),
//                        contentDescription = product.description,
//                        Modifier.height(120.dp).padding(12.dp)
//                    )
//
//                    Column(
//                        verticalArrangement = Arrangement.Center,
//                        modifier = Modifier.padding(12.dp)
//                    ) {
//                        Text(
//                            text = product.title,
//                            modifier = Modifier.fillMaxWidth(),
//                            fontWeight = FontWeight.Bold,
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis
//                        )
//
//                        Text(
//                            text = product.description,
//                            fontSize = 12.sp,
//                            modifier = Modifier.fillMaxWidth(),
//                            maxLines = 1,
//                            overflow = TextOverflow.Ellipsis
//                        )
//
//                        Spacer(Modifier.height(8.dp))
//                        HorizontalDivider(thickness = 2.dp)
//                        Spacer(Modifier.height(8.dp))
//
//                        Row(
//                            modifier = Modifier.fillMaxWidth(),
//                            verticalAlignment = Alignment.CenterVertically,
//                            horizontalArrangement = Arrangement.SpaceAround
//                        ) {
//                            Text(
//                                text = "Qty:",
//                                fontSize = 16.sp,
//                                fontWeight = FontWeight.SemiBold
//                            )
//
//
//                            BadgedBox(
//                                badge = {
//                                    if (quantity > 0) {
//                                        Badge(
//                                            containerColor = Color.Red,
//                                            contentColor = Color.White
//                                        ) {
//                                            Text(text = "$quantity")
//                                        }
//                                    }
//                                }
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Filled.ShoppingCart,
//                                    contentDescription = "Shopping Cart"
//                                )
//                            }
//
//                            Button(onClick = { }) {
//                                Text("Add")
//                            }
//                        }
//                    }
//
//                }

                Text(
                    text = "Product ID: $productName, \nQuantity: $quantity",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(Modifier.height(20.dp))
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(Modifier.height(20.dp))
        }
    }
}