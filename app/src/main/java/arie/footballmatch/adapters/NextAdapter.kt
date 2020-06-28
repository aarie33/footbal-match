package arie.footballmatch.adapters

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import arie.footballmatch.DetailActivity
import arie.footballmatch.R
import arie.footballmatch.connections.Events
import org.jetbrains.anko.bundleOf
import java.text.SimpleDateFormat
import java.util.*

class NextAdapter (private val context: Context, private val items: List<Events>)
    :RecyclerView.Adapter<NextAdapter.MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.next_events_item,parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindItem(items[position])
    }


    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
            private val txtDate: TextView = itemView.findViewById(R.id.txt_date)
            private val txtTime: TextView = itemView.findViewById(R.id.txt_time)
            private val txtHomeTeam: TextView = itemView.findViewById(R.id.txt_home_team)
            private val txtAwayTeam: TextView = itemView.findViewById(R.id.txt_away_team)
            private val imgNotif: ImageView = itemView.findViewById(R.id.img_notif)

        fun toGMTFormat(date: String, time: String): Date? {
            val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateTime = "$date $time"
            return formatter.parse(dateTime)
        }

        @SuppressLint("MissingPermission", "SimpleDateFormat")
        fun bindItem(items: Events){
            val dateformat = SimpleDateFormat("dd-MM-yyyy")
            val timeformat = SimpleDateFormat("HH:mm")
            val y = SimpleDateFormat("yyyy")
            val m = SimpleDateFormat("MM")
            val d = SimpleDateFormat("dd")
            val h = SimpleDateFormat("HH")
            val mnt = SimpleDateFormat("mm")
            val currentTime = toGMTFormat(items.dateEvent.toString(), items.strTime.toString())
            val calendar: Calendar = Calendar.getInstance()

            calendar.setTime(currentTime)

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

            imgNotif.setOnClickListener {
                val calID: Long = 3
                val startMillis: Long = Calendar.getInstance().run {
                    set(y.format(calendar.time).toInt(), m.format(calendar.time).toInt(), d.format(calendar.time).toInt(),
                            h.format(calendar.time).toInt(), mnt.format(calendar.time).toInt())
                    timeInMillis
                }
                val endMillis: Long = Calendar.getInstance().run {
                    set(y.format(calendar.time).toInt(), m.format(calendar.time).toInt(), d.format(calendar.time).toInt(),
                            h.format(calendar.time).toInt(), mnt.format(calendar.time).toInt())
                    timeInMillis
                }


                val values = ContentValues().apply {
                    put(CalendarContract.Events.DTSTART, startMillis)
                    put(CalendarContract.Events.DTEND, endMillis)
                    put(CalendarContract.Events.TITLE, "Next Match Event")
                    put(CalendarContract.Events.DESCRIPTION, items.strHomeTeam + " vs " + items.strAwayTeam)
                    put(CalendarContract.Events.CALENDAR_ID, calID)
                    put(CalendarContract.Events.EVENT_TIMEZONE, "Jakarta")
                }

                val contentResolver = context.contentResolver
                val uri: Uri = contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
                val intent = Intent(Intent.ACTION_EDIT)
                        .setData(uri)
                        .putExtra(CalendarContract.Events.TITLE, "Next Match Event " + items.strHomeTeam + " vs " + items.strAwayTeam)
                context.startActivity(intent)
            }
        }
    }
}