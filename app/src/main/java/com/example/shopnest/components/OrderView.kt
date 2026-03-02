package com.example.shopnest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopnest.model.OrderModel
import com.example.shopnest.utils.Utils

/**
 * @author EMRAN AHMED
 */

@Composable
fun OrderView(order: OrderModel ,navController: NavHostController, modifier: Modifier){

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp)
            .clickable{

            },
        elevation = CardDefaults.cardElevation(8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        ),
    ) {

        Column(
            Modifier.fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = order.id,
                Modifier.padding(2.dp),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = Utils.formateDate(order.time),
                Modifier.padding(2.dp),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.8f),
                fontSize = 16.sp
            )

            Text(
                text = order.status,
                Modifier.padding(2.dp),
                color = Color(0xFFFF5722),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "${order.item.size} items",
                Modifier.padding(4.dp),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}