package com.reihanalavi.footballmatch.models

import com.google.gson.annotations.SerializedName

/**
 * Created by rehan on 6/4/2018.
 */
data class TeamItems (

        //Team Details
        @SerializedName("strTeamBadge") val teamBadge: String? = null,
        @SerializedName("strManager") val teamManager: String? = null

)