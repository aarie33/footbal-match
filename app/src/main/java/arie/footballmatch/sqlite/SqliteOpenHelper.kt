package arie.footballmatch.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class SqliteOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 3) {
    companion object {
        private  var instance: SqliteOpenHelper? = null

        @Synchronized
        fun getIntance(ctx: Context): SqliteOpenHelper{
            if (instance == null) {
                instance = SqliteOpenHelper(ctx.applicationContext)
            }
            return instance as SqliteOpenHelper
        }
    }
    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteEvents.TABLE_FAVORITE, true,
                FavoriteEvents.id to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteEvents.idEvent to TEXT + UNIQUE,
                FavoriteEvents.strDate to TEXT,
                FavoriteEvents.strTime to TEXT,
                FavoriteEvents.idHomeTeam to TEXT,
                FavoriteEvents.strHomeTeam to TEXT,
                FavoriteEvents.intHomeScore to TEXT,
                FavoriteEvents.strHomeGoalDetails to TEXT,
                FavoriteEvents.intHomeShots to TEXT,
                FavoriteEvents.strHomeLineupGoalkeeper to TEXT,
                FavoriteEvents.strHomeLineupDefense to TEXT,
                FavoriteEvents.strHomeLineupMidfield to TEXT,
                FavoriteEvents.idAwayTeam to TEXT,
                FavoriteEvents.strAwayTeam to TEXT,
                FavoriteEvents.intAwayScore to TEXT,
                FavoriteEvents.strAwayGoalDetails to TEXT,
                FavoriteEvents.intAwayShots to TEXT,
                FavoriteEvents.strAwayLineupGoalkeeper to TEXT,
                FavoriteEvents.strAwayLineupDefense to TEXT,
                FavoriteEvents.strAwayLineupMidfield to TEXT)

        db.createTable(FavoriteTeams.TABLE_FAVORITE, true,
                FavoriteTeams.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteTeams.TEAM_ID to TEXT + UNIQUE,
                FavoriteTeams.TEAM_NAME to TEXT,
                FavoriteTeams.TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.dropTable(FavoriteEvents.TABLE_FAVORITE, true)
        db.dropTable(FavoriteTeams.TABLE_FAVORITE, true)
    }
}
val Context.database: SqliteOpenHelper
    get() = SqliteOpenHelper.getIntance(applicationContext)