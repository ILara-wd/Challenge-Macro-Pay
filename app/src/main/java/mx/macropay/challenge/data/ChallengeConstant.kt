package mx.macropay.challenge.data

object ChallengeConstant {

    const val BASE_URL = "https://api.themoviedb.org/"
    const val API_KEY_NAME = "api_key"
    const val API_KEY_VALUE = "c0823934438075d63f1dbda4023e76fc"

    const val API_VERSION: Int = 3
    const val BASE_POSTER_URL = "https://image.tmdb.org/t/p/w185"
    const val BASE_BACKDROP_URL = "https://image.tmdb.org/t/p/w780"
    const val MAX_RATING = 10f

    fun getBackdropUrl(path: String) = BASE_BACKDROP_URL + path
    fun getPosterUrl(path: String) = BASE_POSTER_URL + path

}