package com.example.shopnest.utils

import android.content.Context
import android.widget.Toast


/**
 * @author EMRAN AHMED
 */

object AppUtils{
    fun showText(context: Context, message: String){
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}
