package com.example.movieapp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        loginLoginButton.setOnClickListener {
            if (hasUser()) {
                (activity as? LoginActivity)?.navigateToMainActivity()
            } else {
                Toast.makeText(context, getString(R.string.user_not_found), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hasUser(): Boolean {
        // get user from shared pref
        return false
    }
}
