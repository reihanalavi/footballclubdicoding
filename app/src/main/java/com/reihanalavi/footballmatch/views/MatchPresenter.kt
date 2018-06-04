package com.reihanalavi.footballmatch.views

import com.google.gson.Gson
import com.reihanalavi.footballmatch.repo.ApiCall
import com.reihanalavi.footballmatch.repo.ApiRepo
import com.reihanalavi.footballmatch.responses.MatchResponse
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

/**
 * Created by rehan on 5/21/2018.
 */
class MatchPresenter(
        private val view: MatchView,
        private val apiRepo: ApiRepo,
        private val gson: Gson
) {

    fun getMatch(nextOrMatch: String?) {

        view.showLoading()

        when(nextOrMatch) {
            "NEXT_MATCH" -> {
                doAsync {
                    val data = gson.fromJson(apiRepo
                            .doRequest(ApiCall.getNextMatch()),
                            MatchResponse::class.java
                    )
                    uiThread {
                        view.hideLoading()
                        view.showMatch(data.matchItems)
                    }
                }

            }
            "PAST_MATCH" -> {
                doAsync {
                    val data = gson.fromJson(apiRepo
                            .doRequest(ApiCall.getPastMatch()),
                            MatchResponse::class.java
                    )
                    uiThread {
                        view.hideLoading()
                        view.showMatch(data.matchItems)
                    }
                }
            }
        }


    }

}