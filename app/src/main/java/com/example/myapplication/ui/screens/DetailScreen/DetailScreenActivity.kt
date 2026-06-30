package com.example.myapplication.ui.screens.DetailScreen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.myapplication.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import shared.data.datasource.RemoteDataSourceImpl
import shared.data.repository.MovieRepositoryImpl
import shared.domain.interactor.MovieInteractorImpl
import shared.presentation.viewmodel.detail.DetailViewModel

class DetailScreenActivity: BaseActivity() {

    @Composable
    override fun Content() {
        val viewModel: DetailViewModel by viewModel()

        val movieId = intent.getIntExtra("movie_id", 0)

        DetailScreen(
            viewModel,
            movieId,
            onBackPressed = {
                finish()
            }
        )
    }
}