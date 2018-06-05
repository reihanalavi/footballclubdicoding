package com.reihanalavi.footballmatch

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.reihanalavi.footballmatch.models.DetailItems
import com.reihanalavi.footballmatch.models.TeamItems
import com.reihanalavi.footballmatch.repo.ApiRepo
import com.reihanalavi.footballmatch.views.DetailPresenter
import com.reihanalavi.footballmatch.views.DetailView
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx

class DetailActivity : AppCompatActivity(), DetailView {

    private val detailItems: MutableList<DetailItems> = mutableListOf()
    private lateinit var detailPresenter: DetailPresenter

    override fun showLoading() {
        pb_details.visibility = View.VISIBLE
        mainLayout.visibility = View.INVISIBLE
    }

    override fun hideLoading() {
        pb_details.visibility = View.INVISIBLE
        mainLayout.visibility = View.VISIBLE
    }

    override fun showDetail(detail: List<DetailItems>?, home: List<TeamItems>?, away: List<TeamItems>?) {
        try {
            let {
                val data = detail?.get(0)
                val homeData = home?.get(0)
                val awayData = away?.get(0)

                //Replaces Team Home from Detail Results
                val goalHome: String? = data?.homeGoal
                goalHome?.replace(";", "\n")
                val redHome: String? = data?.homeRed
                redHome?.replace(";", "\n")
                val yellowHome: String? = data?.homeYellow
                yellowHome?.replace(";", "\n")
                val gkHome: String? = data?.homeGk
                gkHome?.replace(";", "\n")
                val defHome: String? = data?.homeDef
                defHome?.replace(";", "\n")
                val midHome: String? = data?.homeMid
                midHome?.replace(";", "\n")
                val forHome: String? = data?.homeFor
                forHome?.replace(";", "\n")
                val subHome: String? = data?.homeSub
                subHome?.replace(";", "\n")

                //Replaces Team Away from Detail Results
                val goalAway: String? = data?.awayGoal
                goalAway?.replace(";", "\n")
                val redAway: String? = data?.awayRed
                redAway?.replace(";", "\n")
                val yellowAway: String? = data?.awayYellow
                yellowAway?.replace(";", "\n")
                val gkAway: String? = data?.awayGk
                gkAway?.replace(";", "\n")
                val defAway: String? = data?.awayDef
                defAway?.replace(";", "\n")
                val midAway: String? = data?.awayMid
                midAway?.replace(";", "\n")
                val forAway: String? = data?.awayFor
                forAway?.replace(";", "\n")
                val subAway: String? = data?.awaySub
                subAway?.replace(";", "\n")

                Glide.with(ctx).load(homeData?.teamBadge).into(img_home)
                txt_manager_home.text = homeData?.teamManager
                txt_goal_home.text = goalHome
                txt_shots_home.text = data?.homeShots
                txt_red_home.text = redHome
                txt_yellow_home.text = yellowHome
                txt_gk_home.text = gkHome
                txt_def_home.text = defHome
                txt_mid_home.text = midHome
                txt_for_home.text = forHome
                txt_sub_home.text = subHome

                Glide.with(ctx).load(awayData?.teamBadge).into(img_away)
                txt_manager_away.text = awayData?.teamManager
                txt_goal_away.text = goalAway
                txt_shots_away.text = data?.awayShots
                txt_red_away.text = redAway
                txt_yellow_away.text = yellowAway
                txt_gk_away.text = gkAway
                txt_def_away.text = defAway
                txt_mid_away.text = midAway
                txt_for_away.text = forAway
                txt_sub_away.text = subAway
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

            //pbDetails = findViewById(R.id.pb_details) as ProgressBar

            /**
            txtDate = findViewById(R.id.txtDate) as TextView
            txtHomeTeam = findViewById(R.id.txtHomeTeam) as TextView
            txtAwayTeam = findViewById(R.id.txtAwayTeam) as TextView
            txtScore = findViewById(R.id.txtScore) as TextView

            txtHomeGoals = findViewById(R.id.txtHomeGoals) as TextView
            txtHomeShots = findViewById(R.id.txtHomeShots) as TextView
            txtAwayGoals = findViewById(R.id.txtAwayGoals) as TextView
            txtAwayShots = findViewById(R.id.txtAwayShots) as TextView
            **/

            val i = intent

            detailPresenter = DetailPresenter(this, ApiRepo(), Gson())

            txt_date.text = i.getStringExtra("dateMatch")
            txt_team_home.text = i.getStringExtra("homeTeam")
            txt_team_away.text = i.getStringExtra("awayTeam")
            txt_score.text = i.getStringExtra("goalHome") + ":" + i.getStringExtra("goalAway")

            val homeId: String? = i.getStringExtra("idHome")
            val awayId: String? = i.getStringExtra("idAway")
            val matchId: String? = i.getStringExtra("matchId")

            detailPresenter.loadDetailMatch(homeId, awayId, matchId)

        }

    }
}
