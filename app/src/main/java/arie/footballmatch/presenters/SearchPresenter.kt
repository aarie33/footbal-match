package arie.footballmatch.presenters

import arie.footballmatch.connections.DBApi
import arie.footballmatch.connections.EventResponseCari
import arie.footballmatch.connections.ReqApi
import arie.footballmatch.views.SearchEventView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class SearchPresenter(private val view: SearchEventView,
                      private val req: ReqApi,
                      private val gson: Gson) {
    fun getSearchEvents(query: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(req
                    .doRequest(DBApi.getSearchSchedules(query)),
                    EventResponseCari::class.java)

            uiThread {
                view.hideLoading()
                if (data!=null)
                    view.showEventListCari(data.event)
            }
        }
    }
}