package com.example.movieapp.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.movieapp.R

class MainActivity : AppCompatActivity() {

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

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }

    fun setActionBarText(actionBarText: String? = null, actionBarTextResId: Int? = null) {
        if (actionBarTextResId != null) {
            supportActionBar?.setTitle(actionBarTextResId)
        } else {
            supportActionBar?.title = actionBarText.orEmpty()
        }
    }
}
