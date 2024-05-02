package mx.macropay.challenge.domain

import mx.macropay.challenge.data.Repository
import javax.inject.Inject

class GenreMoviesUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke() = repository.doGetAllGenreMovie()
}