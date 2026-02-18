package com.example.shopnest.pages

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.shopnest.model.ProductModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.tbuonomo.viewpagerdotsindicator.compose.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.compose.model.DotGraphic
import com.tbuonomo.viewpagerdotsindicator.compose.type.ShiftIndicatorType
import kotlinx.coroutines.delay

/**
 * @author EMRAN AHMED
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsPage(productId: String, modifier: Modifier, navController: NavHostController) {

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = "Details Screen",
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
    }){ innerPadding ->

        var product by remember {
            mutableStateOf(ProductModel())
        }

        LaunchedEffect(
            Unit
        ) {
            Firebase.firestore.collection("data")
                .document("stock")
                .collection("products")
                .document(productId)
                .get()
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        val result = it.result.toObject(ProductModel::class.java)
                        if(result != null){
                            product = result
                        }
                    }
                }
        }

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = product.title
            )


            Column (
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp)
            ){
                val pagerState = rememberPagerState(
                    initialPage = 0,
                    pageCount = { product.images.size}
                )

                HorizontalPager(
                    state = pagerState,
                    Modifier.padding(2.dp)
                ) { page ->
                    AsyncImage(
                        model = product.images[page],
                        contentDescription = product.description,
                        Modifier.height(220.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp)),
                        alignment = Alignment.Center
                    )
                }

                Spacer(Modifier.height(5.dp))

                DotsIndicator(
                    dotCount = product.images.size,
                    pagerState = pagerState,
                    type = ShiftIndicatorType(
                        DotGraphic(
                            color = MaterialTheme.colorScheme.onSurface,
                            size = 8.dp
                        )
                    )
                )

                Spacer(Modifier.height(8.dp))

                Row (
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$"+ product.price,
                        fontSize = 16.sp,
                        style = TextStyle(textDecoration = TextDecoration.LineThrough)
                    )

                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "$"+ product.actualPrice,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(Modifier.weight(1f))

                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Add to Favourites",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Spacer(Modifier.height(8.dp))

                Button(
                    onClick = {},
                    Modifier.fillMaxWidth()
                        .height(50.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 10.dp
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "Add to Cart",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(Modifier.width(8.dp))

                    Text(
                        text = "Add to Cart",
                        fontSize = 20.sp
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = "Product Description: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(8.dp))

                Text(
                    text = product.description,
                    fontSize = 16.sp
                )

                if(product.otherDetails.isNotEmpty()){
                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Other Product Details: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(Modifier.height(12.dp))

                product.otherDetails.forEach { (key, value) ->
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "$key: ",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )

                        Text(
                            text = value,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }

            }
        }
    }
}