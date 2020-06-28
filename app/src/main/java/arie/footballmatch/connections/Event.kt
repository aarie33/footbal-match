package arie.footballmatch.connections

import com.google.gson.annotations.SerializedName

data class Event(
        @SerializedName("idEvent")
        val idEvent: String? = null,
        @SerializedName("dateEvent")
        val dateEvent: String? = null,
        @SerializedName("idHomeTeam")
        val idHomeTeam: String? = null,
        @SerializedName("strHomeTeam")
        val strHomeTeam: String? = null,
        @SerializedName("intHomeScore")
        val intHomeScore: String? = null,
        @SerializedName("strHomeGoalDetails")
        val strHomeGoalDetails: String? = null,
        @SerializedName("intHomeShots")
        val intHomeShots: String? = null,
        @SerializedName("strHomeLineupGoalkeeper")
        val strHomeLineupGoalkeeper: String? = null,
        @SerializedName("strHomeLineupDefense")
        val strHomeLineupDefense: String? = null,
        @SerializedName("strHomeLineupMidfield")
        val strHomeLineupMidfield: String? = null,
        @SerializedName("idAwayTeam")
        val idAwayTeam: String? = null,
        @SerializedName("strAwayTeam")
        val strAwayTeam: String? = null,
        @SerializedName("intAwayScore")
        val intAwayScore: String? = null,
        @SerializedName("strAwayGoalDetails")
        val strAwayGoalDetails: String? = null,
        @SerializedName("intAwayShots")
        val intAwayShots: String? = null,
        @SerializedName("strAwayLineupGoalkeeper")
        val strAwayLineupGoalkeeper: String? = null,
        @SerializedName("strAwayLineupDefense")
        val strAwayLineupDefense: String? = null,
        @SerializedName("strAwayLineupMidfield")
        val strAwayLineupMidfield: String? = null,
        @SerializedName("strTime")
        val strTime: String? = null
)