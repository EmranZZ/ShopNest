package com.example.shopnest.pages

import android.content.Context
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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

    LaunchedEffect(Unit) {

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

        /** Name */
        CustomFieldTitle("Name")

        CustomTextField(
            value = name,
            onValueChange = {
                name = it
            },
            context = context,
            type = "name"
        )

        /** Address */
        CustomFieldTitle("Address:")

        CustomTextField(
            value = address,
            onValueChange = {
                address = it
            },
            context = context,
            type = "address"
        )

        /** Email */
        CustomFieldTitle("Email:")

        CustomTextField(
            value = email,
            onValueChange = {
                email = it
            },
            context = context,
            type = "email"
        )

        /** Total Product*/
        CustomFieldTitle("Total Product:")
        Utils.Spacer(8)

        Text(
            text = user.cartItems.size.toString(),
            fontSize = 20.sp,
            modifier = Modifier.padding(8.dp)
        )
        Utils.Divider()

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            TextButton(onClick = {
                navController.navigate(Screen.Order.route)
            }) {
                Text(
                    text = "View Order",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            TextButton(onClick = {
                navController.popBackStack()
                navController.navigate(Screen.Auth.route)
            }) {
                Row {
                    Text(
                        "Sign Out",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.padding(4.dp))

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = "Sign Out",
                        )
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    context: Context,
    type: String
){
    Utils.Spacer(8)

    TextField(
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(12.dp)),
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {

            if(value.isNotEmpty()){
                Firebase.firestore.collection("users")
                    .document(FirebaseAuth.getInstance().currentUser?.uid!!)
                    .update(type, value)
                    .addOnCompleteListener {
                        Utils.showText(context, "${type.replaceFirstChar { it.uppercase() }} Updated Successfully")
                    }
            } else{
                Utils.showText(context, "Empty ${type.replaceFirstChar { it.uppercase() }} Not Allowed")
            }
        }),
    )

    Utils.Spacer(16)
    Utils.Divider()
}

@Composable
fun CustomFieldTitle(string: String){
    Text(
        text = string,
        modifier = Modifier.padding(start = 4.dp)
    )
}