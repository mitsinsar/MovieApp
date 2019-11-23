package com.example.movieapp.ui.authentication.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.base.ViewModelFactory
import com.example.movieapp.data.model.User
import com.example.movieapp.data.repository.UserRepository
import com.example.movieapp.extensions.textAsString
import kotlinx.android.synthetic.main.fragment_sign_up.signUpButton
import kotlinx.android.synthetic.main.fragment_sign_up.signUpPasswordEditText
import kotlinx.android.synthetic.main.fragment_sign_up.signUpSignInTextView
import kotlinx.android.synthetic.main.fragment_sign_up.signUpUsernameEditText

class SignUpFragment : BaseFragment() {

    private lateinit var signUpViewModel: SignUpViewModel

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
                val username = signUpUsernameEditText.textAsString
                val password = signUpPasswordEditText.textAsString
                val user = User(username, password)
                signUpViewModel.saveUser(user)
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
        val sharedPreferences = getBaseActivity().getSharedPreferences()
        val userRepository = UserRepository(sharedPreferences)
        signUpViewModel = ViewModelProviders.of(this, ViewModelFactory(userRepository)).get(SignUpViewModel::class.java)
    }

    private fun isNameValid(): Boolean {
        return signUpUsernameEditText.text.trim().isNotBlank()
    }

    private fun isPasswordValid(): Boolean {
        return signUpPasswordEditText.text.trim().isNotBlank()
    }
}
