package edu.uw.eep523.locationupdates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

class plati : AppCompatActivity() {
    var finishedPlati:Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
    }

    fun Play(view: View){
        finishedPlati = true
        textView4!!.text = "DONE!!"
    }

    fun Exit(view: View) {
        //go back to the main page
    }
}
