package arie.footballmatch.sqlite

data class FavoriteEvents(val id: Long?,
                          val idEvent: String?,
                          val strDate: String?,
                          val strTime: String?,
                          val idHomeTeam: String?,
                          val strHomeTeam: String?,
                          val intHomeScore: String?,
                          val strHomeGoalDetails: String?,
                          val intHomeShots: String?,
                          val strHomeLineupGoalkeeper: String?,
                          val strHomeLineupDefense: String?,
                          val strHomeLineupMidfield: String?,
                          val idAwayTeam: String?,
                          val strAwayTeam: String?,
                          val intAwayScore: String?,
                          val strAwayGoalDetails: String?,
                          val intAwayShots: String?,
                          val strAwayLineupGoalkeeper: String?,
                          val strAwayLineupDefense: String?,
                          val strAwayLineupMidfield: String?) {
    companion object {
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE_EVENT"
        const val id: String = "ID_"
        const val idEvent: String = "idEvent"
        const val strDate: String = "strDate"
        const val strTime: String = "strTime"
        const val idHomeTeam: String = "idHomeTeam"
        const val strHomeTeam: String = "strHomeTeam"
        const val intHomeScore: String = "intHomeScore"
        const val strHomeGoalDetails: String = "strHomeGoalDetails"
        const val intHomeShots: String = "intHomeShots"
        const val strHomeLineupGoalkeeper: String = "strHomeLineupGoalkeeper"
        const val strHomeLineupDefense: String = "strHomeLineupDefense"
        const val strHomeLineupMidfield: String = "strHomeLineupMidfield"
        const val idAwayTeam: String = "idAwayTeam"
        const val strAwayTeam: String = "strAwayTeam"
        const val intAwayScore: String = "intAwayScore"
        const val strAwayGoalDetails: String = "strAwayGoalDetails"
        const val intAwayShots: String = "intAwayShots"
        const val strAwayLineupGoalkeeper: String = "strAwayLineupGoalkeeper"
        const val strAwayLineupDefense: String = "strAwayLineupDefense"
        const val strAwayLineupMidfield: String = "strAwayLineupMidfield"
    }
}