package com.example.shopnest.viewmodel

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import com.example.shopnest.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

/**
 * @author EMRAN AHMED
 */
class AuthViewModel: ViewModel() {
    val auth = FirebaseAuth.getInstance()
    val firestore = Firebase.firestore



    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    onResult(true, null)
                } else{
                    onResult(false, "Something is wrong")
                }
            }
    }

    fun signup(name: String, email: String, password: String, onResult: (Boolean, String?) -> Unit){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    val userId = it.result?.user?.uid

                    val userModel = UserModel(name, email, userId!!)

                    firestore.collection("users").document(userId)
                        .set(userModel)
                        .addOnCompleteListener { result ->
                            if(result.isSuccessful){
                                onResult(true, null)
                            } else{
                                onResult(false, "Something is wrong")
                            }
                        }

                } else{
                    onResult(false, it.exception?.localizedMessage)
                }
            }
    }

}