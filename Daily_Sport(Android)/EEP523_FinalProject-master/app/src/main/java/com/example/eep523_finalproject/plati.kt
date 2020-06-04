package com.example.eep523_finalproject
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_plati.*
import java.util.HashMap

class plati : AppCompatActivity() {
    var finishedPlati:Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plati)

        val exit = findViewById(R.id.p_exist) as Button
        exit.setOnClickListener {
            val map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
            if(finishedPlati==true){
                map.put("Pilates",true)
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("where", "plati");
            intent.putExtra("check", finishedPlati);
            intent.putExtra("map", map);
            startActivity(intent)
        }
    }

    fun Play(view: View){
        finishedPlati = true
        textView4!!.text = "DONE!!"
    }

    fun Exit(view: View) {
        //go back to the main page
    }
}

