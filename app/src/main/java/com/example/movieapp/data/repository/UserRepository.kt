package com.example.movieapp.data.repository

import android.content.SharedPreferences
import com.example.movieapp.data.model.User

class UserRepository(private val sharedPreferences: SharedPreferences) : BaseRepository() {

    fun saveUser(user: User) {
        with(getEditor()) {
            putString(USERNAME, user.username)
            putString(PASSWORD, user.password)
            apply()
        }
    }

    fun getUser(): User? {
        val username = sharedPreferences.getString(USERNAME, null)
        val password = sharedPreferences.getString(PASSWORD, null)
        return if (username != null && password != null) User(username, password) else null
    }

    private fun getEditor(): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    companion object {
        private const val USERNAME = "username"
        private const val PASSWORD = "password"
    }
}
