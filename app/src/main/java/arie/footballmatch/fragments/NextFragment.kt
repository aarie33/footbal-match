package arie.footballmatch.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import arie.footballmatch.R
import arie.footballmatch.adapters.NextAdapter
import arie.footballmatch.connections.Events
import arie.footballmatch.connections.ReqApi
import arie.footballmatch.presenters.LastNextPresenter
import arie.footballmatch.views.LastView
import com.google.gson.Gson
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class NextFragment : Fragment(), LastView {
    private lateinit var rvNextSchedule: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var spinner: Spinner
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private val events: MutableList<Events> = mutableListOf()
    private lateinit var nextPresenter: LastNextPresenter
    private lateinit var adapter: NextAdapter
    private lateinit var leagueName: String
    private lateinit var leagueId: Array<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_next, container, false)
        rvNextSchedule = view.findViewById(R.id.rvNextSchedule)
        rvNextSchedule.layoutManager = LinearLayoutManager(context)
        progressBar = view.findViewById(R.id.progressbar)
        swipeRefreshLayout = view.findViewById(R.id.swipe)

        adapter = NextAdapter(context, events)
        rvNextSchedule.adapter = adapter

        leagueId = resources.getStringArray(R.array.leagueId)
        leagueName = leagueId[0]

        val reqApi = ReqApi()
        val myGson = Gson()
        nextPresenter = LastNextPresenter(this, reqApi, myGson)

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(ctx, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner = view.findViewById(R.id.spinner)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                leagueName = leagueId[position]
                nextPresenter.getNextEventsList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                leagueName = leagueId[0]
            }
        }

        swipeRefreshLayout.onRefresh {
            nextPresenter.getNextEventsList(leagueName)
        }
        nextPresenter.getNextEventsList(leagueName)

        return view
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showEventList(data: List<Events>) {
        swipeRefreshLayout.isRefreshing = false
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
    }
    fun View.visible(){
        visibility = View.VISIBLE
    }
    fun View.invisible(){
        visibility = View.INVISIBLE
    }
}
