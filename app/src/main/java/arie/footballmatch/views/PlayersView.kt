package arie.footballmatch.views

import arie.footballmatch.connections.Player

interface PlayersView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}