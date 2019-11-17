package com.example.movieapp.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun getBaseActivity() = activity as BaseActivity
}
