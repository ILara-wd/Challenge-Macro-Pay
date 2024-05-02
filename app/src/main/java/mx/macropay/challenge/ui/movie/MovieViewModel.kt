package mx.macropay.challenge.ui.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import mx.macropay.challenge.data.model.entity.Genre
import mx.macropay.challenge.data.model.entity.Movie
import mx.macropay.challenge.domain.DiscoverMovieUseCase
import mx.macropay.challenge.domain.GenreMoviesUseCase
import mx.macropay.challenge.domain.PopularMovieUseCase
import mx.macropay.challenge.domain.TopRatedMovieUseCase
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    val genreMoviesUseCase: GenreMoviesUseCase,
    val popularMovieUseCase: PopularMovieUseCase,
    val topRatedMovieUseCase: TopRatedMovieUseCase,
    val discoveryMovieUseCase: DiscoverMovieUseCase
) : ViewModel() {

    private val page = MutableLiveData<Int>().apply { value = 1 }
    val isLoading = MutableLiveData<Boolean>()
    val message = MutableLiveData<String>()
    val firstPopularMovie: MutableLiveData<Movie> = MutableLiveData()
    val popularMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val topRatedMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val discoveryMovie: MutableLiveData<List<Movie>> = MutableLiveData()
    val genreByPopularMovie: MutableLiveData<List<Genre>> = MutableLiveData()

    init {
        viewModelScope.launch {
            val responseTopRated = topRatedMovieUseCase(page.value)
            if (responseTopRated.isNotEmpty()) {
                topRatedMovie.postValue(responseTopRated)
            } else {
                message.postValue("error generic")
            }

            val responseDiscovery = discoveryMovieUseCase(page.value)
            if (responseDiscovery.isNotEmpty()) {
                discoveryMovie.postValue(responseDiscovery)
            } else {
                message.postValue("error generic")
            }
        }
    }


    fun getGenreByPopularMovie() {
        viewModelScope.launch {
            val response = genreMoviesUseCase()
            if (response.isNotEmpty()) {
                val genres: MutableList<Genre> = mutableListOf()
                firstPopularMovie.value?.genreIds.orEmpty().forEach { idGenre ->
                    genres.addAll(response.filter { it.id == idGenre })
                }
                genreByPopularMovie.postValue(genres)
            } else {
                message.postValue("error generic")
            }
        }
    }

    fun getPopularMovie() {
        viewModelScope.launch {
            val response = popularMovieUseCase(page.value)
            isLoading.postValue(true)
            if (response.isNotEmpty()) {
                popularMovie.postValue(response)
                firstPopularMovie.postValue(response.first())
            } else {
                message.postValue("error generic")
            }
            isLoading.postValue(false)
        }
    }

}