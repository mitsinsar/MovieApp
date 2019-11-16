package com.example.movieapp.login.signup

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.model.User
import com.example.movieapp.data.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var saveUserJob: Job? = null
    val saveUserResult = MutableLiveData<Boolean>()

    fun saveUser(user: User) {
        if (saveUserJob?.isActive == true) {
            return
        }
        saveUserJob = launchSaveUser(user)
    }

    private fun launchSaveUser(user: User): Job? {
        return viewModelScope.launch(Dispatchers.Main) {
            userRepository.saveUser(user)
            saveUserResult.postValue(true)
        }
    }
}
