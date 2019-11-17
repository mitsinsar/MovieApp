package com.example.movieapp.extensions

import android.view.View
import android.widget.EditText

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

val EditText.textAsString: String
    get() = text.toString()
