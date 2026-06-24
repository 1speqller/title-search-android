package com.example.myapplication.ui.screens.DetailScreen

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.myapplication.R
import com.example.myapplication.ui.templates.MovieText
import org.intellij.lang.annotations.JdkConstants
import shared.domain.model.DetailMovieModel
import shared.presentation.viewmodel.MainScreenState
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
                model = movie.image.medium,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            MovieText(
                modifier = Modifier.padding(top = 4.dp),
                text = movie.name ?: "Без названия",
                size = 16.sp
            )
        }
    }
}