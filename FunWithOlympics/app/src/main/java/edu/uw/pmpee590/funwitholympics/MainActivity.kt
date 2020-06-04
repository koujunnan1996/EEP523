package edu.uw.pmpee590.funwitholympics


import android.media.MediaPlayer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log



class MainActivity : AppCompatActivity() {

    private  var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    public override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")

    }

    public override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
        mediaPlayer = MediaPlayer.create(this, R.raw.olympic)
        mediaPlayer?.start();
    }

    public override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
        mediaPlayer?.stop()
    }

    public override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    public override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG", "onDestroy() called")
    }


    companion object {
        private val TAG = "TAG"
    }
}

