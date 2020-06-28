package arie.footballmatch.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import arie.footballmatch.R
import arie.footballmatch.sqlite.FavoriteTeams
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class FavTeamsAdapter(private val context: Context, private val favorite: List<FavoriteTeams>)
    :RecyclerView.Adapter<FavTeamsAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TeamUI().createView(AnkoContext.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = favorite.size

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.bindItem(favorite[position])
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

    inner class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        private val teamBadge: ImageView = itemView.find(R.id.team_badge)
        private val teamName: TextView = itemView.find(R.id.team_name)

        fun bindItem(teams: FavoriteTeams) {
            Picasso.get().load(teams.teamBadge).into(teamBadge)
            teamName.text = teams.teamName

            itemView.setOnClickListener {

            }
        }
    }
}