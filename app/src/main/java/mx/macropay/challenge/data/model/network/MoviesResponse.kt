package mx.macropay.challenge.data.model.network

import mx.macropay.challenge.data.model.entity.Movie
import com.google.gson.annotations.SerializedName

data class MoviesResponse(
    @SerializedName("page")
    override var page: Int,

    @SerializedName("results")
    override var results: List<Movie>
) : BasePageListResponse<Movie>
