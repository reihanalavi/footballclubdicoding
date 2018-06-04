package com.reihanalavi.footballmatch

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.reihanalavi.footballmatch.models.DetailItems
import com.reihanalavi.footballmatch.models.MatchItems
import com.reihanalavi.footballmatch.repo.ApiRepo
import com.reihanalavi.footballmatch.views.DetailPresenter
import com.reihanalavi.footballmatch.views.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx

class DetailActivity : AppCompatActivity(), DetailView {

    private val detailItems: MutableList<DetailItems> = mutableListOf()
    private lateinit var detailPresenter: DetailPresenter
    private lateinit var pbDetails: ProgressBar

    //Result View
    private lateinit var txtDate: TextView
    private lateinit var txtHomeTeam: TextView
    private lateinit var txtHomeForm: TextView
    private lateinit var txtAwayTeam: TextView
    private lateinit var txtAwayForm: TextView
    private lateinit var txtScore: TextView
    //Statistic View
    private lateinit var txtHomeGoals: TextView
    private lateinit var txtHomeShots: TextView
    private lateinit var txtAwayGoals: TextView
    private lateinit var txtAwayShots: TextView
    //Cards View
    private lateinit var txtHomeRed: TextView
    private lateinit var txtHomeYellow: TextView
    private lateinit var txtAwayRed: TextView
    private lateinit var txtAwayYellow: TextView
    //Lineup View
    private lateinit var txtHomeGk: TextView
    private lateinit var txtHomeDef: TextView
    private lateinit var txtHomeMid: TextView
    private lateinit var txtHomeFor: TextView
    private lateinit var txtAwayGk: TextView
    private lateinit var txtAwayDef: TextView
    private lateinit var txtAwayMid: TextView
    private lateinit var txtAwayFor: TextView
    //Substitution View
    private lateinit var txtHomeSub: TextView
    private lateinit var txtAwaySub: TextView

    override fun showLoading() {
        pbDetails.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        pbDetails.visibility = View.INVISIBLE
    }

    override fun showDetail(detail: List<DetailItems>?, home: List<DetailItems>?, away: List<DetailItems>?) {
        try {
            let {
                Log.i("Away Goal","${detail?.get(0)?.awayGoal}")
                Log.i("Away Badge", "${away?.get(0)?.teamBadge}")
            }
        } catch (e: Exception) {
            Log.d("TAG", e.toString())
        }
    }

    override fun showBadge() {
        Log.i("Away Badge", "${detailItems[0].awayGoal}")
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        ctx.let {
            pbDetails = findViewById(R.id.pb_details) as ProgressBar

            txtDate = findViewById(R.id.txtDate) as TextView
            txtHomeTeam = findViewById(R.id.txtHomeTeam) as TextView
            txtAwayTeam = findViewById(R.id.txtAwayTeam) as TextView
            txtScore = findViewById(R.id.txtScore) as TextView

            txtHomeGoals = findViewById(R.id.txtHomeGoals) as TextView
            txtHomeShots = findViewById(R.id.txtHomeShots) as TextView
            txtAwayGoals = findViewById(R.id.txtAwayGoals) as TextView
            txtAwayShots = findViewById(R.id.txtAwayShots) as TextView

            val i = intent

            detailPresenter = DetailPresenter(this, ApiRepo(), Gson())

            txtDate.text = i.getStringExtra("dateMatch")
            txtHomeTeam.text = i.getStringExtra("homeTeam")
            txtAwayTeam.text = i.getStringExtra("awayTeam")
            txtScore.text = i.getStringExtra("goalHome") + ":" + i.getStringExtra("goalAway")

            val homeId: String? = i.getStringExtra("idHome")
            val awayId: String? = i.getStringExtra("idAway")
            val matchId: String? = i.getStringExtra("matchId")

            detailPresenter.loadDetailMatch(homeId, awayId, matchId)

        }

    }
}
