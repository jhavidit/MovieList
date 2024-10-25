package com.jhavidit.movielisting.movielisting

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jhavidit.movielisting.NetworkResult
import com.jhavidit.movielisting.api.MovieRepository
import com.jhavidit.movielisting.api.MovieListingResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListingViewModel @Inject constructor(
    private val movieListingRepository: MovieRepository
) : ViewModel() {

    private val _movieList = MutableStateFlow<List<MovieListingResponse.Result>>(emptyList())
    val movieList: StateFlow<List<MovieListingResponse.Result>>
        get() = _movieList

    private val _eventFlow = MutableSharedFlow<MovieListingEvents>()
    val eventFlow = _eventFlow.asSharedFlow()

    private val _eventState = mutableStateOf(MovieListingState())
    val eventState: State<MovieListingState> = _eventState


    fun getMovieList() {
        viewModelScope.launch {
            _eventState.value = _eventState.value.copy(isLoading = true)
            val result = movieListingRepository.getMovieList()
            _eventState.value = _eventState.value.copy(isLoading = false)
            when (result) {
                is NetworkResult.Error -> {
                    _eventFlow.emit(
                        MovieListingEvents.ShowMessage(
                            result.message
                        )
                    )
                }

                is NetworkResult.Success -> {
                    result.data.results?.let {
                        _movieList.emit(it)
                    } ?: run {
                        _movieList.emit(emptyList())
                    }

                }
            }
        }
    }


}