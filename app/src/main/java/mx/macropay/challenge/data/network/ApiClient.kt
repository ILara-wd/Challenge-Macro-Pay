package mx.macropay.challenge.data.network

import mx.macropay.challenge.data.ChallengeConstant
import mx.macropay.challenge.data.model.network.MoviesResponse
import mx.macropay.challenge.data.model.network.GenresResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiClient {

    @GET("/${ChallengeConstant.API_VERSION}/genre/movie/list")
    fun movieServiceFetchMovieGenreList(): Call<GenresResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/now_playing")
    fun movieServiceFetchPopularList(
        @Query("page") page: Int?
    ): Call<MoviesResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/top_rated")
    fun movieServiceFetchTopRatedList(
        @Query("page") page: Int?
    ): Call<MoviesResponse>

    @GET("/${ChallengeConstant.API_VERSION}/movie/upcoming")
    fun movieServiceFetchDiscoverList(
        @Query("page") page: Int?
    ): Call<MoviesResponse>

}
