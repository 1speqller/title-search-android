package shared.di

import com.example.myapplication.ui.screens.DetailScreen.DetailScreen
import org.koin.dsl.module
import shared.data.datasource.RemoteDataSource
import shared.data.datasource.RemoteDataSourceImpl
import shared.data.repository.MovieRepositoryImpl
import shared.domain.interactor.MovieInteractorImpl
import shared.domain.repository.MovieRepository
import shared.presentation.viewmodel.detail.DetailViewModel
import shared.presentation.viewmodel.main.MainScreenViewModel

val DI = module {

    // DataSource
    single<RemoteDataSource> { RemoteDataSourceImpl() }

    // Repository
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    // Interactor
    single { MovieInteractorImpl(get()) }

    // ViewModel
    factory { MainScreenViewModel(get()) }
    factory { DetailViewModel(get()) }
}