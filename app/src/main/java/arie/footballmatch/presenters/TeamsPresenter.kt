package arie.footballmatch.presenters

import arie.footballmatch.connections.DBApi
import arie.footballmatch.connections.ReqApi
import arie.footballmatch.connections.TeamResponse
import arie.footballmatch.views.TeamsView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class TeamsPresenter(private val view: TeamsView,
                     private val apiRepository: ReqApi,
                     private val gson: Gson) {

    fun getTeamList(league: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(DBApi.getTeams(league)),
                    TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamList(data.teams)
            }
        }
    }
    fun getTeamSearch(query: String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(DBApi.getTeamSearch(query)),
                    TeamResponse::class.java)
            uiThread {
                view.hideLoading()
                view.showTeamList(data?.teams!!)
            }
        }
    }
}