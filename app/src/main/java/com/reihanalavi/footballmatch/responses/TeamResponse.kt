package com.reihanalavi.footballmatch.responses

import com.google.gson.annotations.SerializedName
import com.reihanalavi.footballmatch.models.TeamItems

/**
 * Created by rehan on 6/4/2018.
 */
data class TeamResponse (
        @SerializedName("teams") val teamsItems: List<TeamItems>?
)