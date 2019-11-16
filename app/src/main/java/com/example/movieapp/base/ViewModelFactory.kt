package com.example.movieapp.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieapp.data.repository.BaseRepository
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.data.repository.UserRepository
import com.example.movieapp.login.login.LoginViewModel
import com.example.movieapp.login.signup.SignUpViewModel
import com.example.movieapp.main.detail.DetailViewModel
import com.example.movieapp.main.movielist.MovieViewModel

class ViewModelFactory(private val repository: BaseRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> MovieViewModel(repository as MovieRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository as MovieRepository) as T
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> SignUpViewModel(repository as UserRepository) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(repository as UserRepository) as T
            else -> throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
