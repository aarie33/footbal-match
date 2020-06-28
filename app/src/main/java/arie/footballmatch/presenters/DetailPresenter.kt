package arie.footballmatch.presenters

import android.util.Log
import arie.footballmatch.connections.DBApi
import arie.footballmatch.connections.ReqApi
import arie.footballmatch.connections.TeamResponse
import arie.footballmatch.views.DetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(private val view: DetailView,
                      private val req: ReqApi,
                      private val gson: Gson) {
    fun getHomeDetail(id: String?) {
        doAsync {
            val data = gson.fromJson(req
                    .doRequest(DBApi.getTeamDetail(id)),
                    TeamResponse::class.java)
            Log.d("GET HOME D", data.toString())
            Log.d("GET IMG", data.teams.toString())

            uiThread {
                view.showHomeDetail(data.teams[0].strTeamBadge)
            }
        }
    }
    fun getAwayDetail(id: String?) {
        doAsync {
            val data = gson.fromJson(req
                    .doRequest(DBApi.getTeamDetail(id)),
                    TeamResponse::class.java)

            uiThread {
                view.showAwayDetail(data.teams[0].strTeamBadge)
            }
        }
    }
}