package arie.footballmatch.connections

import com.google.gson.annotations.SerializedName

data class Team(
        @SerializedName("strTeamBadge")
        val strTeamBadge: String? = null,

        @SerializedName("idTeam")
        val teamId: String? = null,

        @SerializedName("strTeam")
        val teamName: String? = null,

        @SerializedName("intFormedYear")
        val teamFormedYear: String? = null,

        @SerializedName("strStadium")
        val teamStadium: String? = null,

        @SerializedName("strDescriptionEN")
        val teamDescription: String? = null
)