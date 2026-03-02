package com.example.shopnest.screen

import android.widget.Space
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopnest.components.ProductItemView
import com.example.shopnest.components.TopBar
import com.example.shopnest.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCategoriesScreen(category: String, modifier: Modifier, navController: NavHostController){

    var productList by remember {
        mutableStateOf<List<ProductModel>>(emptyList())
    }

    LaunchedEffect(Unit) {

        Firebase.firestore.collection("data")
            .document("stock")
            .collection("products")
            .whereEqualTo("category", category)
            .get()

            .addOnCompleteListener {
                if (it.isSuccessful){
                    // Converting the documents to ProductModel objects
                    val result = it.result.documents.mapNotNull { docs ->
                        docs.toObject(ProductModel::class.java)
                    }
                    productList = result.plus(result).plus(result).plus(result)
                }
            }
    }

    Scaffold(topBar = {
        TopBar(
            title = category.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
        )

    }) { innerPadding ->

        LazyColumn (modifier = Modifier.fillMaxSize()
            .padding(innerPadding)
            .padding(start = 8.dp, end = 8.dp)
        ) {

            items(productList.chunked(2)){ product->
                Row {
                    product.forEach {
                        ProductItemView(product = it, modifier = Modifier.weight(1f), navController)
                    }

                    if(product.size == 1){
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Spacer(Modifier.height(5.dp))
            }
        }
    }
}