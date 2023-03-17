package com.test.a3.utils

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.test.a3.R

fun setTheme(isDark: Boolean, context: Context, vararg textViews: TextView) {
    if (isDark) {
        textViews.forEach {
            it.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.app_green
                )
            )
        }
    }else{
        textViews.forEach {
            it.setTextColor(
                ContextCompat.getColor(
                    context,
                    R.color.white
                )
            )
        }
    }
}