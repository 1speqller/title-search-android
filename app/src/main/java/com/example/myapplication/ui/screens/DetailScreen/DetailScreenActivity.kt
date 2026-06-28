package com.example.myapplication.ui.screens.DetailScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.androidx.viewmodel.ext.android.viewModel
import shared.data.datasource.RemoteDataSourceImpl
import shared.data.repository.MovieRepositoryImpl
import shared.domain.interactor.MovieInteractorImpl
import shared.presentation.viewmodel.detail.DetailViewModel

class DetailScreenActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel: DetailViewModel by viewModel()

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