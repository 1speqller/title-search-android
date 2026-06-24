package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.screens.DetailScreen.DetailScreenActivity
import com.example.myapplication.ui.screens.MainScreen
import shared.data.datasource.RemoteDataSourceImpl
import shared.data.repository.MovieRepositoryImpl
import shared.domain.interactor.MovieInteractor
import shared.presentation.viewmodel.MainScreenViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataSource = RemoteDataSourceImpl()
        val repository = MovieRepositoryImpl(dataSource)
        val interactor = MovieInteractor(repository)
        val viewModel = MainScreenViewModel(interactor)

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