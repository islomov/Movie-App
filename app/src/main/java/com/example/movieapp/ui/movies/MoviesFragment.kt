package com.example.movieapp.ui.movies

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState

import com.example.movieapp.common.BaseFragment
import com.example.movieapp.common.getAppropriateMessage
import com.example.movieapp.common.gone
import com.example.movieapp.common.visible
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.ui.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class MoviesFragment: BaseFragment<FragmentMoviesBinding>() {

    private val viewModel: MoviesViewModel by viewModel()
    private var adapter: MoviesAdapter? = null
    private var act: MainActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        act = context as? MainActivity
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoviesBinding
        get() = FragmentMoviesBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setUpPaging()
        setupUI()
        observeEvents()
    }

    private fun setupUI() {
        adapter = MoviesAdapter {
            act?.navigateToDetail(it)
        }
        binding?.recycler?.adapter = adapter?.withLoadStateFooter(
            footer = MoviesAdapterLoadingState { adapter?.retry() }
        )
    }

    private fun observeEvents() {
        lifecycleScope.launch {
            viewModel.moviesFlow.collect {
                adapter?.submitData(it)
            }
        }
        lifecycleScope.launch {
            adapter?.loadStateFlow?.collectLatest { loadState->
                when(val state = loadState.refresh) {
                    is LoadState.Loading -> {
                        if(adapter?.itemCount == 0)
                            binding?.pbMain?.visible()
                        binding?.llErrorContainer?.gone()
                    }
                    is LoadState.NotLoading -> {
                        binding?.pbMain?.gone()
                    }
                    is LoadState.Error -> {
                        if(adapter?.itemCount == 0) {
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
}