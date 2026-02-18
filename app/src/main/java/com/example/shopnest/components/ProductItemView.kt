package com.example.shopnest.components

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.shopnest.model.ProductModel

/**
 * @author EMRAN AHMED
 */

@Composable
fun ProductItemView(product: ProductModel, modifier: Modifier, navController: NavHostController){
    Card(
        modifier = modifier.padding(8.dp)
            .clickable{
                navController.navigate("product_details/${product.id}")
            },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp,
            pressedElevation = 10.dp,
            hoveredElevation = 12.dp
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column (
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = product.images.firstOrNull(),
                contentDescription = product.title,
                Modifier.height(120.dp).fillMaxWidth()
            )

            Text(
                text = product.title, modifier = Modifier.padding(8.dp).fillMaxWidth(),
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "$"+ product.price,
                fontSize = 14.sp,
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = "$"+ product.actualPrice,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.weight(1f))

            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to Cart",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}