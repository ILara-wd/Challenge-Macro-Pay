package mx.macropay.challenge.data

import mx.macropay.challenge.data.database.dao.MovieDao
import mx.macropay.challenge.data.database.entity.MovieEntity
import mx.macropay.challenge.data.model.Provider
import mx.macropay.challenge.data.model.entity.Genre
import mx.macropay.challenge.data.model.entity.Movie
import mx.macropay.challenge.data.model.entity.toDomain
import mx.macropay.challenge.data.network.Service
import javax.inject.Inject

class Repository @Inject constructor(
    private val api: Service,
    private val provider: Provider,
    private val quoteDao: MovieDao
) {

    suspend fun doGetPopularMovie(page: Int?): List<Movie> {
        val response = api.getPopularMovie(page)
        provider.popularMovie = response
        return response
    }

    suspend fun doGetDiscoverMovieMovie(page: Int?): List<Movie> {
        val response = api.getDiscoverMovie(page)
        provider.discoverMovie = response
        return response
    }

    suspend fun doGetTopRatedMovieMovie(page: Int?): List<Movie> {
        val response = api.getTopRatedMovie(page)
        provider.topRatedMovie = response
        return response
    }

    suspend fun doGetAllGenreMovie(): List<Genre> {
        val response = api.getGetAllGenreMovie()
        provider.genreList = response
        return response
    }

    suspend fun getAllQuotesFromDatabase(): List<Movie> {
        val response: List<MovieEntity> = quoteDao.getAllMovie()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<MovieEntity>) {
        quoteDao.insertAllMovie(quotes)
    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllMovie()
    }

}
