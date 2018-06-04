package com.reihanalavi.footballmatch.views

import android.util.Log
import com.google.gson.Gson
import com.reihanalavi.footballmatch.models.DetailItems
import com.reihanalavi.footballmatch.repo.ApiCall
import com.reihanalavi.footballmatch.repo.ApiRepo
import com.reihanalavi.footballmatch.responses.DetailResponse
import com.reihanalavi.footballmatch.responses.MatchResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by rehan on 5/23/2018.
 */
class DetailPresenter (
        private val view: DetailView,
        private val apiRepo: ApiRepo,
        private val gson: Gson
) {

    fun loadDetailMatch(homeId: String?, awayId: String?, matchId: String?) {
        view.showLoading()

        doAsync {
            val reqHomeTeam = apiRepo.loadTeam(homeId)
            val reqAwayTeam = apiRepo.loadTeam(awayId)
            val reqDetail = apiRepo.loadDetail(matchId)
            val home = Gson().fromJson(reqHomeTeam, DetailResponse::class.java)
            val away = Gson().fromJson(reqAwayTeam, DetailResponse::class.java)
            val detail = Gson().fromJson(reqDetail, DetailResponse::class.java
            )
            uiThread {
                view.hideLoading()
                view.showDetail(detail?.detailItems, home?.detailItems, away?.detailItems)
                Log.i("HeyHeyHey : ", reqDetail)
            }

        }

    }

}