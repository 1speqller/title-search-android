package com.example.myapplication.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import shared.presentation.viewmodel.TvMazeViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.templates.MovieCell
import com.example.myapplication.ui.templates.MovieInputText
import shared.domain.model.ShowModel
import shared.presentation.viewmodel.MainScreenState

@Composable
fun MainScreen(
    viewModel: TvMazeViewModel
) {
    val state by viewModel.state.collectAsState()
    val query = viewModel.query.collectAsState()

    Screen(
        state = state,
        onQueryChange = viewModel::onQueryChange,
        query = query
    )
}

@Composable
fun Screen(
    state: MainScreenState,
    onQueryChange: (String) -> Unit,
    query: State<String?>
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            MovieInputText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                value = query.value,
                onChange = {
                    onQueryChange(it)
                },
                placeholder = "Поиск",

                )
            when(state) {
                is MainScreenState.Error -> {
                    // nothing
                }
                MainScreenState.Loading -> {
                    // nothing
                }
                MainScreenState.Start -> {
                    // nothing
                }
                is MainScreenState.Success -> MovieGrid(state.shows)
            }

        }
    }
}

@Composable
private fun MovieGrid(
    movies: List<ShowModel>
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        columns = GridCells.Fixed(2)
    ) {
        items(movies) { movie ->
            MovieCell(
                title = movie.name,
                genres = movie.genres,
                photoPath = movie.image?.medium
            )
        }
    }
}