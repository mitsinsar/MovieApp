package com.example.movieapp.ui.main.movielist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.base.ViewModelFactory
import com.example.movieapp.data.model.Movie
import com.example.movieapp.data.network.RetrofitClient
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.ui.main.movielist.MovieListFragmentDirections.actionMovieListFragmentToDetailFragment
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : BaseFragment() {

    private val movieRepository by lazy { MovieRepository(RetrofitClient.getMovieApi()) }
    private val movieAdapter by lazy { MovieAdapter(::onItemClick) }
    private val movieListViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(movieRepository)).get(MovieViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        initObserver()

        movieListViewModel.getPopularMovies()
    }

    private fun initUi() {
        movieListMainRecyclerView.apply {
            adapter = movieAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun initObserver() {
        movieListViewModel.getPopularMoviesResult.observe(viewLifecycleOwner, Observer {
            movieAdapter.setItems(it)
        })
    }

    private fun onItemClick(movie: Movie) {
        findNavController().navigate(actionMovieListFragmentToDetailFragment(movie.id.toString()))
    }
}
