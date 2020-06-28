package arie.footballmatch.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import arie.footballmatch.R
import arie.footballmatch.adapters.FavEventsAdapter
import arie.footballmatch.sqlite.FavoriteEvents
import arie.footballmatch.sqlite.database
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavEventsFragment : Fragment() {
    private lateinit var rvSchedule: RecyclerView
    private val myfavorite: MutableList<FavoriteEvents> = mutableListOf()
    private lateinit var eventsAdapter: FavEventsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.fragment_fav, container, false)
        rvSchedule = view.findViewById(R.id.rvFavSchedule)
        rvSchedule.layoutManager = LinearLayoutManager(context)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        eventsAdapter = FavEventsAdapter(context, myfavorite)
        rvSchedule.adapter = eventsAdapter
        showFav()
    }

    override fun onResume() {
        super.onResume()
        showFav()
    }

    private fun showFav() {
        myfavorite.clear()
        context?.database?.use {
            val result = select(FavoriteEvents.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteEvents>())
            myfavorite.addAll(favorite)
            eventsAdapter.notifyDataSetChanged()
        }
    }
}
