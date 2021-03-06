package edu.uw.eep523.locationupdates

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_location.*
import java.text.DateFormat
import java.util.*
import kotlin.math.sqrt

/**
 * This sample allows the user to request location updates using the ACCESS_FINE_LOCATION setting as specified in AndroidManifest.xml
 */
class MainActivity : AppCompatActivity() {

    private var Input_num1: EditText? = null
    private var Input_num2: EditText? = null
    private var mFusedLocationClient: FusedLocationProviderClient? = null // Provides access to the Fused Location Provider API.
    private var mSettingsClient: SettingsClient? = null //  access to the Location Settings API.
    private var mLocationRequest: LocationRequest? = null //parameters for requests to the FusedLocationProviderApi.
    private var mLocationSettingsRequest: LocationSettingsRequest? = null
    private var mLocationCallback: LocationCallback? = null //Callback for Location events.
    private var mCurrentLocation: Location? = null // geographical location.
    private var mRequestingLocationUpdates: Boolean? = null //Tracks the status of the location updates request
    private var mLastUpdateTime: String? = null //Time when the location was updated
    private var x:Double? = 0.0
    private var y:Double? = 0.0
    private var result:Double? = 0.0
    private var meter = 0
    private var flag:Int? = 0
    private var pre_x:Double? = 0.0
    private var pre_y:Double? = 0.0
    private var diff_x:Double? = 0.0
    private var diff_y:Double? = 0.0
    private var count = 0
    private var pre_time = System.currentTimeMillis()

    private val mHandler: Handler = Handler()


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        mRequestingLocationUpdates = false
        mLastUpdateTime = ""
        Input_num1 = findViewById<View>(R.id.editText1) as EditText
        Input_num2 = findViewById<View>(R.id.editText2) as EditText

        // Update values using data stored in the Bundle.
        if (savedInstanceState != null) {
            if (savedInstanceState.keySet().contains(KEY_REQUESTING_LOCATION_UPDATES)) {
                mRequestingLocationUpdates = savedInstanceState.getBoolean(
                    KEY_REQUESTING_LOCATION_UPDATES)
            }
            if (savedInstanceState.keySet().contains(KEY_LOCATION)) {
                mCurrentLocation = savedInstanceState.getParcelable(KEY_LOCATION)
            }
            if (savedInstanceState.keySet().contains(KEY_LAST_UPDATED_TIME)) {
                mLastUpdateTime = savedInstanceState.getString(KEY_LAST_UPDATED_TIME)
            }
            updateUI()
            update()
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        mSettingsClient = LocationServices.getSettingsClient(this)

        // Start the process of building the LocationCallback, LocationRequest
        createLocationCallback()
        createLocationRequest()

        /**
         *Check  if a device has the needed location settings.
         */
        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        mLocationSettingsRequest = builder.build()

       // Log.e("where","90")
   }


