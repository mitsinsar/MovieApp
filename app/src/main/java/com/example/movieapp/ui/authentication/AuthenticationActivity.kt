package com.example.movieapp.ui.authentication

import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.base.BaseActivity
import com.example.movieapp.ui.main.MainActivity

class AuthenticationActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)
    }

    fun navigateToMainActivity() {
        startActivity(MainActivity.newIntent(this))
        finish()
    }
}
