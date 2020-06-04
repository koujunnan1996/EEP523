package edu.uw.pmpee590.funwitholympics

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_fragment_sport_detail.*

class FragmentSportDetail : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_sport_detail, container, false)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        createSports()
        //Receive the Intent
        val intent = activity!!.intent
        val id = intent.getIntExtra(KEY, 0)
        updateSportDetail(id)

    }


    lateinit var mSportsContainer : Array<Sport>

    // Create the container of sports
    fun createSports(){
        mSportsContainer = arrayOf(Sport(R.drawable.artswimming, R.string.sport_name_artswimming, R.string.details_artswimming),
            Sport(R.drawable.beachvolley, R.string.sport_name_beachvolley, R.string.details_beachvolley),
            Sport(R.drawable.modernpentalon, R.string.sport_name_modernpentalon, R.string.details_modernpentalon),
            Sport(R.drawable.luge, R.string.sport_name_luge, R.string.details_luge))

    }




    fun updateSportDetail(index: Int) {
        sport_pic_detail.setImageResource(mSportsContainer[index].sportPict)
        sport_descript_detail.setText(mSportsContainer[index].sportDetail)
        sport_name_detail.setText(mSportsContainer[index].sportName)
    }

    companion object {
        private val KEY = "thesport"
    }

}
