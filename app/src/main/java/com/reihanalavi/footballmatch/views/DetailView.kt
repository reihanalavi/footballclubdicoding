package com.reihanalavi.footballmatch.views

import com.reihanalavi.footballmatch.models.DetailItems
import com.reihanalavi.footballmatch.models.TeamItems

/**
 * Created by rehan on 5/23/2018.
 */
interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetail(detail: List<DetailItems>?, home: List<TeamItems>?, away: List<TeamItems>?)
    fun showBadge()
}