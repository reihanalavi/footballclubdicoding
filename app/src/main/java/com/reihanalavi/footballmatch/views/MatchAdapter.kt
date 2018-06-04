package com.reihanalavi.footballmatch.views

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.reihanalavi.footballmatch.R
import com.reihanalavi.footballmatch.models.MatchItems
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by rehan on 5/21/2018.
 */
class MatchAdapter(private val context: Context, private var matchItems: List<MatchItems>, private val listener: (MatchItems) -> Unit)
    : RecyclerView.Adapter<MatchAdapter.MatchViewHolder>(){


    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(matchItems[position], listener)
    }

    override fun getItemCount(): Int {
        return matchItems.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(LayoutInflater.from(context).inflate(R.layout.items, parent, false))
    }

    fun refresh(fill: List<MatchItems>) {
        matchItems = fill
        notifyDataSetChanged()
    }

    class MatchViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val dateMatch = view.findViewById(R.id.date_match) as TextView
        private val homeTeam = view.findViewById(R.id.home_team) as TextView
        private val awayTeam = view.findViewById(R.id.away_team) as TextView
        private val scoreTeam = view.findViewById(R.id.score_team) as TextView

        fun bindItem(matchItems: MatchItems, listener: (MatchItems) -> Unit) {

            dateMatch.text = matchItems.dateMatch
            homeTeam.text = matchItems.teamHome
            awayTeam.text = matchItems.teamAway
            scoreTeam.text = matchItems.scoreHome + ":" + matchItems.scoreAway
            itemView.onClick {listener(matchItems)}

        }

    }


}