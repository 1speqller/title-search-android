package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.myapplication.ui.screen.MainScreen
import shared.data.datasource.RemoteDataSourceImpl
import shared.data.repository.TvMazeRepositoryImpl
import shared.domain.interactor.SearchShowInteractor
import shared.presentation.viewmodel.TvMazeViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataSource = RemoteDataSourceImpl()
        val repository = TvMazeRepositoryImpl(dataSource)
        val interactor = SearchShowInteractor(repository)
        val viewModel = TvMazeViewModel(interactor)

        setContent {
            MainScreen(viewModel)
        }
    }
}