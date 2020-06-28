package arie.footballmatch.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import arie.footballmatch.DetailActivity
import arie.footballmatch.R
import arie.footballmatch.connections.Event
import org.jetbrains.anko.bundleOf
import java.text.SimpleDateFormat
import java.util.*

class SearchAdapter (private val context: Context, private val items: List<Event>)
    : RecyclerView.Adapter<SearchAdapter.MyViewHolderCari>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolderCari {
        return MyViewHolderCari(LayoutInflater.from(context).inflate(R.layout.search_events_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolderCari?, position: Int) {
        holder?.bindItem(items[position])
    }


    inner class MyViewHolderCari(view: View): RecyclerView.ViewHolder(view){
        private val txtDate: TextView = itemView.findViewById(R.id.txt_date)
        private val txtTime: TextView = itemView.findViewById(R.id.txt_time)
        private val txtHomeTeam: TextView = itemView.findViewById(R.id.txt_home_team)
        private val txtAwayTeam: TextView = itemView.findViewById(R.id.txt_away_team)

        @SuppressLint("SimpleDateFormat")
        fun bindItem(items: Event){
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm")
            val dateformat = SimpleDateFormat("dd-MM-yyyy")
            val timeformat = SimpleDateFormat("HH:mm")
            val currentTime = items.dateEvent+" "+items.strTime?.substring(0,5)
            val date: Date = sdf.parse(currentTime)
            val calendar: Calendar = Calendar.getInstance()

            calendar.setTime(date)
            calendar.add(Calendar.HOUR, 7)

            txtDate.text = dateformat.format(calendar.time).toString()
            txtHomeTeam.text = items.strHomeTeam
            txtAwayTeam.text = items.strAwayTeam
            txtTime.text = timeformat.format(calendar.time).toString()

            itemView.setOnClickListener {
                val intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("event_id", items.idEvent)
                intent.putExtra("date", dateformat.format(calendar.time).toString())
                intent.putExtra("time", timeformat.format(calendar.time).toString())
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

                ContextCompat.startActivity(context, intent, bundleOf("event_id" to items.idEvent))
            }
        }
    }
}