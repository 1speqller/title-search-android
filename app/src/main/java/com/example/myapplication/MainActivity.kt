package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.screens.DetailScreen.DetailScreenActivity
import com.example.myapplication.ui.screens.MainScreen
import org.koin.androidx.viewmodel.ext.android.viewModel
import shared.data.datasource.RemoteDataSourceImpl
import shared.data.repository.MovieRepositoryImpl
import shared.domain.interactor.MovieInteractorImpl
import shared.presentation.viewmodel.main.MainScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: MainScreenViewModel by viewModel()

        setContent {
            MainScreen(
                viewModel,
                onMovieClick = { movieId ->
                    val intent = Intent(
                        this,
                        DetailScreenActivity::class.java
                    ).apply {
                        putExtra("movie_id", movieId)
                    }
                    startActivity(intent)
                }
            )
        }
    }
}