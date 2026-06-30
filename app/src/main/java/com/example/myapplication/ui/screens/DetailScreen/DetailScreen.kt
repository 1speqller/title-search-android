package com.example.myapplication.ui.screens.DetailScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.templates.MovieText
import shared.domain.model.DetailMovieModel
import shared.presentation.viewmodel.detail.DetailScreenState
import shared.presentation.viewmodel.detail.DetailViewModel

@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    movieId: Int,
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    when(state) {
        is DetailScreenState.Error -> {
            Log.e("Error", (state as DetailScreenState.Error).message)
        }
        DetailScreenState.Loading -> {
            // nothing
        }
        DetailScreenState.Start -> {
            viewModel.onInit(movieId)
        }
        is DetailScreenState.Success -> {
            Screen(
                movie = (state as DetailScreenState.Success).movie,
                onBackPressed = onBackPressed,
            )
        }
    }

}

@Composable
private fun Screen(
    movie: DetailMovieModel,
    onBackPressed: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            IconButton(
                modifier = Modifier.padding(top = 8.dp),
                onClick = onBackPressed
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_backward_arrow),
                    contentDescription = null
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            AsyncImage(
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.Gray,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(12.dp))
                    .align(alignment = Alignment.CenterHorizontally),
                alignment = Alignment.Center,
                model = movie.image?.medium,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier.padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                MovieText(
                    modifier = Modifier.padding(end = 8.dp),
                    text = movie.name ?: "Без названия",
                    size = 32.sp
                )
                Row(
                    modifier = Modifier
                        .background(
                            color = Color.Unspecified,
                            shape = RoundedCornerShape(24.dp)
                        )
                        .border(
                            color = Color.LightGray,
                            width = 1.dp,
                            shape = RoundedCornerShape(24.dp)
                        ),
                ) {
                    MovieText(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(vertical = 4.dp, horizontal = 8.dp),
                        text = movie.status,
                        color = Color.Magenta
                    )
                }
            }
            movie.startAt?.let {
                Column() {
                    MovieText(
                        text = "Start at: " + movie.startAt,
                        color = Color.LightGray
                    )
                    movie.endAt?.let {
                        MovieText(
                            modifier = Modifier.padding(top = 4.dp),
                            text = "End at: " + movie.endAt,
                            color = Color.LightGray
                        )
                    }
                }
            }

            movie.description?.let {
                MovieText(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(vertical = 8.dp),
                    text = movie.description,
                    color = Color.Gray
                )
            }
        }
    }
}