package arie.footballmatch.views

import arie.footballmatch.connections.Events

interface LastView{
    fun showLoading()
    fun hideLoading()
    fun showEventList(data: List<Events>)
}