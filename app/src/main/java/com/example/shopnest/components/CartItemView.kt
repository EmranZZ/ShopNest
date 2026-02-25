package com.example.shopnest.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.shopnest.model.ProductModel
import com.example.shopnest.utils.Utils
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */

@Composable
fun CartItemView(
    productId: String,
    quantity: Long,
    navController: NavHostController
) {

    var productModel by remember {
        mutableStateOf(ProductModel())
    }

    val context = LocalContext.current

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

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(12.dp)
    ) {

        Row (
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            AsyncImage(
                model = productModel.images.firstOrNull(),
                contentDescription = productModel.title,
                Modifier
                    .width(120.dp)
                    .height(120.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .padding(8.dp)
            )

            Column(
                Modifier.weight(1f).padding(8.dp)
            ) {

                Text(
                    text = productModel.title,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = "$"+ productModel.price,
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "$"+ productModel.actualPrice,
                        fontSize = 12.sp,
                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    )
                }

                Spacer(Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = {
                            Utils.removeFromCart(context, productModel.id)
                        }
                    ) {
                        Text("-")
                    }

                    BadgedBox(
                        badge = {
                            if(quantity > 0){
                                Badge(
                                    containerColor = Color.Red,
                                    contentColor = Color.White
                                ){
                                    Text(quantity.toString())
                                }
                            }
                        }
                    ) {

                        Icon(
                            imageVector = Icons.Filled.ShoppingCart,
                            contentDescription = "Cart",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(
                        onClick = {
                            Utils.addToCart(context, productModel.id)
                        }
                    ) {
                        Text("+")
                    }
                }
            }

            IconButton(
                onClick = {
                    Utils.removeFromCart(context, productModel.id, removeAll = true)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Remove from Cart",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

        }
    }
}