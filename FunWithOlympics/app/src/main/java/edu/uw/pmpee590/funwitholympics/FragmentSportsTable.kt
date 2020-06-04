package edu.uw.pmpee590.funwitholympics


import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TableLayout
import android.widget.TableRow
import kotlinx.android.synthetic.main.fragment_fragment_sports_table.*


class FragmentSportsTable : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_sports_table, container, false)

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layout = activity!!.findViewById<TableLayout>(R.id.table_layout)

        //Attach an onClic listener to every ImageView in the TableLayout
        for (i in 0 until layout.childCount-1) {
            //Note that the -1 in the line above is to just add a listener to the first row
            val row = layout.getChildAt(i) as TableRow
            for (j in 0 until row.childCount) {
                val button = row.getChildAt(j) as ImageButton

                button.setOnClickListener(object : View.OnClickListener {
                    override fun onClick(v: View?) {
                        val mSportIndex = returnSportIndex(v!!)

                        val orientation = activity!!.resources.configuration.orientation
                        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            val frag =fragmentManager!!.findFragmentById(R.id.fragmentRight) as FragmentSportDetail
                            frag.updateSportDetail(mSportIndex)
                        }

                        else {


                            //Start SportDetail activity
                            startFragmentRight(mSportIndex)
                        }

                    }
                })

            }
        }

    }




    fun startFragmentRight(id:Int){
        val i = Intent(activity, SportDetailActivity::class.java)
        i.putExtra(KEY, id)
        startActivity(i)
        //this is a comment
    }

    fun returnSportIndex(view:View): Int {
        var mIndex = when (view) {
            artswimming -> 0
            beachvolley -> 1
            modernpentalon -> 2
            luge -> 3
            // Continue for the rest of sports
            // ......
            else
            -> 0 //default value
        }
        return mIndex

    }



    companion object {
        private val KEY = "thesport"
    }


}
