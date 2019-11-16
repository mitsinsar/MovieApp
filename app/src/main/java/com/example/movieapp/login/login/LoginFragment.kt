package com.example.movieapp.login.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.base.ViewModelFactory
import com.example.movieapp.data.repository.UserRepository
import com.example.movieapp.login.LoginActivity
import com.example.movieapp.login.login.LoginFragmentDirections.actionLoginFragmentToSignUpFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViewModel()
        initObservers()
    }

    private fun initUi() {
        loginLoginButton.setOnClickListener {
            val username = loginUsernameEditText.text.toString()
            val password = loginPasswordEditText.text.toString()
            loginViewModel.isUserValid(username, password)
        }

        loginSignUpTextView.setOnClickListener {
            findNavController().navigate(actionLoginFragmentToSignUpFragment())
        }
    }

    private fun initObservers() {
        loginViewModel.isUserValidResult.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                (activity as? LoginActivity)?.navigateToMainActivity()
            } else {
                Toast.makeText(context, getString(R.string.user_not_found), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initViewModel() {
        val sharedPreferences = (activity as LoginActivity).getSharedPreferences()
        val userRepository = UserRepository(sharedPreferences)
        loginViewModel = ViewModelProviders.of(this, ViewModelFactory(userRepository)).get(LoginViewModel::class.java)
    }
}