    /**
     * When the ACCESS_FINE_LOCATION setting is specified, combined with a fast update
     * interval (5 seconds), the Fused Location Provider API returns location updates that are
     * accurate to within a few feet.
     */
    private fun createLocationRequest() {
        mLocationRequest = LocationRequest()

        // Sets the desired interval for active location updates. This interval is
        // inexact. You may not receive updates at all if no location sources are available, or
        // you may receive them slower than requested. You may also receive updates faster than
        // requested if other applications are requesting location at a faster interval.
        mLocationRequest!!.interval = UPDATE_INTERVAL_IN_MILLISECONDS

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest!!.fastestInterval = FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
        mLocationRequest!!.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    /**
     * Creates a callback for receiving location events.
     */
    private fun createLocationCallback() {
        mLocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                mCurrentLocation = locationResult.lastLocation
                mLastUpdateTime = DateFormat.getTimeInstance().format(Date())
                updateUI()            }
        }
    }

    /**
     * Handles the Start Updates button and requests start of location updates. Does nothing if
     * updates have already been requested.
     */
    fun startUpdatesButton(view: View?) {

        if (flag == 1){
            if (!mRequestingLocationUpdates!!) {
                mRequestingLocationUpdates = true
                updateUI()
                startLocationUpdates()
            }
        }

        else if (flag == 0) {
            //Log.e("where","142")

           // textView3!!.setText("Running")

            //Log.e("JUnnan","Junan Kou")
            startTimeUpdates()
        }
    }

    fun startTimeUpdates (){
        //Log.e("where","150")
        var set  = Input_num1!!.getText().toString().toInt()

        val th = Thread(object : Runnable {
            var set  = Input_num1!!.getText().toString().toInt()
            private val startTime = System.currentTimeMillis()
            var out : Boolean = false;
            override fun run() {
                while ( textView3.text != set.toString() ) {
                    runOnUiThread {
                        if(out==false) {
                            textView3.setText("" + (System.currentTimeMillis() - startTime) / 1000)
                        }
                        if(textView3.text == set.toString()){
                            textView3.setText("Done!!!")
                            out=true;
                        }
                    }
                    try {
                        if(out==true){

                            return
                        }
                        Thread.sleep(1000)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
            }
        })
        th.start()

       // Log.e("where",set.toString())
        //Log.e("where", textView3.text as String)
        //println(count)
        /* (count != set) {
            var cur_time = System.currentTimeMillis()
            if (cur_time - pre_time > 1000) {
                Log.e("start","start detect")
                pre_time = cur_time
                count++
                println(count)
                //textView3!!.setText("234")
            }
        }*/
    }

    private fun update() {
        if (mRequestingLocationUpdates!!) {
            start_updates_button!!.isEnabled = false
            stop_updates_button!!.isEnabled = true
        } else {
            start_updates_button!!.isEnabled = true
            stop_updates_button!!.isEnabled = false
        }
        textView3!!.text ="Time:$count s"
    }


    /**
     * Handles the Stop Updates button, and requests removal of location updates.
     */
    fun stopUpdatesButton(view: View?) {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        if (flag == 1){
            stopLocationUpdates()
        }
        else if (flag == 0){
            stopTimeUpdate()
        }
    }

    /**
     * Requests location updates from the FusedLocationApi. Note: we don't call this unless location
     * runtime permission has been granted.
     */
    private fun startLocationUpdates() {
        // Begin by checking if the device has the necessary location settings.
        mSettingsClient!!.checkLocationSettings(mLocationSettingsRequest)
            .addOnSuccessListener(this) {
                Log.i(TAG, "All location settings are ok.")
                mFusedLocationClient!!.requestLocationUpdates(mLocationRequest,
                    mLocationCallback, Looper.myLooper())
                updateUI()
            }
            .addOnFailureListener(this) {
                mRequestingLocationUpdates = false
                updateUI()
            }

    }

    fun stopTimeUpdate() {
        start_updates_button!!.isEnabled = true
        stop_updates_button!!.isEnabled = false
        textView3!!.text = "unfinished"
    }

    /**
     * Updates all UI fields.
     */
    private fun updateUI() {
        if (mRequestingLocationUpdates!!) {
            start_updates_button!!.isEnabled = false
            stop_updates_button!!.isEnabled = true
        } else {
            start_updates_button!!.isEnabled = true
            stop_updates_button!!.isEnabled = false
        }

        if (flag == 1){
            if (mCurrentLocation != null) {
                x = Math.abs(mCurrentLocation!!.latitude) * 10e7
                y = Math.abs(mCurrentLocation!!.longitude) * 10e7
                diff_x = x!! - pre_x!!
                diff_y = y!! - pre_y!!
                result = sqrt(diff_x!! * diff_x!! + diff_y!! * diff_y!!)
                println("$x")
                println("$y")
                if (result!! >= 1000.0) {
                    pre_x = x
                    pre_y = y
                    meter++
                    textView2!!.text = "Distance: $meter m"
                    //Log.e("start","start detect")
                }
                if (meter == Input_num2!!.getText().toString().toInt()){
                    textView2!!.text = "Done !!!!"
                    stopLocationUpdates()
                }
//            if (meter!! == Input_num2!!.getText().toString().toInt()) {
//
////            latitude_text!!.text =getString(R.string.latitude_label) + ": " +  mCurrentLocation!!.latitude
////            longitude_text!!.text = getString(R.string.latitude_label) + ": " +  mCurrentLocation!!.longitude
////            last_update_time_text!!.text = getString( R.string.last_update_time_label) + ": " + mLastUpdateTime
//            }
            }
        }

        else if (flag == 0) {

            while (count != Input_num1!!.getText().toString().toInt()) {

                var cur_time = System.currentTimeMillis()
                if (cur_time - pre_time > 1000) {

                    pre_time = cur_time
                    count++
                    //println(count)
                    textView3!!.setText("Time:$count")
                    //Log.e("start", "start detect")
                }
            }
            count = 0
            textView3!!.text = "Done!!!"

        }
    }


    fun timeMode(view: View){
        flag = 0
        textView3!!.text ="Set a time!"
    }

    fun distanceMode(view: View){
        flag = 1
        textView2!!.text = "Distance: $meter m"
    }
    /**
     * Removes location updates from the FusedLocationApi.
     */
    private fun stopLocationUpdates() {
        // It is a good practice to remove location requests when the activity is in a paused or
        // stopped state. Doing so helps battery performance and is especially
        // recommended in applications that request frequent location updates.
        mFusedLocationClient!!.removeLocationUpdates(mLocationCallback)
            .addOnCompleteListener(this) {
                mRequestingLocationUpdates = false
                updateUI()
            }
    }

    /**
     * Stores activity data in the Bundle.
     */
    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        savedInstanceState.putBoolean(KEY_REQUESTING_LOCATION_UPDATES, mRequestingLocationUpdates!!)
        savedInstanceState.putParcelable(KEY_LOCATION, mCurrentLocation)
        savedInstanceState.putString(KEY_LAST_UPDATED_TIME, mLastUpdateTime)
        super.onSaveInstanceState(savedInstanceState)
    }

    public override fun onResume() {
        super.onResume()
        if (mRequestingLocationUpdates!! && checkPermissions()) {
            startLocationUpdates()
        } else if (!checkPermissions()) {
            requestPermissions()
        }
    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CHECK_SETTINGS -> when (resultCode) {
                Activity.RESULT_OK -> {}
                Activity.RESULT_CANCELED -> {
                    mRequestingLocationUpdates = false
                    updateUI()
                }
            }
        }
    }



    /**
     * Shows a [Snackbar].
     *
     * @param mainTextStringId The id for the string resource for the Snackbar text.
     * @param actionStringId   The text of the action item.
     * @param listener         The listener associated with the Snackbar action.
     */
    private fun showSnackbar(mainTextStringId: Int, actionStringId: Int,
                             listener: View.OnClickListener) {
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(mainTextStringId),
            Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(actionStringId), listener).show()
    }

    /**
     * Return the current state of the permissions needed.
     */
    private fun checkPermissions(): Boolean {
        val permissionState = ActivityCompat.checkSelfPermission(this,
            Manifest.permission.ACCESS_FINE_LOCATION)
        return permissionState == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        val shouldProvideRationale = ActivityCompat.shouldShowRequestPermissionRationale(this,
            Manifest.permission.ACCESS_FINE_LOCATION)
            //This would happen if the user denied the request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            showSnackbar(R.string.permission_rationale,
                android.R.string.ok, View.OnClickListener { // Request permission
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_PERMISSIONS_REQUEST_CODE)
                })
        } else {
            // Request permission.
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_PERMISSIONS_REQUEST_CODE)
        }
    }

    /**
     * Callback received when a permissions request has been completed.
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        Log.i(TAG, "onRequestPermissionResult")
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (grantResults.size <= 0) {
                // If user interaction was interrupted, the permission request is cancelled and you
                // receive empty arrays.
                Log.i(TAG, "User interaction was cancelled.")
            } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (mRequestingLocationUpdates!!) {
                    Log.i(TAG, "Permission granted, updates requested, starting location updates")
                    startLocationUpdates()
                }
            } else {
                // Permission denied.
                // Notify the user via a SnackBar that they have rejected a core permission for the
                // app, which makes the Activity useless. In a real app, core permissions would
                // typically be best requested during a welcome-screen flow.
                showSnackbar(R.string.permission_denied_explanation,
                    R.string.settings, View.OnClickListener { // Build intent that displays the App settings screen.
                        val intent = Intent()
                        intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                        val uri = Uri.fromParts("package",
                            packageName, null)
                        intent.data = uri
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        startActivity(intent)
                    })
            }
        }
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
        private const val REQUEST_PERMISSIONS_REQUEST_CODE = 123
        private const val REQUEST_CHECK_SETTINGS = 0x1
        /**
         * The desired interval for location updates. Inexact. Updates may be more or less frequent.
         */
        private const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 1
        private const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = UPDATE_INTERVAL_IN_MILLISECONDS / 2

        private const val KEY_REQUESTING_LOCATION_UPDATES = "requesting-location-updates"
        private const val KEY_LOCATION = "location"
        private const val KEY_LAST_UPDATED_TIME = "last-updated-time"
    }
}