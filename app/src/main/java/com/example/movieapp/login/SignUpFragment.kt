package com.example.movieapp.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.movieapp.R
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        signUpSignUpButton.setOnClickListener {
            if (isNameValid() && isPasswordValid()) {
                // show success toast
                // navigateUp()
            } else {
                Toast.makeText(context, getString(R.string.please_fill_all_the_fields), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isNameValid(): Boolean {
        return signUpUsernameEditText.text.trim().isNotBlank()
    }

    private fun isPasswordValid(): Boolean {
        return signUpPasswordEditText.text.trim().isNotBlank()
    }
}
