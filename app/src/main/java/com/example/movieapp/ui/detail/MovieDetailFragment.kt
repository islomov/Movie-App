package com.example.movieapp.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.example.movieapp.common.*
import com.example.movieapp.databinding.FragmentMovieDetailBinding
import com.example.movieapp.ui.MainActivity
import com.example.movieapp.ui.model.MovieItemUI
import com.example.movieapp.ui.movies.MoviesAdapter
import com.example.movieapp.ui.movies.MoviesAdapterLoadingState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.ext.android.bind
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val viewModel: MovieDetailViewModel by viewModel()
    private var adapter: MoviesAdapter? = null
    private var movieItem: MovieItemUI? = null

    private var act: MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        act = context as? MainActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieItem = it.getParcelable(KEY_MOVIE_ITEM)
        }
    }
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDetailBinding
        get() = FragmentMovieDetailBinding::inflate


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUpSimilarMoviesPaging(movieItem?.id)
        setupUI()
        observeEvents()
    }

    private fun setupUI() {
        adapter = MoviesAdapter{
            act?.navigateToDetail(it)
        }
        binding?.recycler?.adapter = adapter?.withLoadStateFooter(
            footer = MoviesAdapterLoadingState { adapter?.retry() }
        )
        binding?.ivPoster?.loadGlideRoundedCorner(movieItem?.posterPath?.getPath())
        binding?.tvDesc?.text = movieItem?.overview
        binding?.tvTitle?.text = movieItem?.originalName
        binding?.tvRating?.text = "Rating:${movieItem?.voteAverage}"
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            viewModel.similarMoviesFlow.collect {
                adapter?.submitData(it)
            }
        }
        lifecycleScope.launch {
            adapter?.loadStateFlow?.collectLatest { loadState ->
                when (val state = loadState.refresh) {
                    is LoadState.Loading -> {
                        if (adapter?.itemCount == 0)
                            binding?.pbMain?.visible()
                        binding?.llErrorContainer?.gone()
                    }
                    is LoadState.NotLoading -> {
                        binding?.pbMain?.gone()
                    }
                    is LoadState.Error -> {
                        if (adapter?.itemCount == 0) {
                            binding?.pbMain?.gone()
                            binding?.llErrorContainer?.visible()
                            binding?.tvErrorMessageMain?.text = state.error.getAppropriateMessage()
                            binding?.btnRetryMain?.setOnClickListener {
                                adapter?.retry()
                            }
                        }
                    }
                }
            }
        }

    }

    companion object {

        const val KEY_MOVIE_ITEM = "movie_item"

        fun newInstance(movieItem: MovieItemUI?): MovieDetailFragment {
            val args = Bundle()
            args.putParcelable(KEY_MOVIE_ITEM, movieItem)
            val fragment = MovieDetailFragment()
            fragment.arguments = args
            return fragment
        }
    }


}