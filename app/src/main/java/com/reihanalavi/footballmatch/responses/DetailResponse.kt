package com.reihanalavi.footballmatch.responses

import com.google.gson.annotations.SerializedName
import com.reihanalavi.footballmatch.models.DetailItems

/**
 * Created by rehan on 5/23/2018.
 */
data class DetailResponse(
        @SerializedName("events") val detailItems: List<DetailItems>?
)