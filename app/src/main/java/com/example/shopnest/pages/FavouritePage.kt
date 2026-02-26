package com.example.shopnest.pages

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.shopnest.components.ProductItemView
import com.example.shopnest.model.ProductModel
import com.example.shopnest.utils.Utils
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavouritePage(navController: NavHostController, modifier: Modifier = Modifier){

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    "ShopNest",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }) { innerPadding ->

        var productList by remember {
            mutableStateOf<List<ProductModel>>(emptyList())
        }

        val context = LocalContext.current

        LaunchedEffect(Unit) {
            val favouriteList = Utils.getFavourite(context)

            Firebase.firestore.collection("data")
                .document("stock")
                .collection("products")
                .whereIn("id", favouriteList.toList())
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        // Converting the documents to ProductModel objects
                        val result = it.result.documents.mapNotNull { docs ->
                            docs.toObject(ProductModel::class.java)
                        }
                        productList = result
                    }
                }
        }

        LazyColumn (modifier = Modifier.padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp)
        ) {
            items(productList.chunked(2)){ product->

                Row() {
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