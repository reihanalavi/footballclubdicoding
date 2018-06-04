package com.reihanalavi.footballmatch.models

import com.google.gson.annotations.SerializedName

/**
 * Created by rehan on 5/23/2018.
 */
data class DetailItems (

        //Team Details
        @SerializedName("strTeamBadge") val teamBadge: String? = null,
        @SerializedName("strManager") val teamManager: String? = null,

        //Match Details HOME
        @SerializedName("strHomeGoalDetails") val homeGoal: String? = null,
        @SerializedName("strHomeRedCards") val homeRed: String? = null,
        @SerializedName("strHomeYellowCards") val homeYellow: String? = null,
        @SerializedName("strHomeLineupGoalkeeper") val homeGk: String? = null,
        @SerializedName("strHomeLineupDefense") val homeDef: String? = null,
        @SerializedName("strHomeLineupMidfield") val homeMid: String? = null,
        @SerializedName("strHomeLineupForward") val homeFor: String? = null,
        @SerializedName("strHomeLineupSubstitutes") val homeSub: String? = null,
        @SerializedName("strHomeFormation") val homeForm: String? = null,
        @SerializedName("intHomeShots") val homeShots: String? = null,

        //Match Details AWAY
        @SerializedName("strAwayGoalDetails") val awayGoal: String? = null,
        @SerializedName("strAwayRedCards") val awayRed: String? = null,
        @SerializedName("strAwayYellowCards") val awayYellow: String? = null,
        @SerializedName("strAwayLineupGoalkeeper") val awayGk: String? = null,
        @SerializedName("strAwayLineupDefense") val awayDef: String? = null,
        @SerializedName("strAwayLineupMidfield") val awayMid: String? = null,
        @SerializedName("strAwayLineupForward") val awayFor: String? = null,
        @SerializedName("strAwayLineupSubstitutes") val awaySub: String? = null,
        @SerializedName("strAwayFormation") val awayForm: String? = null,
        @SerializedName("intAwayShots") val awayShots: String? = null

)