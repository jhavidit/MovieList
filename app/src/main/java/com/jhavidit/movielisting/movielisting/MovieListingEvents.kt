package com.jhavidit.movielisting.movielisting

sealed class MovieListingEvents {
    data class ShowMessage(val text: String, ) : MovieListingEvents()
    data class Navigate(val route: String) : MovieListingEvents()
    data object NavigateUp : MovieListingEvents()
}