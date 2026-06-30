package com.example.myapplication.ui.screens.MainScreen

import android.content.Intent
import androidx.compose.runtime.Composable
import com.example.myapplication.BaseActivity
import com.example.myapplication.ui.screens.DetailScreen.DetailScreenActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import shared.presentation.viewmodel.main.MainScreenViewModel

class MainActivity : BaseActivity() {

    @Composable
    override fun Content() {
        val viewModel: MainScreenViewModel by viewModel()

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