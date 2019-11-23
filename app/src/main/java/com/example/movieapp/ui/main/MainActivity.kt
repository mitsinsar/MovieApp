package com.example.movieapp.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.R
import com.example.movieapp.base.BaseActivity

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupTopNav()
    }

    private fun setupTopNav() {
        navController = Navigation.findNavController(this, R.id.mainFragmentContainer)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    fun setActionBarTitle(title: String? = null, titleResId: Int? = null) {
        if (titleResId != null) {
            supportActionBar?.setTitle(titleResId)
        } else {
            supportActionBar?.title = title.orEmpty()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}
