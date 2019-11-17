package com.example.movieapp.login.signup

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
import com.example.movieapp.data.model.User
import com.example.movieapp.data.repository.UserRepository
import com.example.movieapp.login.LoginActivity
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    lateinit var signUpViewModel: SignUpViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
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
        signUpButton.setOnClickListener {
            if (isNameValid() && isPasswordValid()) {
                saveUser()
            } else {
                Toast.makeText(context, getString(R.string.please_fill_all_the_fields), Toast.LENGTH_SHORT).show()
            }
        }
        signUpSignInTextView.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun initObservers() {
        signUpViewModel.saveUserResult.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                findNavController().navigateUp()
            }
        })
    }

    private fun initViewModel() {
        val sharedPreferences = (activity as LoginActivity).getSharedPreferences()
        val userRepository = UserRepository(sharedPreferences)
        signUpViewModel = ViewModelProviders.of(this, ViewModelFactory(userRepository)).get(SignUpViewModel::class.java)
    }

    private fun saveUser() {
        val username = signUpUsernameEditText.text.toString()
        val password = signUpPasswordEditText.text.toString()
        val user = User(username, password)
        signUpViewModel.saveUser(user)
    }

    private fun isNameValid(): Boolean {
        return signUpUsernameEditText.text.trim().isNotBlank()
    }

    private fun isPasswordValid(): Boolean {
        return signUpPasswordEditText.text.trim().isNotBlank()
    }
}
