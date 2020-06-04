package com.example.eep523_finalproject
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_ball.*
import java.util.HashMap

class ball : AppCompatActivity() {
    var finishedBall:Boolean? = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ball)

        val exit = findViewById(R.id.button4) as Button
        exit.setOnClickListener {
            val map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
            if(finishedBall==true){
                map.put("Ball Games",true)
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("where", "ball");
            intent.putExtra("check", finishedBall);
            intent.putExtra("map", map);
            startActivity(intent)
        }
    }

    fun Play(view: View){
        finishedBall = true
        textView4!!.text = "DONE!!"
    }

    fun Exit(view:View) {
        //go back to the main page
    }
}

