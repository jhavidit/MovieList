package com.jhavidit.movielisting.movielisting

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MovieListing(onCardClick: (Int) -> Unit, viewModel: MovieListingViewModel = hiltViewModel()) {

    LaunchedEffect(Unit) {
        viewModel.getMovieList()

    }

    val context = LocalContext.current

    val movieList = viewModel.movieList.collectAsStateWithLifecycle()


    val state = viewModel.eventState.value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is MovieListingEvents.Navigate -> {

                }

                MovieListingEvents.NavigateUp -> {

                }

                is MovieListingEvents.ShowMessage -> {
                    Toast.makeText(context, event.text, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    val lazyListState = rememberLazyListState()

    Scaffold(
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier.padding(20.dp),
                    text = "Top Movies",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                LazyColumn(
                    state = lazyListState
                ) {
                    items(items = movieList.value, key = { movie ->
                        movie.id ?: ""
                    }) {
                        MovieDetailCard(
                            data = MovieDetailCardData(
                                id = it.id ?: -1,
                                image = "https://image.tmdb.org/t/p/w500" + (it.posterPath ?: ""),
                                title = it.title ?: "",
                                description = it.overview ?: "",
                                onClick = { id ->
                                    onCardClick.invoke(id)
                                },
                                rating = (it.voteAverage ?: 0).toString()
                            )
                        )
                    }
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

        }
    )


}


