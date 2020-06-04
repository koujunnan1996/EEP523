package com.example.eep523_finalproject

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView



class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener,
    NavigationView.OnNavigationItemSelectedListener {
    var bottomNavigationView: BottomNavigationView? = null

    var toolbar: Toolbar? = null
    var toggle: ActionBarDrawerToggle? = null
    var map : HashMap<String, Boolean> = HashMap<String, Boolean> ()

    @SuppressLint("RestrictedApi", "ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var drawerLayout: DrawerLayout? = findViewById(R.id.drawer)
        var navigationView: NavigationView? = findViewById(R.id.navigationView)
        bottomNavigationView =
            findViewById<View>(R.id.navigation) as BottomNavigationView
        bottomNavigationView!!.setOnNavigationItemSelectedListener(this)

        toolbar = findViewById(R.id.toolbar)
        //setSupportActionBar(toolbar)
        supportActionBar!!.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.drawerOpen,
            R.string.drawerClose
        )
        if (drawerLayout != null) {
            drawerLayout.addDrawerListener(toggle!!)
        };
        toggle!!.syncState()
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this)
        }

        val add_Target = findViewById(R.id.addTarget) as Button
        // set on-click listener
        add_Target.setOnClickListener {
            val intent = Intent(this, daily::class.java)
            intent.putExtra("map", map);
            startActivity(intent)
        }

        val bundle :Bundle ?=intent.extras
        if (bundle!=null){
            val where = intent.getStringExtra("where")
            if(where=="confirm"){
                map=intent.getSerializableExtra("map") as HashMap<String, Boolean>
                for ((key, value) in map) {
                    Log.e("map", "$key = $value")

                    // Create a LinearLayout element
                    val linearLayout = findViewById(R.id.fragment_container) as LinearLayout
                    linearLayout.orientation = LinearLayout.VERTICAL

                    val button = Button(this)
                    var button_text = key
                    if (value == false){
                        button_text += "  Uncompleted"
                    }else{
                        button_text += "   Done!"
                        button.setBackgroundColor(R.color.c1)
                    }
                    button.text = button_text

                    //--------------------------------------------------------------------------
                    if(key == "Running(treadmill)"){
                        //Log.e("error","line 127")
                        button.setOnClickListener {
                            val intent = Intent(this, running1::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Ball Games"){
                        button.setOnClickListener {
                            val intent = Intent(this, ball::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Yoga"){
                        button.setOnClickListener {
                            val intent = Intent(this, yoga::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }
                    else if(key == "Swimming"){
                        button.setOnClickListener {
                            val intent = Intent(this, swim::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    } else if(key == "Pull-Up"){
                        button.setOnClickListener {
                            val intent = Intent(this, pullup::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Push-Up"){
                        button.setOnClickListener {
                            val intent = Intent(this, pushup::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Rope Skipping"){
                        button.setOnClickListener {
                            val intent = Intent(this, skipping::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Pilates"){
                        button.setOnClickListener {
                            val intent = Intent(this, plati::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }
                    //--------------------------------------------------------------------------

                    button.isAllCaps=false
                    linearLayout.addView(button)
                    //scrollView.addView(linearLayout)
                }
                if(map.size == 9 ){
                    val linearLayout = findViewById(R.id.fragment_container) as LinearLayout
                    linearLayout.orientation = LinearLayout.VERTICAL
                    val button = Button(this)
                    var button_text = ""
                    button.setBackgroundColor(Color.WHITE)
                    val button2 = Button(this)
                    var button2_text = ""
                    button2.setBackgroundColor(Color.WHITE)
                    linearLayout.addView(button)
                    linearLayout.addView(button2)

                }
            }else if(where=="exit"){
                Log.e("where","line95")
                map=intent.getSerializableExtra("map") as HashMap<String, Boolean>
                for ((key, value) in map) {
                    Log.e("map", "$key = $value")

                    // Create a LinearLayout element
                    val linearLayout = findViewById(R.id.fragment_container) as LinearLayout
                    linearLayout.orientation = LinearLayout.VERTICAL

                    val button = Button(this)
                    var button_text = key
                    if (value == false){
                        button_text += "  Uncompleted"
                    }else{
                        button_text += "   Done!"
                        button.setBackgroundColor(R.color.c1)
                    }
                    button.text = button_text
                    //--------------------------------------------------------------------------
                    if(key == "Running(treadmill)"){
                        //Log.e("error","line 127")
                        button.setOnClickListener {
                            val intent = Intent(this, running1::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Ball Games"){
                        button.setOnClickListener {
                            val intent = Intent(this, ball::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Yoga"){
                        button.setOnClickListener {
                            val intent = Intent(this, yoga::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }
                    else if(key == "Swimming"){
                        button.setOnClickListener {
                            val intent = Intent(this, swim::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    } else if(key == "Pull-Up"){
                        button.setOnClickListener {
                            val intent = Intent(this, pullup::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Push-Up"){
                        button.setOnClickListener {
                            val intent = Intent(this, pushup::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Rope Skipping"){
                        button.setOnClickListener {
                            val intent = Intent(this, skipping::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }else if(key == "Pilates"){
                        button.setOnClickListener {
                            val intent = Intent(this, plati::class.java)
                            intent.putExtra("map", map);
                            startActivity(intent)
                        }
                    }
                    //--------------------------------------------------------------------------
                    button.isAllCaps=false
                    linearLayout.addView(button)
                }
                if(map.size== 9 ){
                    val linearLayout = findViewById(R.id.fragment_container) as LinearLayout
                    linearLayout.orientation = LinearLayout.VERTICAL
                    val button = Button(this)
                    var button_text = ""
                    button.setBackgroundColor(Color.WHITE)
                    val button2 = Button(this)
                    var button2_text = ""
                    button2.setBackgroundColor(Color.WHITE)
                    linearLayout.addView(button)
                    linearLayout.addView(button2)
                }
            }else if(where=="Running(treadmill)"){
                //val finished = intent.getStringExtra("check")
                map=intent.getSerializableExtra("map") as HashMap<String, Boolean>
                upDateButtons()
            }else if(where=="ball") {
                //val finished = intent.getStringExtra("check")
                map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
                upDateButtons()
            }else if(where=="yoga") {
                map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
                upDateButtons()
            }else if(where=="swim") {
                map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
                upDateButtons()
            }else if(where=="pullup") {
                map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
                upDateButtons()
            }else if(where=="pushup") {
                map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
                upDateButtons()
            }else if(where=="rope") {
                map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
                upDateButtons()
            }else if(where=="plati") {
                map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
                upDateButtons()
            }


        }
    }

    @SuppressLint("ResourceAsColor")
    fun upDateButtons(){
        for ((key, value) in map) {
            // Create a LinearLayout element
            val linearLayout = findViewById(R.id.fragment_container) as LinearLayout
            linearLayout.orientation = LinearLayout.VERTICAL

            val button = Button(this)
            var button_text = key
            if (value == false){
                button_text += "  Uncompleted"
                if(key == "Running(treadmill)"){
                    //Log.e("error","line 127")
                    button.setOnClickListener {
                        val intent = Intent(this, running1::class.java)
                        intent.putExtra("map", map);
                        startActivity(intent)
                    }
                }else if(key == "Ball Games"){
                    button.setOnClickListener {
                        val intent = Intent(this, ball::class.java)
                        intent.putExtra("map", map);
                        startActivity(intent)
                    }
                }else if(key == "Yoga"){
                    button.setOnClickListener {
                        val intent = Intent(this, yoga::class.java)
                        intent.putExtra("map", map);
                        startActivity(intent)
                    }
                }
                else if(key == "Swimming"){
                    button.setOnClickListener {
                        val intent = Intent(this, swim::class.java)
                        intent.putExtra("map", map);
                        startActivity(intent)
                    }
                }else if(key == "Pull-Up"){
                    button.setOnClickListener {
                        val intent = Intent(this, pullup::class.java)
                        intent.putExtra("map", map);
                        startActivity(intent)
                    }
                }else if(key == "Push-Up"){
                    button.setOnClickListener {
                        val intent = Intent(this, pushup::class.java)
                        intent.putExtra("map", map);
                        startActivity(intent)
                    }
                }else if(key == "Rope Skipping"){
                    button.setOnClickListener {
                        val intent = Intent(this, skipping::class.java)
                        intent.putExtra("map", map);
                        startActivity(intent)
                    }
                }else if(key == "Pilates"){
                    button.setOnClickListener {
                        val intent = Intent(this, plati::class.java)
                        intent.putExtra("map", map);
                        startActivity(intent)
                    }
                }
            }else{
                button_text += "   Done!"
                button.setBackgroundColor(R.color.c1)
            }
            button.text = button_text
            button.isAllCaps=false
            linearLayout.addView(button)
        }
        if(map.size== 9 ){
            val linearLayout = findViewById(R.id.fragment_container) as LinearLayout
            linearLayout.orientation = LinearLayout.VERTICAL
            val button = Button(this)
            var button_text = ""
            button.setBackgroundColor(Color.WHITE)
            val button2 = Button(this)
            var button2_text = ""
            button2.setBackgroundColor(Color.WHITE)
            linearLayout.addView(button)
            linearLayout.addView(button2)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_daily -> {
                val intent = Intent(this, daily::class.java)
                intent.putExtra("map", map);
                startActivity(intent)
                return true
            }
            R.id.Kou -> {
                val intent = Intent(this, BLEConection::class.java)
                //intent.putExtra("map", map);
                startActivity(intent)
                return true
            }

        }

        return false
    }
}


