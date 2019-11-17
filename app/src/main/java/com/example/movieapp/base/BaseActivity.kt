package com.example.movieapp.base

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    fun getSharedPreferences(): SharedPreferences = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)

    companion object {
        private const val sharedPrefName = "movie_app_shared_pref"
    }
}
