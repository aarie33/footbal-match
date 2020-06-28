package arie.footballmatch.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arie.footballmatch.R
import arie.footballmatch.adapters.FavTeamsAdapter
import arie.footballmatch.sqlite.FavoriteTeams
import arie.footballmatch.sqlite.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class FavTeamsFragment: Fragment() {
    private lateinit var rvSchedule: RecyclerView
    private val myfavorite: MutableList<FavoriteTeams> = mutableListOf()
    private lateinit var adapter: FavTeamsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_fav_teams, container, false)
        rvSchedule = view.findViewById(R.id.rvFavTeams)
        rvSchedule.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = FavTeamsAdapter(context, myfavorite)
        rvSchedule.adapter = adapter
        showFav()
    }

    override fun onResume() {
        super.onResume()
        showFav()
    }

    private fun showFav(){
        myfavorite.clear()
        context?.database?.use {
            val result = select(FavoriteTeams.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteTeams>())
            myfavorite.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }
}
