package edu.uw.eep523.locationupdates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

class Main3Activity_swim : AppCompatActivity() {
    var finishedSwim:Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3_activity_swim)
    }

    fun Play(view: View){
        finishedSwim = true
        textView4!!.text = "DONE!!"
    }

    fun Exit(view: View) {
        //go back to the main page
    }
}
