package com.reihanalavi.footballmatch.fragments


import android.app.ActionBar
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import com.google.gson.Gson
import com.reihanalavi.footballmatch.DetailActivity

import com.reihanalavi.footballmatch.R
import com.reihanalavi.footballmatch.models.MatchItems
import com.reihanalavi.footballmatch.repo.ApiRepo
import com.reihanalavi.footballmatch.views.MatchAdapter
import com.reihanalavi.footballmatch.views.MatchPresenter
import com.reihanalavi.footballmatch.views.MatchView
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.toast


/**
 * A simple [Fragment] subclass.
 */
class NextMatch : Fragment(), MatchView {

    private val matchItems: MutableList<MatchItems> = mutableListOf()
    private lateinit var matchPresenter: MatchPresenter
    private lateinit var matchAdapter: MatchAdapter

    private lateinit var pbNext: ProgressBar
    private lateinit var rvNext: RecyclerView

    private val nextMatch: String = "NEXT_MATCH"

    override fun showLoading() {
        pbNext.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbNext.visibility = View.INVISIBLE
    }

    override fun showMatch(data: List<MatchItems>?) {
        data?.let {
            Log.i("On Show Match : ", "Data Size : ${data?.size}")
            matchAdapter.refresh(it)
            if(data?.size == null) {
                toast(getString(R.string.there_is_no_upcoming_match))
            }
        }
    }

    override fun showNull() {
        toast("There is no upcoming match(es)")
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val views = inflater?.inflate(R.layout.fragment_next_match, container, false)

        pbNext = views?.findViewById(R.id.pb_next) as ProgressBar
        rvNext = views?.findViewById(R.id.rv_next) as RecyclerView

        views?.let {

            matchAdapter = MatchAdapter(context, matchItems) {
                ctx.startActivity<DetailActivity>(
                        "idHome" to it.homeTeamId, "idAway" to it.awayTeamId,
                        "goalHome" to it.scoreHome, "goalAway" to it.scoreAway,
                        "homeTeam" to it.teamHome, "awayTeam" to it.teamAway,
                        "dateMatch" to it.dateMatch,
                        "matchId" to it.eventId
                )
            }

            rvNext.layoutManager = LinearLayoutManager(ctx)
            rvNext.adapter = matchAdapter
            matchPresenter = MatchPresenter(this, ApiRepo(), Gson())
            matchPresenter.getMatch(nextMatch)

        }

        return views
    }

}