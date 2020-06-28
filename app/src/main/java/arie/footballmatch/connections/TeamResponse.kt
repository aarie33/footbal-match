package arie.footballmatch.connections

import com.google.gson.annotations.SerializedName

data class TeamResponse (
        @SerializedName("teams")
        val teams : List<Team>
)