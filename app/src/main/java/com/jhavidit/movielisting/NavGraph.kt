package com.jhavidit.movielisting

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.jhavidit.movielisting.movielisting.MovieListing

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navHostController, startDestination = NavComponents.MovieListingScreen) {
        composable<NavComponents.MovieListingScreen> {
            MovieListing(onCardClick = { id ->
                navHostController.navigate(NavComponents.MovieDetailScreen(id = id))
            })
        }
        composable<NavComponents.MovieDetailScreen> {
            val movieDetailScreen: NavComponents.MovieDetailScreen = it.toRoute()
            MovieDetailScreen(id = movieDetailScreen.id)
        }
    }
}