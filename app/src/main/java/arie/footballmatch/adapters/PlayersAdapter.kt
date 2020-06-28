package arie.footballmatch.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import arie.footballmatch.DetailPlayer
import arie.footballmatch.R
import arie.footballmatch.connections.Player
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class PlayersAdapter (private val player: List<Player>)
    : RecyclerView.Adapter<PlayerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(PlayerUI().createView(AnkoContext.create(parent.context, parent)), parent.context)
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: PlayerViewHolder?, position: Int) {
        holder?.bindItem(player[position])
    }

}

class PlayerUI  : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(width = matchParent, height = wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.player_thumb
                }.lparams{
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.player_name
                    textSize = 16f
                }.lparams{
                    margin = dip(15)
                }

                textView {
                    id = R.id.player_position
                    textSize = 13f
                }.lparams{
                    width= matchParent
                    gravity=Gravity.RIGHT
                    margin = dip(15)
                }

            }
        }
    }
}
class PlayerViewHolder(view: View, private val ctx: Context) : RecyclerView.ViewHolder(view){
    private val playerThumb: ImageView = view.find(R.id.player_thumb)
    private val playerName: TextView = view.find(R.id.player_name)
    private val playerPosition: TextView = view.find(R.id.player_position)


    fun bindItem(player: Player) {
        Picasso.get().load(player.strThumb).into(playerThumb)
        playerName.text = player.strPlayer
        playerPosition.text = player.strPosition

        itemView.setOnClickListener{
            val intent = Intent(ctx, DetailPlayer::class.java)
            intent.putExtra("player_id", player.idPlayer)
            intent.putExtra("player_name", player.strPlayer)
            intent.putExtra("player_thumb", player.strThumb)
            intent.putExtra("player_position", player.strPosition)
            intent.putExtra("player_weight", player.strWeight)
            intent.putExtra("player_height", player.strHeight)
            intent.putExtra("player_description", player.strDescriptionEN)
            ContextCompat.startActivity(ctx, intent, bundleOf("player_id" to player.idPlayer))
        }
    }
}