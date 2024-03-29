package com.example.movieapp.ui.main.moviedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.base.BaseFragment
import com.example.movieapp.base.ViewModelFactory
import com.example.movieapp.data.model.MovieDetail
import com.example.movieapp.data.network.RetrofitClient
import com.example.movieapp.data.repository.MovieRepository
import com.example.movieapp.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseFragment() {

    private val movieRepository by lazy { MovieRepository(RetrofitClient.getMovieApi()) }
    private val detailViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory(movieRepository)).get(DetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getMovieDetail()
        initObserver()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as? MainActivity)?.setActionBarTitle("")
    }

    private fun getMovieDetail() {
        arguments?.run {
            val movieId = DetailFragmentArgs.fromBundle(this).movieId
            detailViewModel.getMovieDetail(movieId)
        }
    }

    private fun initObserver() {
        detailViewModel.getMovieDetailResult.observe(viewLifecycleOwner, Observer { movieDetail ->
            initUi(movieDetail)
        })
    }

    private fun initUi(movieDetail: MovieDetail?) {
        movieDetail?.run {
            Glide.with(detailBackgroundImageView).load(movieDetail.backdropPath).into(detailBackgroundImageView)
            Glide.with(detailPosterImageView).load(movieDetail.posterPath).into(detailPosterImageView)
            detailTitleTextView.text = title
            detailTaglineTextView.text = tagline
            detailOverviewTextView.text = overview
            detailReleaseDateTextView.text = releaseDate
            (activity as? MainActivity)?.setActionBarTitle(title)
        }
    }
}
