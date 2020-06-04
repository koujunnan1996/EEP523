package com.example.eep523_finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import java.time.LocalDateTime


class daily : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    NavigationView.OnNavigationItemSelectedListener {

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)
        var bottomNavigationView =
            findViewById<View>(R.id.navigation) as BottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener(this)

        val map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
        val map_copy : HashMap<String,Boolean> = HashMap<String, Boolean> ();

        for ((key, value) in map) {
            map_copy.put(key,value);
        }

        val running1 = findViewById(R.id.button_IndoorRunning) as Button
        running1.setOnClickListener {
            // your code to perform when the user clicks on the button
            if(running1.getText().toString() == "Running(treadmill)") {
                Toast.makeText(this@daily, "Running(theadmill) added.", Toast.LENGTH_SHORT).show()
                running1.setText("Running(treadmill)*");
                running1.setBackgroundColor(R.color.icon_navi);
                map.put("Running(treadmill)",false);
            }else  if(running1.getText().toString() == "Running(treadmill)*") {
                Toast.makeText(this@daily, "Running(theadmill) removed.", Toast.LENGTH_SHORT).show()
                running1.setText("Running(treadmill)");
                running1.setBackgroundColor(Color.WHITE);
                map.remove("Running(treadmill)");
            }
        }

        val running2 = findViewById(R.id.button_Running) as Button
        running2.text = LocalDateTime.now().toString().subSequence(0,10)
        //running2.setBackgroundColor(R.color.colorAccent)

        val pullup = findViewById(R.id.button_Pullup) as Button
        pullup.setOnClickListener {
            // your code to perform when the user clicks on the button
            if(pullup.getText().toString() == "Pull-Up") {
                Toast.makeText(this@daily, "Pull-Up added.", Toast.LENGTH_SHORT).show()
                pullup.setText("Pull-Up*");
                pullup.setBackgroundColor(R.color.icon_navi);
                map.put("Pull-Up",false);
            }else  if(pullup.getText().toString() == "Pull-Up*") {
                Toast.makeText(this@daily, "Pull-Up removed.", Toast.LENGTH_SHORT).show()
                pullup.setText("Pull-Up");
                pullup.setBackgroundColor(Color.WHITE);
                map.remove("Pull-Up");
            }
        }

        val rope = findViewById(R.id.button_Rope) as Button
        rope.setOnClickListener {
            // your code to perform when the user clicks on the button
            if(rope.getText().toString() == "Rope Skipping") {
                Toast.makeText(this@daily, "Rope Skipping added.", Toast.LENGTH_SHORT).show()
                rope.setText("Rope Skipping*");
                rope.setBackgroundColor(R.color.icon_navi);
                map.put("Rope Skipping",false);
            }else  if(rope.getText().toString() == "Rope Skipping*") {
                Toast.makeText(this@daily, "Rope Skipping removed.", Toast.LENGTH_SHORT).show()
                rope.setText("Rope Skipping");
                rope.setBackgroundColor(Color.WHITE);
                map.remove("Rope Skipping");
            }
        }

        val ball = findViewById(R.id.button_Ball) as Button
        ball.setOnClickListener {
            // your code to perform when the user clicks on the button
            if(ball.getText().toString() == "Ball Games") {
                Toast.makeText(this@daily, "Ball Games added.", Toast.LENGTH_SHORT).show()
                ball.setText("Ball Games*");
                ball.setBackgroundColor(R.color.icon_navi);
                map.put("Ball Games",false);
            }else  if(ball.getText().toString() == "Ball Games*") {
                Toast.makeText(this@daily, "Ball Games removed.", Toast.LENGTH_SHORT).show()
                ball.setText("Ball Games");
                ball.setBackgroundColor(Color.WHITE);
                map.remove("Ball Games");
            }
        }

        val pushup = findViewById(R.id.button_Pushup) as Button
        pushup.setOnClickListener {
            // your code to perform when the user clicks on the button
            if(pushup.getText().toString() == "Push-Up") {
                Toast.makeText(this@daily, "Push-Up added.", Toast.LENGTH_SHORT).show()
                pushup.setText("Push-Up*");
                pushup.setBackgroundColor(R.color.icon_navi);
                map.put("Push-Up",false);
            }else  if(pushup.getText().toString() == "Push-Up*") {
                Toast.makeText(this@daily, "Push-Up removed.", Toast.LENGTH_SHORT).show()
                pushup.setText("Push-Up");
                pushup.setBackgroundColor(Color.WHITE);
                map.remove("Push-Up");
            }
        }

        val swim = findViewById(R.id.button_Swimming) as Button
        swim.setOnClickListener {
            // your code to perform when the user clicks on the button
            if(swim.getText().toString() == "Swimming") {
                Toast.makeText(this@daily, "Swimming added.", Toast.LENGTH_SHORT).show()
                swim.setText("Swimming*");
                swim.setBackgroundColor(R.color.icon_navi);
                map.put("Swimming",false);
            }else  if(swim.getText().toString() == "Swimming*") {
                Toast.makeText(this@daily, "Swimming removed.", Toast.LENGTH_SHORT).show()
                swim.setText("Swimming");
                swim.setBackgroundColor(Color.WHITE);
                map.remove("Swimming");
            }
        }

        val yoga = findViewById(R.id.button_Yoga) as Button
        yoga.setOnClickListener {
            // your code to perform when the user clicks on the button
            if(yoga.getText().toString() == "Yoga") {
                Toast.makeText(this@daily, "Yoga added.", Toast.LENGTH_SHORT).show()
                yoga.setText("Yoga*");
                yoga.setBackgroundColor(R.color.icon_navi);
                map.put("Yoga",false);
            }else  if(yoga.getText().toString() == "Yoga*") {
                Toast.makeText(this@daily, "Yoga removed.", Toast.LENGTH_SHORT).show()
                yoga.setText("Yoga");
                yoga.setBackgroundColor(Color.WHITE);
                map.remove("Yoga");
            }
        }

        val pilates = findViewById(R.id.button_Pilates) as Button
        pilates.setOnClickListener {
            // your code to perform when the user clicks on the button
            if(pilates.getText().toString() == "Pilates") {
                Toast.makeText(this@daily, "Pilates added.", Toast.LENGTH_SHORT).show()
                pilates.setText("Pilates*");
                pilates.setBackgroundColor(R.color.icon_navi);
                map.put("Pilates",false);
            }else  if(pilates.getText().toString() == "Pilates*") {
                Toast.makeText(this@daily, "Pilates removed.", Toast.LENGTH_SHORT).show()
                pilates.setText("Pilates");
                pilates.setBackgroundColor(Color.WHITE);
                map.remove("Pilates");
            }
        }

        val exit = findViewById(R.id.button_Exit) as Button
        exit.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("where", "exit");
            intent.putExtra("map",map_copy)
            startActivity(intent)
        }

        val confirm = findViewById(R.id.button_Confirm) as Button
        confirm.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("where", "confirm");
            intent.putExtra("map",map);
            startActivity(intent)
        }

        for ((key, value) in map) {
            if(key == running1.text){
                running1.setText("Running(treadmill)*");
                running1.setBackgroundColor(R.color.icon_navi);
            }else if(key == running2.text){
                running2.setText("Running*");
                running2.setBackgroundColor(R.color.icon_navi);
            }else if(key == pullup.text){
                pullup.setText("Pull-Up*");
                pullup.setBackgroundColor(R.color.icon_navi);
            }else if(key == rope.text){
                rope.setText("Rope Skipping*");
                rope.setBackgroundColor(R.color.icon_navi);
            }else if(key==ball.text){
                ball.setText("Ball Games*");
                ball.setBackgroundColor(R.color.icon_navi);
            }else if(key == pushup.text){
                pushup.setText("Push-Up*");
                pushup.setBackgroundColor(R.color.icon_navi);
            }else if(key == swim.text){
                swim.setText("Swimming*");
                swim.setBackgroundColor(R.color.icon_navi);
            }else if(key == yoga.text){
                yoga.setText("Yoga*");
                yoga.setBackgroundColor(R.color.icon_navi);
            }else if(key == pilates.text){
                pilates.setText("Pilates*");
                pilates.setBackgroundColor(R.color.icon_navi);
            }
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
           /* R.id.action_today -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }*/
        }

        return false
    }
}


