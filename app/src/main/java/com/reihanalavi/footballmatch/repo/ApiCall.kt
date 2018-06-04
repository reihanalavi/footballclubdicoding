package com.reihanalavi.footballmatch.repo

import com.reihanalavi.footballmatch.BuildConfig

/**
 * Created by rehan on 5/21/2018.
 */
object ApiCall {

    fun getNextMatch(): String {
        return BuildConfig.NEXT_MATCH
    }

    fun getPastMatch(): String {
        return BuildConfig.PAST_MATCH
    }

    fun getBadgeTeam(teamId: String?): String {
        return BuildConfig.TEAM_URL + teamId
    }

    fun getMatchDetails(matchId: String?): String {
        return BuildConfig.DETAIL_MATCH + matchId
    }

}