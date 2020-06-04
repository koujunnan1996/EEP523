package com.example.eep523_finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_yoga.*
import java.util.HashMap

class yoga : AppCompatActivity() {
    var finishedYoga:Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_yoga)

        val exit = findViewById(R.id.yoga_exist) as Button
        exit.setOnClickListener {
            val map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
            if(finishedYoga==true){
                map.put("Yoga",true)
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("where", "yoga");
            intent.putExtra("check", finishedYoga);
            intent.putExtra("map", map);
            startActivity(intent)
        }
    }


    fun Play(view: View){
        finishedYoga = true
        textView4!!.text = "DONE!!"
    }

    fun Exit(view: View) {
        //go back to the main page
    }
}
