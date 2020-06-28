package arie.footballmatch.connections

import com.google.gson.annotations.SerializedName

data class Player(
        @SerializedName("idPlayer")
        val idPlayer: String? = null,
        @SerializedName("idTeam")
        val idTeam: String? = null,
        @SerializedName("idSoccerXML")
        val idSoccerXML: String? = null,
        @SerializedName("idPlayerManager")
        val idPlayerManager: String? = null,
        @SerializedName("strNationality")
        val strNationality: String? = null,
        @SerializedName("strPlayer")
        val strPlayer: String? = null,
        @SerializedName("strTeam")
        val strTeam: String? = null,
        @SerializedName("strSport")
        val strSport: String? = null,
        @SerializedName("intSoccerXMLTeamID")
        val intSoccerXMLTeamID: String? = null,
        @SerializedName("dateBorn")
        val dateBorn: String? = null,
        @SerializedName("dateSigned")
        val dateSigned: String? = null,
        @SerializedName("strSigning")
        val strSigning: String? = null,
        @SerializedName("strWage")
        val strWage: String? = null,
        @SerializedName("strBirthLocation")
        val strBirthLocation: String? = null,
        @SerializedName("strDescriptionEN")
        val strDescriptionEN: String? = null,
        @SerializedName("strGender")
        val strGender: String? = null,
        @SerializedName("strPosition")
        val strPosition: String? = null,
        @SerializedName("strCollege")
        val strCollege: String? = null,
        @SerializedName("strFacebook")
        val strFacebook: String? = null,
        @SerializedName("strWebsite")
        val strWebsite: String? = null,
        @SerializedName("strTwitter")
        val strTwitter: String? = null,
        @SerializedName("strInstagram")
        val strInstagram: String? = null,
        @SerializedName("strYoutube")
        val strYoutube: String? = null,
        @SerializedName("strHeight")
        val strHeight: String? = null,
        @SerializedName("strWeight")
        val strWeight: String? = null,
        @SerializedName("intLoved")
        val intLoved: String? = null,
        @SerializedName("strThumb")
        val strThumb: String? = null,
        @SerializedName("strCutout")
        val strCutout: String? = null,
        @SerializedName("strBanner")
        val strBanner: String? = null,
        @SerializedName("strFanart1")
        val strFanart1: String? = null,
        @SerializedName("strFanart2")
        val strFanart2: String? = null,
        @SerializedName("strFanart3")
        val strFanart3: String? = null,
        @SerializedName("strFanart4")
        val strFanart4: String? = null,
        @SerializedName("strLocked")
        val strLocked: String? = null
)