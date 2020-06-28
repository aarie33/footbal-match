package arie.footballmatch.views

import arie.footballmatch.connections.Team

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}