package com.jhavidit.movielisting.movielisting

data class MovieDetailCardData(
    val id: Int,
    val image: String,
    val title: String,
    val description: String,
    val rating: String,
    val onClick: (Int) -> Unit
)