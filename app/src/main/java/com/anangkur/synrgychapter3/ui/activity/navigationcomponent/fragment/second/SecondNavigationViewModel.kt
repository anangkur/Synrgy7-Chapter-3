package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.savedstate.SavedStateRegistryOwner
import com.anangkur.synrgychapter3.data.datasource.local.MovieLocalDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.local.room.RoomDatabase
import com.anangkur.synrgychapter3.data.datasource.remote.MovieRemoteDataSourceImpl
import com.anangkur.synrgychapter3.data.repository.MovieRepositoryImpl
import com.anangkur.synrgychapter3.domain.MovieRepository
import com.anangkur.synrgychapter3.ui.dataclass.Movie

class SecondNavigationViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle,
                ): T {
                    val roomDatabase = Room.databaseBuilder(
                        context = context,
                        name = RoomDatabase.NAME,
                        klass = RoomDatabase::class.java,
                    ).build()
                    val movieRepository: MovieRepository = MovieRepositoryImpl(
                        remoteDataSource = MovieRemoteDataSourceImpl(),
                        localDataSource = MovieLocalDataSourceImpl(
                            movieDao = roomDatabase.movieDao(),
                        ),
                    )
                    return SecondNavigationViewModel(
                        repository = movieRepository,
                    ) as T
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