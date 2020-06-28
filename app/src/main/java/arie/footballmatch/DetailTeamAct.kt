package arie.footballmatch

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import arie.footballmatch.R.drawable.ic_add_to_favorite
import arie.footballmatch.R.drawable.ic_added_to_favorite
import arie.footballmatch.R.id.add_to_favorite
import arie.footballmatch.R.menu.detail_menu
import arie.footballmatch.fragments.OverviewTeamFragment
import arie.footballmatch.fragments.PlayerTeamsFragment
import arie.footballmatch.sqlite.FavoriteTeams
import arie.footballmatch.sqlite.database
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailTeamAct : AppCompatActivity() {
    private var mSectionsPagerAdapter: DetailTeamAct.SectionsPagerAdapter? = null
    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setTitle("")

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.setupWithViewPager(container)

        val intent = intent
        id = intent.getStringExtra("team_id")

        Picasso.get().load(intent.getStringExtra("team_badge")).into(img_badge)
        txtTeam.text = intent.getStringExtra("team_name")
        txtYear.text = intent.getStringExtra("team_year")
        txtStadium.text = intent.getStringExtra("team_stadium")

        favoriteState()
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when(position){
                0 -> {
                    return OverviewTeamFragment.newInstance(intent.getStringExtra("team_description"))
                }
                1 -> {
                    return PlayerTeamsFragment.createInstance(intent.getStringExtra("team_id"))
                }
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            when(position){
                0 -> {
                    return "Overview"
                }
                1 -> {
                    return "Players"
                }
                else ->{
                    return "Team Detail"
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
                return false
            }
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite() else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                return true
            }
            else->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }
    private fun addToFavorite(){
        try {
            database.use {
                insert(FavoriteTeams.TABLE_FAVORITE,
                        FavoriteTeams.TEAM_ID to intent.getStringExtra("team_id"),
                        FavoriteTeams.TEAM_NAME to intent.getStringExtra("team_name"),
                        FavoriteTeams.TEAM_BADGE to intent.getStringExtra("team_badge"))
            }
            snackbar(container, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(container, e.localizedMessage).show()
        }
    }
    private fun removeFromFavorite(){
        try {
            database.use {
                delete(FavoriteTeams.TABLE_FAVORITE, "(TEAM_ID = {id})",
                        "id" to id)
            }
            snackbar(container, "Removed to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(container, e.localizedMessage).show()
        }
    }
    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
    }
    private fun favoriteState(){
        database.use {
            val result = select(FavoriteTeams.TABLE_FAVORITE)
                    .whereArgs("("+FavoriteTeams.TEAM_ID+" = {id})",
                            "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }
}
