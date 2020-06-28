package arie.footballmatch

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import arie.footballmatch.R.id.search_item
import arie.footballmatch.fragments.LastFragment
import arie.footballmatch.fragments.NextFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.setupWithViewPager(container)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation.selectedItemId = R.id.events

        if (ContextCompat.checkSelfPermission(this,
                        Manifest.permission.WRITE_CALENDAR)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.READ_CONTACTS)) {

                Toast.makeText(this, "Izinkan membaca kalender", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(this,
                        arrayOf(Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR), 111)
            }
        }
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment? {
            when(position){
                0 -> {
                    val last = LastFragment()
                    return last
                }
                1 -> {
                    val next = NextFragment()
                    return next
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
                    return "Last Match"
                }
                1 -> {
                    return "Next Match"
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
                false
            }
            R.id.teams ->{
                var teamIntent = Intent(this, TeamsActivity::class.java)
                teamIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(teamIntent)
                finish()
            }
            R.id.favorites ->{
                var favIntent = Intent(this, FavActivity::class.java)
                favIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                startActivity(favIntent)
                finish()
            }
        }
        true
    }

    override fun onResume() {
        super.onResume()
        navigation.selectedItemId = R.id.events
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search_icon, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            search_item->{
                startActivity(Intent(applicationContext, CariEventAct::class.java))
                return false
            }
            else->{
                return super.onOptionsItemSelected(item)
            }
        }
    }
}

