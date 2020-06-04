package com.example.eep523_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_swim.*
import java.util.HashMap

class swim : AppCompatActivity() {
    var finishedSwim:Boolean? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swim)

        val exit = findViewById(R.id.swim_exist) as Button
        exit.setOnClickListener {
            val map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
            if(finishedSwim==true){
                map.put("Swimming",true)
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("where", "swim");
            intent.putExtra("check", finishedSwim);
            intent.putExtra("map", map);
            startActivity(intent)
        }
    }

    fun Play(view: View){
        finishedSwim = true
        textView4!!.text = "DONE!!"
    }

    fun Exit(view: View) {
        //go back to the main page
    }
}
