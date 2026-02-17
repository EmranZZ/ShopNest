package com.example.shopnest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shopnest.model.ProductModel

/**
 * @author EMRAN AHMED
 */

@Composable
fun ProductItemView(product: ProductModel, modifier: Modifier){
//    Card(modifier = Modifier.size(130.dp)
//        .clickable{
//
//        },
//        elevation = CardDefaults.cardElevation(
//            defaultElevation = 2.dp,
//            pressedElevation = 4.dp,
//            hoveredElevation = 8.dp
//        ),
//        shape = RoundedCornerShape(12.dp)
//    ) {
        AsyncImage(
            model = product.images.firstOrNull(),
            contentDescription = product.title,
            modifier.height(120.dp)
                .fillMaxSize()
        )
//    }
}