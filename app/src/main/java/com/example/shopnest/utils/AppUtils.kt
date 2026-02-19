package com.example.shopnest.utils

import android.content.Context
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore


/**
 * @author EMRAN AHMED
 */

object AppUtils{
    fun showText(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun addToCart(context: Context, productId: String){

        val userDocs = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)

        userDocs.get().addOnCompleteListener { userInfo->
            val currentCart = userInfo.result.get("cartItems") as? Map<String, Long> ?: emptyMap()
            val currentQuantity = currentCart[productId] ?: 0
            val updatedQuantity = currentQuantity + 1

            userDocs.update("cartItems.$productId", updatedQuantity)
                .addOnCompleteListener { cart->
                    if(cart.isSuccessful){
                        showText(context, "Product added to cart")
                    } else{
                        showText(context, "Failed to add product to cart")
                    }
                }
        }
    }
}
