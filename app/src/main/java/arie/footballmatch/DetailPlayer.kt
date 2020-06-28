package arie.footballmatch

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import android.view.View
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_player.*

class DetailPlayer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_player)
        val toolbar = findViewById<View>(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(intent.getStringExtra("player_name"))

        Picasso.get().load(intent.getStringExtra("player_thumb")).into(imgThumb)
        txtWeight.text = intent.getStringExtra("player_weight")
        txtHeight.text = intent.getStringExtra("player_height")
        txtPosition.text = intent.getStringExtra("player_position")
        txtDescription.text = intent.getStringExtra("player_description")
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home->{
                finish()
                return false
            }
            else->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
}
