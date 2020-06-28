package arie.footballmatch.connections

import com.google.gson.annotations.SerializedName

data class PlayerResponse (
        @SerializedName("player")
        val player : List<Player>
)