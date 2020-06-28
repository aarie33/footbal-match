package arie.footballmatch

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import arie.footballmatch.fragments.FavEventsFragment
import arie.footballmatch.fragments.FavTeamsFragment
import kotlinx.android.synthetic.main.activity_main.*

class FavActivity : AppCompatActivity() {
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fav)

        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.setupWithViewPager(container)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.favorites
    }

    override fun onResume() {
        super.onResume()
        navigation.selectedItemId = R.id.favorites
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when(position){
                0 -> {
                    val fav = FavEventsFragment()
                    return fav
                }
                1 -> {
                    val team = FavTeamsFragment()
                    return team
                }
            }
            return null
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            when(position){
                0 -> {
                    return "Schedules"
                }
                1 -> {
                    return "Teams"
                }
                else ->{
                    return "Schedule"
                }
            }
        }
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.events ->{
                var mainIntent = Intent(this, MainActivity::class.java)
                mainIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(mainIntent)
                finish()
            }
            R.id.teams ->{
                var teamIntent = Intent(this, TeamsActivity::class.java)
                teamIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(teamIntent)
                finish()
            }
            R.id.favorites ->{
                false
            }
        }
        true
    }
}
