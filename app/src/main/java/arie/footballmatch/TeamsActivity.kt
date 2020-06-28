package arie.footballmatch

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import arie.footballmatch.fragments.TeamsFragment
import arie.footballmatch.fragments.TeamsSearchFragment
import kotlinx.android.synthetic.main.activity_main.*



class TeamsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.teams

        supportFragmentManager.beginTransaction().add(R.id.rlFragment, TeamsFragment(), "Teams").commit()
    }

    override fun onResume() {
        super.onResume()
        navigation.selectedItemId = R.id.teams
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.events ->{
                var mainIntent = Intent(this, MainActivity::class.java)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(mainIntent)
                finish()
            }
            R.id.teams ->{
                false
            }
            R.id.favorites ->{
                var favIntent = Intent(this, FavActivity::class.java)
                favIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(favIntent)
                finish()
            }
        }
        true
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_action, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        var searchView: SearchView = searchItem?.actionView as SearchView
        searchView.queryHint = "Cari team"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val fragment = supportFragmentManager.findFragmentByTag("TeamsSearch") as TeamsSearchFragment
                fragment.getTeam(p0)
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })
        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                Log.d("diskusage", "search closed")
                return true
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_search->{
                supportFragmentManager.beginTransaction().replace(R.id.rlFragment,TeamsSearchFragment(), "TeamsSearch").addToBackStack("Back").commit()
                return true
            }
            android.R.id.home->{
                onBackPressed()
                return false
            }
            else->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
