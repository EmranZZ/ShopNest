package com.example.shopnest.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.AsyncImage
import com.example.shopnest.model.CategoryModel

/**
 * @author EMRAN AHMED
 */

@Composable
fun CategoryProductPage(modifier: Modifier, category: CategoryModel){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AsyncImage(
            model = category.imageUrl,
            contentDescription = category.name
        )

        Text(text = "category Product Page: ${category.name}")
    }
}