package arie.footballmatch.fragments


import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import org.jetbrains.anko.dip
import org.jetbrains.anko.leftPadding
import org.jetbrains.anko.rightPadding
import org.jetbrains.anko.topPadding

class OverviewTeamFragment : Fragment(){

    companion object {
        fun newInstance(description: String): OverviewTeamFragment{
            val fragment = OverviewTeamFragment()
            val args = Bundle()
            args.putString("description", description)
            fragment.setArguments(args)
            return fragment
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("Val ARGS", arguments.getString("description"))

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return TextView(activity).apply {
            setText(arguments.getString("description"))
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
        }
    }
}
