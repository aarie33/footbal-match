package arie.footballmatch.connections

import android.net.Uri
import arie.footballmatch.BuildConfig

object DBApi {
    fun getLastSchedules(id: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath("1")
                .appendPath("eventspastleague.php")
                .appendQueryParameter("id", id)
                .build()
                .toString()
    }
    fun getNextSchedules(id: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath("1")
                .appendPath("eventsnextleague.php")
                .appendQueryParameter("id", id)
                .build()
                .toString()
    }
    fun getSearchSchedules(query: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath("1")
                .appendPath("searchevents.php")
                .appendQueryParameter("e", query)
                .build()
                .toString()
    }
    fun getTeamDetail(id: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath("1")
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", id)
                .build()
                .toString()
    }
    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath("1")
                .appendPath("search_all_teams.php")
                .appendQueryParameter("l", league)
                .build()
                .toString()
    }
    fun getTeamSearch(query: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath("1")
                .appendPath("searchteams.php")
                .appendQueryParameter("t", query)
                .build()
                .toString()
    }
    fun getPlayerDetail(id: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath("1")
                .appendPath("lookupplayer.php")
                .appendQueryParameter("id", id)
                .build()
                .toString()
    }
    fun getPlayerList(id: String?): String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath("1")
                .appendPath("lookup_all_players.php")
                .appendQueryParameter("id", id)
                .build()
                .toString()
    }

}