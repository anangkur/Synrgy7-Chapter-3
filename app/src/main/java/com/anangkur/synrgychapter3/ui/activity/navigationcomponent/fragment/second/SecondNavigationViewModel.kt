package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anangkur.synrgychapter3.data.MovieLocalDataSource
import com.anangkur.synrgychapter3.data.MovieRemoteDataSource
import com.anangkur.synrgychapter3.data.MovieRepositoryImpl
import com.anangkur.synrgychapter3.data.local.MovieLocalDataSourceImpl
import com.anangkur.synrgychapter3.data.remote.MovieRemoteDataSourceImpl
import com.anangkur.synrgychapter3.domain.MovieRepository
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.data.Movie

class SecondNavigationViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val localDataSource: MovieLocalDataSource = MovieLocalDataSourceImpl()
                val remoteDataSource: MovieRemoteDataSource = MovieRemoteDataSourceImpl()
                val myRepository: MovieRepository = MovieRepositoryImpl(
                    remoteDataSource = remoteDataSource,
                    localDataSource = localDataSource,
                )
                SecondNavigationViewModel(repository = myRepository)
            }
        }
    }

    /**
     * Retrieves a list of sample movie data.
     *
     * This function returns a list of sample movie data, each containing an image URL, a title, and a description.
     * The sample movie data is provided for demonstration purposes and can be used to populate UI components such
     * as RecyclerViews with movie items.
     *
     * @return A list of Movie objects containing sample movie data.
     */
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = _movies
    fun retrieveMovieData() {
        // Create and return a list of sample movie data
        _movies.value = repository.fetchData()
    }
}