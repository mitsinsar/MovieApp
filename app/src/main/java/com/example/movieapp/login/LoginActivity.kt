package com.example.movieapp.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.R
import com.example.movieapp.main.MainActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun getSharedPreferences() = getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)

    fun navigateToMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(mainActivityIntent)
        finish()
    }

    companion object {
        private const val sharedPrefName = "movie_app_shared_pref"
    }
}
