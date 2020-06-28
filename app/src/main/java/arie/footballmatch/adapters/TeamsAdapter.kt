package arie.footballmatch.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import arie.footballmatch.DetailTeamAct
import arie.footballmatch.R
import arie.footballmatch.R.id.team_badge
import arie.footballmatch.R.id.team_name
import arie.footballmatch.connections.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class TeamsAdapter (private val teams: List<Team>)
    : RecyclerView.Adapter<TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)), parent.context)
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder?, position: Int) {
        holder?.bindItem(teams[position])
    }

}

class TeamUI  : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.team_badge
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.team_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

            }
        }
    }
}
class TeamViewHolder(view: View, private val ctx: Context) : RecyclerView.ViewHolder(view){
    private val teamBadge: ImageView = view.find(team_badge)
    private val teamName: TextView = view.find(team_name)

    fun bindItem(teams: Team) {
        Picasso.get().load(teams.strTeamBadge).into(teamBadge)
        teamName.text = teams.teamName

        itemView.setOnClickListener{
            val intent = Intent(ctx, DetailTeamAct::class.java)
            intent.putExtra("team_id", teams.teamId)
            intent.putExtra("team_name", teams.teamName)
            intent.putExtra("team_badge", teams.strTeamBadge)
            intent.putExtra("team_year", teams.teamFormedYear)
            intent.putExtra("team_stadium", teams.teamStadium)
            intent.putExtra("team_description", teams.teamDescription)
            ContextCompat.startActivity(ctx, intent, bundleOf("team_id" to teams.teamId))
        }
    }
}