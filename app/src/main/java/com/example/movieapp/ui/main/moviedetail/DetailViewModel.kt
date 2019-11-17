package com.example.movieapp.ui.main.moviedetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.base.BaseViewModel
import com.example.movieapp.data.model.MovieDetail
import com.example.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailViewModel(private val movieRepository: MovieRepository) : BaseViewModel() {

    private var getMovieDetailJob: Job? = null
    val getMovieDetailResult = MutableLiveData<MovieDetail?>()

    fun getMovieDetail(movieId: String) {
        if (getMovieDetailJob?.isActive == true) {
            return
        }
        getMovieDetailJob = launchGetMovieDetail(movieId)
    }

    private fun launchGetMovieDetail(movieId: String): Job? {
        return viewModelScope.launch(Dispatchers.IO) {
            val movieDetail = movieRepository.getMovieDetail(movieId)
            getMovieDetailResult.postValue(movieDetail)
        }
    }
}
