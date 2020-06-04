package com.example.eep523_finalproject

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.HashMap

class skipping : AppCompatActivity(), SensorEventListener {


    private lateinit var mSensorManager: SensorManager
    private lateinit var mSensor: Sensor
    private lateinit var et: EditText


    private lateinit var times: TextView
    //lateinit var start: Button

    //    private var accelerationThreshold = 27F
//    private val SHAKE_SLOP_TIME_MS = 500
    private var shaketime = 0L
    private var work = false
    private var pushup = 0
    private var isFinished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skipping)
        //view.setBackgroundColor(Color.CYAN)


        times = findViewById(R.id.times)
        et = findViewById(R.id.editText)

        shaketime = System.currentTimeMillis()
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager


        mSensor = if (mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
            mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        } else {
            // Sorry, there are no accelerometers on your device.
            null!!
        }


        mSensorManager.registerListener(this, mSensor, 40000)

        val exit = findViewById(R.id.rope_exist) as Button
        exit.setOnClickListener {
            val map = intent.getSerializableExtra("map") as HashMap<String, Boolean>
            if(isFinished==true){
                map.put("Rope Skipping",true)
            }
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("where", "rope");
            intent.putExtra("check", isFinished);
            intent.putExtra("map", map);
            startActivity(intent)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }


    override fun onSensorChanged(event: SensorEvent) {
        // In this example, alpha is calculated as t / (t + dT),
        // where t is the low-pass filter's time-constant and
        // dT is the event delivery rate.
        if (event.sensor.type != Sensor.TYPE_ACCELEROMETER)
            return

        /*
             * It is not necessary to get accelerometer events at a very high
             * rate, by using a slower rate (SENSOR_DELAY_UI), we get an
             * automatic low-pass filter, which "extracts" the gravity component
             * of the acceleration. As an added benefit, we use less power and
             * CPU resources.
       */
//        val ax = event.values[0]
//        val ay = event.values[1]
//        val az = event.values[2]


        if (work) {
            if (event.values[1] >= 15f) {
                val now = System.currentTimeMillis()
                if (now - shaketime < 600) {
                    return
                }
                shaketime = now

                if (et.text.toString() == "") {
                    Toast.makeText(applicationContext, "Please input times", Toast.LENGTH_SHORT).show()
                    return
                }
                //begin.text = "Target:"+et.getText()
//                    val bg = "Target:"+et.getText()
//                    begin.text = bg
//                    begin.setTextColor(Color.parseColor("#0000ff"))

                pushup++
                val temp = "push-up numbers:$pushup"
                times.text = temp

                times.setTextColor(Color.parseColor("#0000ff"))

                var target = et.text.toString().toInt()
                if (pushup == target) {
                    times.text = "Done!!!"
                    times.setTextColor(Color.parseColor("#0000ff"))
                    work = false
                    isFinished = true

                }
            }
        }
    }

    override fun onResume() {
        Log.d("tag","onResume")
        isFinished = false
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
        isFinished = false
        mSensorManager.unregisterListener(this)
        Log.d("tag","onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        isFinished = false
        mSensorManager.unregisterListener(this)
    }

    fun start(View: View) {
        work = true;
        times.text = "You can start!"
        times.setTextColor(Color.parseColor("#0000ff"))
    }

    fun exit(View: View) {

    }

}
