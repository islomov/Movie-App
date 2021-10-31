package com.example.movieapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.data.mapper.toDataModel
import com.example.movieapp.data.model.MovieItemData
import com.example.movieapp.data.network.ApiService

class MoviesPagingSource constructor(private val apiService: ApiService) :
    PagingSource<Int, MovieItemData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItemData> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = apiService.getMovies(page = nextPageNumber)
            return LoadResult.Page(
                data = response.results.map { it.toDataModel() },
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber.plus(1)
            )
        } catch (e: Exception) {
            return LoadResult.Error(Throwable(e))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieItemData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

}