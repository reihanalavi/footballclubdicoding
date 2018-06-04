package com.reihanalavi.footballmatch.views

import com.reihanalavi.footballmatch.models.DetailItems

/**
 * Created by rehan on 5/23/2018.
 */
interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetail(detail: List<DetailItems>?, home: List<DetailItems>?, away: List<DetailItems>?)
    fun showBadge()
}