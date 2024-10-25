package com.jhavidit.movielisting

import kotlinx.serialization.Serializable


sealed class NavComponents {
    @Serializable
    data object MovieListingScreen : NavComponents()

    @Serializable
    data class MovieDetailScreen(val id: Int) : NavComponents()
}