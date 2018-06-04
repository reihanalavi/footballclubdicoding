package com.reihanalavi.footballmatch.repo

import com.reihanalavi.footballmatch.BuildConfig
import java.net.URL

/**
 * Created by rehan on 5/21/2018.
 */
class ApiRepo {

    fun doRequest(url: String): String {
        return URL(url).readText()
    }

    fun loadTeam(teamId: String?): String {
        return URL(BuildConfig.TEAM_URL + teamId).readText()
    }

    fun loadDetail(matchId: String?): String {
        return URL(BuildConfig.DETAIL_MATCH + matchId).readText()
    }

}