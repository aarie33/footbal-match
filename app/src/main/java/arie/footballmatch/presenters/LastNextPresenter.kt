package arie.footballmatch.presenters

import android.util.Log
import arie.footballmatch.connections.DBApi
import arie.footballmatch.connections.EventsResponse
import arie.footballmatch.connections.ReqApi
import arie.footballmatch.views.LastView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class LastNextPresenter(private val view: LastView,
                        private val req: ReqApi,
                        private val gson: Gson) {

    fun getLastEventsList(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(req
                    .doRequest(DBApi.getLastSchedules(id)),
                    EventsResponse::class.java)

            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }
    }
    fun getNextEventsList(id: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(req
                    .doRequest(DBApi.getNextSchedules(id)),
                    EventsResponse::class.java)

            uiThread {
                view.hideLoading()
                Log.e("Data Search", data.toString())
                if (data!=null)
                    view.showEventList(data.events)
            }
        }
    }
}