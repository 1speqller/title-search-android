package com.example.myapplication.ui.screens.DetailScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.templates.MovieText
import com.example.myapplication.ui.templates.RepeatErrorBox
import shared.domain.model.DetailMovieModel
import shared.presentation.viewmodel.detail.DetailScreenState
import shared.presentation.viewmodel.detail.DetailViewModel
import java.util.Locale
import java.util.Locale.getDefault

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewModel: DetailViewModel,
    movieId: Int,
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text("Детали") },
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
                        Icon(
                            painter = painterResource(R.drawable.ic_backward_arrow),
                            contentDescription = "Назад"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            when(state) {
                is DetailScreenState.Error -> {
                    Log.e("Error", (state as DetailScreenState.Error).message)
                    RepeatErrorBox(
                        onClick = {
                            viewModel.onInit(movieId)
                        }
                    )
                }
                DetailScreenState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                DetailScreenState.Start -> {
                    viewModel.onInit(movieId)
                }
                is DetailScreenState.Success -> {
                    SuccessScreen((state as DetailScreenState.Success).movie)
                }
            }
        }
    }
}

@Composable
private fun SuccessScreen(
    movie: DetailMovieModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
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
                    color = if(movie.status == "Ended") {
                        Color(0xFFD235D2)
                    } else {
                        Color(0xFFE9FB00)
                    }
                )
            }
        }
        movie.startAt?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column() {
                    MovieText(
                        text = "Start at: ",
                        color = Color.Gray
                    )
                    MovieText(
                        modifier = Modifier.padding(top = 2.dp),
                        text = movie.startAt,
                        color = Color.LightGray
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    movie.endAt?.let {
                        MovieText(
                            text = "End at: ",
                            color = Color.Gray
                        )
                        MovieText(
                            modifier = Modifier.padding(top = 2.dp),
                            text = movie.endAt,
                            color = Color.LightGray
                        )
                    }
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