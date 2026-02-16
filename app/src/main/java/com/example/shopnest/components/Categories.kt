package com.example.shopnest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.shopnest.model.CategoryModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */

@Composable
fun Categories(modifier: Modifier){
    var categoryList by remember {
        mutableStateOf<List<CategoryModel>>(emptyList())
    }

    LaunchedEffect(Unit) {
        Firebase.firestore.collection("data")
            .document("stock")
            .collection("categories")
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    // Converting the documents to CategoryModel objects
                    val result = it.result.documents.mapNotNull { docs ->
                        docs.toObject(CategoryModel::class.java)
                    }

                    categoryList = result
                }
            }
    }

    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    )  {
        items(categoryList){ item ->
            CategoryItem(item)
        }
    }
}

@Composable
fun CategoryItem(item: CategoryModel ){
    Column (
//        modifier = Modifier.padding(16.dp)
    ) {
        Card (modifier = Modifier.size(120.dp)
            .align(Alignment.CenterHorizontally)
            .clickable{
            },
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp,
                pressedElevation = 4.dp,
                hoveredElevation = 8.dp
            ),
            shape = RoundedCornerShape(12.dp)) {
            AsyncImage(
                model = item.imageUrl,
                contentDescription = item.name,
                modifier = Modifier.size(80.dp)
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(text = item.name, modifier = Modifier.padding(8.dp)
                .align(Alignment.CenterHorizontally),
                fontWeight = FontWeight.Bold)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CategoryPreview(){
    Categories(modifier = Modifier)
}