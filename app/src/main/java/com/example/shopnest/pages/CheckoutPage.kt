package com.example.shopnest.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopnest.model.ProductModel
import com.example.shopnest.model.UserModel
import com.example.shopnest.utils.AppUtils
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */

@Composable
fun CheckoutPage(navController: NavHostController, modifier: Modifier){
    var user by remember {
        mutableStateOf(UserModel())
    }

    var productList by remember{
        mutableStateOf<List<ProductModel>>(emptyList())
    }

    var subTotal by remember {
        mutableFloatStateOf(0f)
    }

    var discount by remember {
        mutableFloatStateOf(0f)
    }

    var tax by remember {
        mutableFloatStateOf(0f)
    }

    var total by remember {
        mutableFloatStateOf(0f)
    }

    fun calculate(){
        productList.forEach {
            val price = it.price.toFloat()
            val quantity = user.cartItems[it.id] ?: 0L

            subTotal += price * quantity

            discount = subTotal * (AppUtils.getDiscountPerc())/100

            tax = subTotal * (AppUtils.getTaxPerc())/100

            total = subTotal - discount + tax
        }
    }

    LaunchedEffect(Unit) {

        Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)
            .get()
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val result = it.result.toObject(UserModel::class.java)

                    if(result != null){
                        user = result

                        Firebase.firestore.collection("data")
                            .document("stock")
                            .collection("products")
                            .whereIn("id", user.cartItems.keys.toList())
                            .get()
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    val productModel = task.result.toObjects(ProductModel::class.java)

                                    productList = productModel

                                    calculate()
                                }
                            }
                    }
                }
            }
    }


    Column(modifier.padding(16.dp)) {
        Text("Checkout"
            , fontSize = 22.sp
            , fontWeight = FontWeight.Bold
        )
        AppUtils.Spacer(8)
        HorizontalDivider()

        DeliverAdd(user.name, user.address)

        AppUtils.Spacer(8)

        HorizontalDivider()
        AppUtils.Spacer(8)

        RowItemCheckout("Subtotal:", subTotal.toString())
        RowItemCheckout("Discount(-):", discount.toString())
        RowItemCheckout("Tax(+):", tax.toString())

        HorizontalDivider()
        AppUtils.Spacer(8)
        Column(Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Let's Pay for:"
                , fontSize = 18.sp
                , fontWeight = FontWeight.Bold)

            AppUtils.Spacer(8)

            Text(
                "$${total.toString()}"
            , fontSize = 24.sp
            , fontWeight = FontWeight.ExtraBold)

        }
    }
}

@Composable
fun RowItemCheckout(title: String, price: String){
    Row(
        Modifier.fillMaxWidth().padding(8.dp)
        , horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(title
            , fontSize = 18.sp
            , fontWeight = FontWeight.Bold
        )

        Text("$$price"
            , fontSize = 16.sp
        )
    }
    AppUtils.Spacer(8)
}

@Composable
fun DeliverAdd(userName: String, userAddress: String){
    Column {
        AppUtils.Spacer(4)

        Text("Deliver to:"
            , fontSize = 18.sp
            , fontWeight = FontWeight.Bold
        )

        AppUtils.Spacer(4)
        Text(userName)
        AppUtils.Spacer(4)
        Text(userAddress)
    }
}