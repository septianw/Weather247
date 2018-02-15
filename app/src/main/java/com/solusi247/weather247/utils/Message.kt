package com.solusi247.weather247.utils

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.widget.Toast
import com.solusi247.weather247.R
import com.solusi247.weather247.Weather247
import kotlinx.android.synthetic.main.custom_toast.view.*

object Message {

    var toast = Toast(Weather247.context)

    enum class Type { INFORMATION, ERROR }

    fun showToast(context: Context, message: String, type: Message.Type, length: Int = Toast.LENGTH_SHORT) {
        dissmissToast()
        toast = Toast(context)
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.custom_toast, null)
        when (type) {
            Type.INFORMATION -> {
                view.ivIcon.setImageResource(R.drawable.ic_info)
                view.customToast.background = ContextCompat.getDrawable(context, R.drawable.custom_toast_info_background)
                view.tvMessage.setTextColor(ContextCompat.getColor(context, R.color.messageInfo))
            }
            Type.ERROR -> {
                view.ivIcon.setImageResource(R.drawable.ic_error)
                view.customToast.background = ContextCompat.getDrawable(context, R.drawable.custom_toast_error_background)
                view.tvMessage.setTextColor(ContextCompat.getColor(context, R.color.messageError))
            }
        }
        view.tvMessage.text = message
        view.tvMessage.text = message
        toast.view = view
        toast.duration = length
        toast.show()
    }

    fun dissmissToast() = toast.cancel()

}