package arie.footballmatch.presenters

import arie.footballmatch.connections.DBApi
import arie.footballmatch.connections.PlayerResponse
import arie.footballmatch.connections.ReqApi
import arie.footballmatch.views.PlayersView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlayersPresenter(private val view: PlayersView,
                       private val apiRepository: ReqApi,
                       private val gson: Gson) {

    fun getPlayerList(teamId: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(DBApi.getPlayerList(teamId)),
                    PlayerResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showPlayerList(data.player)
            }
        }
    }
}