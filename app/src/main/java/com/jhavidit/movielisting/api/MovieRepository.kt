package com.jhavidit.movielisting.api

import com.jhavidit.movielisting.NetworkResult
import com.jhavidit.movielisting.api.moviedetail.MovieDetailAPI
import com.jhavidit.movielisting.api.moviedetail.MovieDetailResponse
import com.jhavidit.movielisting.safeApiCall
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val movieListingAPI: MovieListingAPI,
    private val movieDetailAPI: MovieDetailAPI
) {

    suspend fun getMovieList(): NetworkResult<MovieListingResponse> {
        return safeApiCall {
            movieListingAPI.getPopularMovieList()
        }
    }

    suspend fun getMovieDetails(movieId: Int): NetworkResult<MovieDetailResponse> {
        return safeApiCall {
            movieDetailAPI.getMovieDetails(movieId)
        }
    }

}