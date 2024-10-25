package com.jhavidit.movielisting.api

import retrofit2.Response
import retrofit2.http.GET

interface MovieListingAPI {

    @GET("movie/popular")
    suspend fun getPopularMovieList(): Response<MovieListingResponse>

}