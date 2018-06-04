package com.reihanalavi.footballmatch.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Spinner
import android.widget.Toast
import com.google.gson.Gson
import com.reihanalavi.footballmatch.DetailActivity
import com.reihanalavi.footballmatch.MainActivity

import com.reihanalavi.footballmatch.R
import com.reihanalavi.footballmatch.models.MatchItems
import com.reihanalavi.footballmatch.repo.ApiRepo
import com.reihanalavi.footballmatch.views.MatchAdapter
import com.reihanalavi.footballmatch.views.MatchPresenter
import com.reihanalavi.footballmatch.views.MatchView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_past_match.*
import kotlinx.android.synthetic.main.fragment_past_match.view.*
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import java.util.logging.Logger


/**
 * A simple [Fragment] subclass.
 */
class PastMatch : Fragment(), MatchView {

    private val matchItems: MutableList<MatchItems> = mutableListOf()
    private lateinit var matchPresenter: MatchPresenter
    private lateinit var matchAdapter: MatchAdapter

    private lateinit var listMatch: RecyclerView
    private lateinit var progressBar: ProgressBar

    private var pastMatch: String = "PAST_MATCH"

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.INVISIBLE
    }

    override fun showMatch(data: List<MatchItems>?) {
        data?.let {
            Log.i("On Show Match : ", "Data Size : ${data?.size}")
            matchAdapter.refresh(it)
        }
    }

    /** This is used to parse JSON from API without Adapter
    fun bindData(matchItems: MatchItems?) {
        var team: String? = matchItems?.teamHome
        team?.replace(";","\n")
    }
    **/

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val views = inflater?.inflate(R.layout.fragment_past_match, container, false)


        views?.let {

            listMatch = it.findViewById(R.id.rv_past) as RecyclerView
            progressBar = it.findViewById(R.id.pb_past) as ProgressBar

            matchAdapter = MatchAdapter(context, matchItems) {
                ctx.startActivity<DetailActivity>(
                        "idHome" to it.homeTeamId, "idAway" to it.awayTeamId,
                        "goalHome" to it.scoreHome, "goalAway" to it.scoreAway,
                        "homeTeam" to it.teamHome, "awayTeam" to it.teamAway,
                        "dateMatch" to it.dateMatch,
                        "matchId" to it.eventId
                )
            }

            listMatch.layoutManager = LinearLayoutManager(ctx)

            listMatch.adapter = matchAdapter

            matchPresenter = MatchPresenter(this, ApiRepo(), Gson())

            matchPresenter.getMatch(pastMatch)
        }

        return views
    }

}// Required empty public constructor