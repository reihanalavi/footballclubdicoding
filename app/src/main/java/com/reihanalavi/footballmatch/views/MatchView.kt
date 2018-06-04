package com.reihanalavi.footballmatch.views

import com.reihanalavi.footballmatch.models.MatchItems

/**
 * Created by rehan on 5/21/2018.
 */
interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatch(data: List<MatchItems>?)
}