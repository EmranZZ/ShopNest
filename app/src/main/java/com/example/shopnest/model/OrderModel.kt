package com.example.shopnest.model

import com.google.firebase.Timestamp

/**
 * @author EMRAN AHMED
 */

data class OrderModel(
    val id: String = "",
    val userId: String = "",
    val time: Timestamp = Timestamp.now(),
    val item: Map<String, Long> = emptyMap(),
    val address: String = "",
    val status: String = ""
)