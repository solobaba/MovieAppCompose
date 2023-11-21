//package com.example.movieappcompose.viewmodel
//
//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.asLiveData
//import androidx.lifecycle.viewModelScope
//import com.example.movieappcompose.model.network.Constants
//import com.example.movieappcompose.model.network.movieApiService
//import com.example.movieappcompose.model.repository.MovieRepository
//import com.example.movieappcompose.model.response.DiscoverResult
//import com.example.movieappcompose.util.ApiResult
//import com.example.movieappcompose.util.SortBy
//import com.example.movieappcompose.util.buildRetrofit
//import kotlinx.coroutines.channels.Channel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.receiveAsFlow
//import kotlinx.coroutines.launch
//
//sealed class FetchAllMoviesState {
//    object Loading : FetchAllMoviesState()
//    class Error(val exception: String) : FetchAllMoviesState()
//    data class Success(val data: DiscoverResult?) : FetchAllMoviesState()
//}
//
//class MovieListViewModel(
//    app: Application,
//    private val movieRepository: MovieRepository = MovieRepository(
//        buildRetrofit(
//            Constants.BASE_URL
//            //NetworkStatusInterceptor(ConnectionManager(context))
//        ).movieApiService
//    )
//): AndroidViewModel(app) {
//
//    private val _fetchAllMoviesState =
//        Channel<FetchAllMoviesState>(Channel.BUFFERED)
//    val fetchAllMoviesState = _fetchAllMoviesState.receiveAsFlow()
//
//    private val _observeMoviesList = MutableStateFlow<DiscoverResult?>(null)
//    val observeMoviesList = _observeMoviesList.asLiveData()
//
//    fun fetchMovies(
//        sortBy: SortBy,
//        withGenres: String? = null,
//        page: Int
//    ) {
//        viewModelScope.launch {
//            _fetchAllMoviesState.send(FetchAllMoviesState.Loading)
//            when (val result =
//                movieRepository.fetchMoviesList(sortBy, withGenres, page)) {
//                is ApiResult.Success -> {
//                    _observeMoviesList.value = result.data
//                    _fetchAllMoviesState.send(FetchAllMoviesState.Success(result.data))
//                }
//
//                is ApiResult.Error -> {
//                    _fetchAllMoviesState.send(FetchAllMoviesState.Error(result.toString()))
//                }
//
//                is ApiResult.NetworkError -> {
//                    _fetchAllMoviesState.send(FetchAllMoviesState.Error(result.toString()))
//                }
//            }
//        }
//    }
//}
//
//class MovieListViewModelFactory(val app: Application) :
//    ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return MovieListViewModel(app) as T
//    }
//}