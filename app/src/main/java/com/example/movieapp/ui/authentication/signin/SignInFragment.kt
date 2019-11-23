package com.example.movieapp.ui.authentication.signin

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
import com.example.movieapp.data.repository.UserRepository
import com.example.movieapp.extensions.textAsString
import com.example.movieapp.ui.authentication.AuthenticationActivity
import com.example.movieapp.ui.authentication.signin.SignInFragmentDirections.actionSignInFragmentToSignUpFragment
import kotlinx.android.synthetic.main.fragment_sign_in.signInButton
import kotlinx.android.synthetic.main.fragment_sign_in.signInPasswordEditText
import kotlinx.android.synthetic.main.fragment_sign_in.signInSignUpTextView
import kotlinx.android.synthetic.main.fragment_sign_in.signInUsernameEditText

class SignInFragment : BaseFragment() {

    private lateinit var signInViewModel: SignInViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
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
        signInButton.setOnClickListener {
            val username = signInUsernameEditText.textAsString
            val password = signInPasswordEditText.textAsString
            signInViewModel.isUserValid(username, password)
        }

        signInSignUpTextView.setOnClickListener {
            findNavController().navigate(actionSignInFragmentToSignUpFragment())
        }
    }

    private fun initObservers() {
        signInViewModel.isUserValidResult.observe(viewLifecycleOwner, Observer { isSuccess ->
            if (isSuccess) {
                (activity as? AuthenticationActivity)?.navigateToMainActivity()
            } else {
                Toast.makeText(context, getString(R.string.user_not_found), Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initViewModel() {
        val sharedPreferences = getBaseActivity().getSharedPreferences()
        val userRepository = UserRepository(sharedPreferences)
        signInViewModel = ViewModelProviders.of(this, ViewModelFactory(userRepository)).get(SignInViewModel::class.java)
    }
}
