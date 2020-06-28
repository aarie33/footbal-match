package arie.footballmatch

import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import arie.footballmatch.adapters.SearchAdapter
import arie.footballmatch.connections.Event
import arie.footballmatch.connections.ReqApi
import arie.footballmatch.presenters.SearchPresenter
import arie.footballmatch.views.SearchEventView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.onRefresh

class CariEventAct : AppCompatActivity(), SearchEventView {
    private lateinit var rvSchedule: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val event: MutableList<Event> = mutableListOf()
    private lateinit var nextPresenter: SearchPresenter
    private lateinit var adapter: SearchAdapter
    private lateinit var mQuery: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari_event)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rvSchedule = findViewById(R.id.rvSchedule)
        rvSchedule.layoutManager = LinearLayoutManager(this)
        progressBar = findViewById(R.id.progressbar)
        swipeRefreshLayout = findViewById(R.id.swipe)

        adapter = SearchAdapter(this, event)
        rvSchedule.adapter = adapter

        val reqApi = ReqApi()
        val myGson = Gson()
        nextPresenter = SearchPresenter(this, reqApi, myGson)

        swipeRefreshLayout.onRefresh {
            nextPresenter.getSearchEvents(mQuery)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_action, menu)
        val searchView = MenuItemCompat.getActionView(menu.findItem(R.id.action_search)) as SearchView
        searchView.setQueryHint(resources.getString(R.string.search_hint))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                mQuery = query
                nextPresenter.getSearchEvents(query)
                
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return false
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventListCari(data: List<Event>) {
        swipeRefreshLayout.isRefreshing = false
        event.clear()
        event.addAll(data)
        adapter.notifyDataSetChanged()
    }

    fun View.visible(){
        visibility = View.VISIBLE
    }
    fun View.invisible(){
        visibility = View.INVISIBLE
    }
}
