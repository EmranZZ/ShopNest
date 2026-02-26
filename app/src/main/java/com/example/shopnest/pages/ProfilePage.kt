package com.example.shopnest.pages

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
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
fun ProfilePage(navController: NavHostController, modifier: Modifier = Modifier){

    var user by remember {
        mutableStateOf(UserModel())
    }

    var name by remember {
        mutableStateOf(user.name)
    }

    var email by remember {
        mutableStateOf(user.email)
    }

    var address by remember {
        mutableStateOf(user.address)
    }

    val context = LocalContext.current

    Firebase.firestore.collection("users")
        .document(FirebaseAuth.getInstance().currentUser?.uid!!)
        .get()
        .addOnCompleteListener {
            if(it.isSuccessful){
                val result = it.result.toObject(UserModel::class.java)
                if (result != null){
                    user = result

                    name = result.name
                    email = result.email
                    address = result.address
                }
            }
        }

    Column (
        modifier = modifier.fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Profile",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Box(
            modifier = Modifier.size(100.dp).clip(RoundedCornerShape(12.dp))
                .border(width = 1.dp, shape = CircleShape, color = MaterialTheme.colorScheme.onSurface)
                .align(alignment = Alignment.CenterHorizontally)
        ){

            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = null,
                Modifier.fillMaxWidth().size(90.dp)
            )
        }
        Utils.Spacer(16)

        // Name
        Text("Name: ")

        Utils.Spacer(8)

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = {
                name = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {

                if(name.isNotEmpty()){
                    Firebase.firestore.collection("users")
                        .document(FirebaseAuth.getInstance().currentUser?.uid!!)
                        .update("name", name)
                        .addOnCompleteListener {
                            Utils.showText(context, "Name Updated Successfully")
                        }
                } else{
                    Utils.showText(context, "Empty")
                }
            })
        )

        Utils.Spacer(16)
        Utils.Divider()

        // Address

        Text("Address: ")

        Utils.Spacer(8)

        TextField(
            value = address,
            onValueChange = {
                address = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions {
                Firebase.firestore.collection("users")
                    .document(FirebaseAuth.getInstance().currentUser?.uid!!)
                    .update("address", address)
            },
            modifier = Modifier.fillMaxWidth()
        )

        Utils.Spacer(16)
        Utils.Divider()

        // Email

        Text("Email: ")

        Utils.Spacer(8)

        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions {
                Firebase.firestore.collection("users")
                    .document(FirebaseAuth.getInstance().currentUser?.uid!!)
                    .update("email", email)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Utils.Spacer(16)
        Utils.Divider()

        // Total Product

        Text("Total Product: ")

        Utils.Spacer(8)

        Text(
            user.cartItems.size.toString(),
            fontSize = 20.sp
        )

        Utils.Divider()

        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {

            Text(
                text = "View Order",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
                    .clickable{
                        navController.navigate(Screen.Order.route)
                    }
            )

            IconButton(onClick = {
                navController.popBackStack()
                navController.navigate(Screen.Auth.route)
            }) {
                Row {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Sign Out",
                        )
                }
            }
        }
    }
}