package com.reihanalavi.footballmatch.responses

import com.google.gson.annotations.SerializedName
import com.reihanalavi.footballmatch.models.MatchItems

/**
 * Created by rehan on 5/21/2018.
 */
data class MatchResponse (
        @SerializedName("events") val matchItems: List<MatchItems>?)