package arie.footballmatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import arie.footballmatch.R.drawable.ic_add_to_favorite
import arie.footballmatch.R.drawable.ic_added_to_favorite
import arie.footballmatch.R.id.add_to_favorite
import arie.footballmatch.connections.Events
import arie.footballmatch.connections.ReqApi
import arie.footballmatch.presenters.DetailPresenter
import arie.footballmatch.sqlite.FavoriteEvents
import arie.footballmatch.sqlite.database
import arie.footballmatch.views.DetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh

class DetailActivity : AppCompatActivity(), DetailView {
    private lateinit var presenter: DetailPresenter
    private lateinit var id: String
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var events: Events
    private lateinit var swipe: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("Match Detail")
        val intent = intent
        id = intent.getStringExtra("event_id")

        events = Events(intent.getStringExtra("event_id"),
                intent.getStringExtra("date"),
                intent.getStringExtra("home_id"),
                intent.getStringExtra("home_team"),
                intent.getStringExtra("home_score"),
                if (intent.getStringExtra("home_goal") !=null) intent.getStringExtra("home_goal") else "",
                intent.getStringExtra("home_shot"),
                if (intent.getStringExtra("home_gk") !=null) intent.getStringExtra("home_gk") else "",
                if (intent.getStringExtra("home_defense") !=null) intent.getStringExtra("home_defense") else "",
                if (intent.getStringExtra("home_mid") !=null) intent.getStringExtra("home_mid") else "",
                intent.getStringExtra("away_id"),
                intent.getStringExtra("away_team"),
                intent.getStringExtra("away_score"),
                if (intent.getStringExtra("away_goal") !=null) intent.getStringExtra("away_goal") else "",
                intent.getStringExtra("away_shot"),
                if (intent.getStringExtra("away_gk") !=null) intent.getStringExtra("away_gk") else "",
                if (intent.getStringExtra("away_defense") !=null) intent.getStringExtra("away_defense") else "",
                if (intent.getStringExtra("away_mid") !=null) intent.getStringExtra("away_mid") else "",
                intent.getStringExtra("time"))

        val req = ReqApi()
        val gson = Gson()
        presenter = DetailPresenter(this, req, gson)
        presenter.getHomeDetail(intent.getStringExtra("home_id"))
        presenter.getAwayDetail(intent.getStringExtra("away_id"))
        swipe = findViewById(R.id.swipe)
        swipe.onRefresh {
            showDetail()
        }

        favoriteState()
    }

    override fun onResume() {
        super.onResume()
        showDetail()
    }

    private fun showDetail(){
        swipe.isRefreshing = false
        txtDate.text = events.dateEvent
        txtTime.text = events.strTime
        txtHomeTeam.text = events.strHomeTeam
        txtHomeScore.text = events.intHomeScore
        txtHomeGoals.text = events.strHomeGoalDetails?.replace(";", "\n")
        txtHomeShots.text = events.intHomeShots
        txtHomeGk.text = events.strHomeLineupGoalkeeper?.replace(";", "\n")
        txtHomeDefense.text = events.strHomeLineupDefense?.replace(";", "\n")
        txtHomeMid.text = events.strHomeLineupMidfield?.replace(";", "\n")

        txtAwayTeam.text = events.strAwayTeam
        txtAwayScore.text = events.intAwayScore
        txtAwayGoals.text = events.strAwayGoalDetails?.replace(";", "\n")
        txtAwayShots.text = events.intAwayShots
        txtAwayGk.text = events.strAwayLineupGoalkeeper?.replace(";", "\n")
        txtAwayDefense.text = events.strAwayLineupDefense?.replace(";", "\n")
        txtAwayMid.text = events.strAwayLineupMidfield?.replace(";", "\n")
    }
    override fun showHomeDetail(badge: String?) {
        Picasso.get().load(badge).into(imgHome)
    }
    override fun showAwayDetail(badge: String?) {
        Picasso.get().load(badge).into(imgAway)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return false
            }
            add_to_favorite -> {
                if (isFavorite) removeFavorite() else addToFavorite()
                isFavorite = !isFavorite
                setFavorite()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    private fun addToFavorite(){
        try{
            database.use {
                insert(FavoriteEvents.TABLE_FAVORITE,
                        FavoriteEvents.idEvent to events.idEvent,
                        FavoriteEvents.strDate to events.dateEvent,
                        FavoriteEvents.strTime to events.strTime,
                        FavoriteEvents.idHomeTeam to events.idHomeTeam,
                        FavoriteEvents.strHomeTeam to events.strHomeTeam,
                        FavoriteEvents.intHomeScore to events.intHomeScore,
                        FavoriteEvents.strHomeGoalDetails to events.strHomeGoalDetails,
                        FavoriteEvents.intHomeShots to events.intHomeShots,
                        FavoriteEvents.strHomeLineupGoalkeeper to events.strHomeLineupGoalkeeper,
                        FavoriteEvents.strHomeLineupDefense to events.strHomeLineupDefense,
                        FavoriteEvents.strHomeLineupMidfield to events.strHomeLineupMidfield,
                        FavoriteEvents.idAwayTeam to events.idAwayTeam,
                        FavoriteEvents.strAwayTeam to events.strAwayTeam,
                        FavoriteEvents.intAwayScore to events.intAwayScore,
                        FavoriteEvents.strAwayGoalDetails to events.strAwayGoalDetails,
                        FavoriteEvents.intAwayShots to events.intAwayShots,
                        FavoriteEvents.strAwayLineupGoalkeeper to events.strAwayLineupGoalkeeper,
                        FavoriteEvents.strAwayLineupDefense to events.strAwayLineupDefense,
                        FavoriteEvents.strAwayLineupMidfield to events.strAwayLineupMidfield)
            }
            snackbar(swipe, "Added to Favorites").show()
        }catch (e: SQLiteConstraintException){
            snackbar(swipe, e.localizedMessage).show()
        }
    }
    private fun removeFavorite(){
        try {
            database.use {
                delete(FavoriteEvents.TABLE_FAVORITE, "(idEvent = {id})",
                        "id" to id)
            }
            snackbar(swipe, "Removed from Favorites").show()
        } catch (e: SQLiteConstraintException) {
            snackbar(swipe, e.localizedMessage).show()
        }
    }
    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
    }
    private fun favoriteState(){
        database.use {
            val result = select(FavoriteEvents.TABLE_FAVORITE)
                    .whereArgs("(idEvent = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteEvents>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
