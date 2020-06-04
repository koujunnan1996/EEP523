package edu.uw.eep523.locationupdates

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity_ball : AppCompatActivity() {
    var finishedBall:Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    fun Play(view: View){
        finishedBall = true
        textView4!!.text = "DONE!!"
    }

    fun Exit(view:View) {
       //go back to the main page
    }
}
