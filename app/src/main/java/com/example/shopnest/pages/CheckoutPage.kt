package com.example.shopnest.pages


import android.app.AlertDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.shopnest.R
import com.example.shopnest.model.ProductModel
import com.example.shopnest.model.UserModel
import com.example.shopnest.navigation.Screen
import com.example.shopnest.utils.Utils
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

    val context = LocalContext.current

    fun calculate(){
        productList.forEach {
            val price = it.price.toFloat()
            val quantity = user.cartItems[it.id] ?: 0L

            subTotal += price * quantity

            discount = subTotal * (Utils.getDiscountPerc())/100

            tax = subTotal * (Utils.getTaxPerc())/100

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


    Column(
        modifier.padding(16.dp)
    ) {
        Text("Checkout"
            , fontSize = 22.sp
            , fontWeight = FontWeight.Bold
        )

        Utils.Spacer(8)
        HorizontalDivider()

        DeliverAdd(user.name, user.address)

        Utils.Spacer(8)

        HorizontalDivider()
        Utils.Spacer(8)

        RowItemCheckout("Subtotal:", subTotal.toString())
        RowItemCheckout("Discount(-):", discount.toString())
        RowItemCheckout("Tax(+):", tax.toString())

        HorizontalDivider()
        Utils.Spacer(48)

        Column(Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "Let's Pay for:"
                , fontSize = 18.sp
                , fontWeight = FontWeight.Bold)

            Utils.Spacer(8)

            Text(
                "$${total.toString()}"
            , fontSize = 24.sp
            , fontWeight = FontWeight.ExtraBold)

            Utils.Spacer(32)

            Button(
                onClick = {
                    Utils.clearCartAddToOrder()

                    val alertDialog = AlertDialog.Builder(context, R.style.CustomAlertDialog)
                        .setTitle("Payment Confirmation")
                        .setMessage("Are you sure you want to proceed?")
                        .setPositiveButton("Confirm"){ _, _ ->
                            navController.popBackStack()
                            navController.navigate(Screen.Home.route)
                        }
                        .setNegativeButton("Cancel"){ dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()

                },
                Modifier.height(50.dp).fillMaxWidth()
            ) {
                Text(
                    text = "PAYMENT",
                    fontSize = 20.sp
                )
            }
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
    Utils.Spacer(8)
}

@Composable
fun DeliverAdd(userName: String, userAddress: String){
    Column {
        Utils.Spacer(4)

        Text("Deliver to:"
            , fontSize = 18.sp
            , fontWeight = FontWeight.Bold
        )

        Utils.Spacer(4)
        Text(userName)
        Utils.Spacer(4)
        Text(userAddress)
    }
}