package mx.macropay.challenge.data.model.network

import com.google.gson.annotations.SerializedName
import mx.macropay.challenge.data.model.entity.Cast

data class CreditsResponse(
    @SerializedName("cast")
    override var results: List<Cast>
) : BaseListResponse<Cast>
