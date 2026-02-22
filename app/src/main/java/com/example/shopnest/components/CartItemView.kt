package com.example.shopnest.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopnest.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */

@Composable
fun CartItemView(
    productId: String,
    quantity: Long,
    modifier: Modifier,
    navController: NavHostController
) {

    var productModel by remember {
        mutableStateOf<ProductModel?>(null)
    }

    Text(
        text = "Product ID: $productId, \nQuantity: $quantity",
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(16.dp)
    )

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data")
            .document("stock")
            .collection("products")
            .document(productId)
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val product = it.result.toObject(ProductModel::class.java)

                    if(product != null){
                        productModel = product
                    }
                }
            }
    }

    Text(
        text = "Product Name: ${productModel?.title}, \nPrice: ${productModel?.price}",
        fontWeight = FontWeight.Normal,
        modifier = Modifier.padding(16.dp)
    )
}