package com.example.myapplication.ui.screens.DetailScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import shared.data.datasource.RemoteDataSourceImpl
import shared.data.repository.MovieRepositoryImpl
import shared.domain.interactor.MovieInteractor
import shared.presentation.viewmodel.detail.DetailViewModel

class DetailScreenActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataSource = RemoteDataSourceImpl()
        val repository = MovieRepositoryImpl(dataSource)
        val interactor = MovieInteractor(repository)
        val viewModel = DetailViewModel(interactor)

        val movieId = intent.getIntExtra("movie_id", 0)

        setContent {
            DetailScreen(
                viewModel,
                movieId,
                onBackPressed = {
                    finish()
                }
            )
        }
    }
}