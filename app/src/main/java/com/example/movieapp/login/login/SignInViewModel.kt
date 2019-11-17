package com.example.movieapp.login.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var getUserJob: Job? = null
    val isUserValidResult = MutableLiveData<Boolean>()

    fun isUserValid(username: String, password: String) {
        if (getUserJob?.isActive == true) {
            return
        }
        getUserJob = launchGetUser(username, password)
    }

    private fun launchGetUser(username: String, password: String): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            val user = userRepository.getUser()
            val isUserValid = user?.username == username && user.password == password
            isUserValidResult.postValue(isUserValid)
        }
    }
}
