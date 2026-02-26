package com.example.shopnest.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.shopnest.model.OrderModel
import com.google.firebase.Firebase
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.UUID


/**
 * @author EMRAN AHMED
 */

object Utils {
    fun showText(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun addToCart(context: Context, productId: String) {

        val userDocs = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)

        userDocs.get().addOnCompleteListener { userInfo ->
            val currentCart = userInfo.result.get("cartItems") as? Map<String, Long> ?: emptyMap()
            val currentQuantity = currentCart[productId] ?: 0
            val updatedQuantity = currentQuantity + 1

            val updatedCart = mapOf("cartItems.$productId" to updatedQuantity)

            userDocs.update(updatedCart)
                .addOnCompleteListener { cart ->
                    if (cart.isSuccessful) {
                        showText(context, "Product added to cart")
                    } else {
                        showText(context, "Failed to add product to cart")
                    }
                }
        }
    }

    fun removeFromCart(context: Context, productId: String, removeAll: Boolean = false) {

        val userDocs = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)

        userDocs.get().addOnCompleteListener { userInfo ->
            val currentCart = userInfo.result.get("cartItems") as? Map<String, Long> ?: emptyMap()
            val currentQuantity = currentCart[productId] ?: 0
            val updatedQuantity = currentQuantity - 1

            val updatedCart =
                if (updatedQuantity <= 0 || removeAll)
                    mapOf("cartItems.$productId" to FieldValue.delete())
                else
                    mapOf("cartItems.$productId" to updatedQuantity)

            userDocs.update(updatedCart)
                .addOnCompleteListener { cart ->
                    if (cart.isSuccessful) {
                        showText(context, "Product removed from cart")
                    } else {
                        showText(context, "Failed to remove product from cart")
                    }
                }
        }
    }

    fun clearCartAddToOrder() {
        val userDocs = Firebase.firestore.collection("users")
            .document(FirebaseAuth.getInstance().currentUser?.uid!!)

        userDocs
            .get()
            .addOnCompleteListener { userInfo ->
                val currentCart =
                    userInfo.result.get("cartItems") as? Map<String, Long> ?: emptyMap()

                val order = OrderModel(
                    id = "ORD-" + UUID.randomUUID().toString().replace("-", "").take(10)
                        .uppercase(),
                    userId = FirebaseAuth.getInstance().currentUser?.uid!!,
                    time = Timestamp.now(),
                    item = currentCart,
                    address = userInfo.result.get("address") as? String ?: "",
                    status = "ORDERED"
                )

                Firebase.firestore.collection("orders")
                    .document(order.id)
                    .set(order)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            userDocs.update("cartItems", FieldValue.delete())
                        }
                    }

            }
    }

    @Composable
    fun Spacer(height: Int) {
        Spacer(Modifier.height(height.dp))
    }

    @Composable
    fun Divider() {
        Spacer(16)
        HorizontalDivider()
        Spacer(16)
    }

    fun getDiscountPerc(): Float {
        return 10f
    }

    fun getTaxPerc(): Float {
        return 5f
    }

    fun formateDate(time: Timestamp): String {
        val sdf = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
        return sdf.format(time.toDate().time)
    }


    private const val PREF_NAME = "favourite_pref"
    private const val KEY_FAVOURITE = "favourite_key"

    fun addOrRemoveFavourite(productId: String, context: Context) {
        val list = getFavourite(context).toMutableSet()

        if(list.contains(productId)){
            list.remove(productId)
            showText(context, "Removed from favourite")
        }

        else{
            list.add(productId)
            showText(context, "Added to favourite")
        }

        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

        pref.edit {
            putStringSet(KEY_FAVOURITE, list)
        }
    }

    fun checkFavourite(productId: String, context: Context): Boolean {
        return getFavourite(context).contains(productId)
    }

    fun getFavourite(context: Context): Set<String> {
        val pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return pref.getStringSet(KEY_FAVOURITE, emptySet()) ?: emptySet()
    }
}
