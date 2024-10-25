package com.jhavidit.movielisting.api.moviedetail


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailAPI {

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Int
    ): Response<MovieDetailResponse>

}