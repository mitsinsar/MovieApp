package com.example.movieapp.ui.main.movielist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.base.BaseViewModel
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {

    private var getPopularMoviesJob: Job? = null
    val getPopularMoviesResult = MutableLiveData<List<Movie>>()

    fun getPopularMovies() {
        if (getPopularMoviesJob?.isActive == true) {
            return
        }
        getPopularMoviesJob = launchGetPopularMovies()
    }

    private fun launchGetPopularMovies(): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            val result = movieRepository.getPopularMovies()
            getPopularMoviesResult.postValue(result)
        }
    }
}
