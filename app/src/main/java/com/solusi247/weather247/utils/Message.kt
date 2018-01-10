package com.solusi247.weather247.utils

import android.content.Context
import android.widget.Toast

object Message {

    fun showToast(context: Context, message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, length).show()
    }

}