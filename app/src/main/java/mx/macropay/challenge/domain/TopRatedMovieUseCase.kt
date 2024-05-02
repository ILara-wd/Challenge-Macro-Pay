package mx.macropay.challenge.domain

import mx.macropay.challenge.data.Repository
import javax.inject.Inject

class TopRatedMovieUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(page: Int?) = repository.doGetTopRatedMovieMovie(page)
}