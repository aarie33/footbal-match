package arie.footballmatch.views

import arie.footballmatch.connections.Event

interface SearchEventView {
    fun showLoading()
    fun hideLoading()
    fun showEventListCari(data: List<Event>)
}