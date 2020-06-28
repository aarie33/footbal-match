package arie.footballmatch.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import arie.footballmatch.DetailActivity
import arie.footballmatch.R
import arie.footballmatch.sqlite.FavoriteEvents
import org.jetbrains.anko.bundleOf

class FavEventsAdapter(private val context: Context, private val favorite: List<FavoriteEvents>)
    :RecyclerView.Adapter<FavEventsAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.last_events_item, parent, false))
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(favorite[position])
    }

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        private val txtTime: TextView = itemView.findViewById(R.id.txt_time)
        private val txtHomeTeam: TextView = itemView.findViewById(R.id.txt_home_team)
        private val txtAwayTeam: TextView = itemView.findViewById(R.id.txt_away_team)
        private val txtHomeScore: TextView = itemView.findViewById(R.id.txt_home_score)
        private val txtAwayScore: TextView = itemView.findViewById(R.id.txt_away_score)

        fun bindItem(items: FavoriteEvents){
            txtDate.text = items.strDate
            txtTime.text = items.strTime
            txtHomeTeam.text = items.strHomeTeam
            txtAwayTeam.text = items.strAwayTeam
            txtHomeScore.text = items.intHomeScore
            txtAwayScore.text = items.intAwayScore

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("event_id", items.idEvent)
                intent.putExtra("date", items.strDate)
                intent.putExtra("home_id", items.idHomeTeam)
                intent.putExtra("home_team", items.strHomeTeam)
                intent.putExtra("home_score", items.intHomeScore)
                intent.putExtra("home_goal", items.strHomeGoalDetails)
                intent.putExtra("home_shot", items.intHomeShots)
                intent.putExtra("home_gk", items.strHomeLineupGoalkeeper)
                intent.putExtra("home_defense", items.strHomeLineupDefense)
                intent.putExtra("home_mid", items.strHomeLineupMidfield)

                intent.putExtra("away_id", items.idAwayTeam)
                intent.putExtra("away_team", items.strAwayTeam)
                intent.putExtra("away_score", items.intAwayScore)
                intent.putExtra("away_goal", items.strAwayGoalDetails)
                intent.putExtra("away_shot", items.intAwayShots)
                intent.putExtra("away_gk", items.strAwayLineupGoalkeeper)
                intent.putExtra("away_defense", items.strAwayLineupDefense)
                intent.putExtra("away_mid", items.strAwayLineupMidfield)

                startActivity(context, intent, bundleOf("event_id" to items.idEvent))
            }
        }
    }
}