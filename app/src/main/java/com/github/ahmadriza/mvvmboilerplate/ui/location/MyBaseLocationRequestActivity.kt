package com.github.ahmadriza.mvvmboilerplate.ui.location

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import org.jetbrains.anko.alert
import org.jetbrains.anko.toast
import timber.log.Timber

/**
 * Created by riza@deliv.co.id on 8/15/20.
 */

abstract class MyBaseLocationRequestActivity : AppCompatActivity() {

    protected var configIsForce = false
    protected var configIsRepeat = false
    protected var configInterval = 1000L
    protected var configMinDisplacement = 5f
    protected var configMinInterval = 200L


    companion object {
        const val MY_PERMISSIONS_REQUEST = 10
        const val REQUEST_CHECK_SETTINGS = 20
    }

    private val mLocationRequest: LocationRequest by lazy {
        LocationRequest().apply {
            interval = configInterval
            fastestInterval = configMinInterval
            smallestDisplacement = configMinDisplacement
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
    }

    private val fusedLocationProviderClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    private val locationCallback by lazy {
        object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult?.locations?.lastOrNull()?.let {
                    onLocationRetrieved(it)
                    Timber.d("Location retrieved lat = ${it.latitude} lon = ${it.longitude}")
                    if (!configIsRepeat) removeLocationUpdate()
                }
            }
        }
    }

    abstract fun onLocationRetrieved(location: Location)

    protected fun getLocation() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED
            ) {
                checkForGps()
            } else {
                if (configIsForce) {

                    alert {
                        message = "Ijin lokasi diperlukan untuk mengakses fitur ini"
                        positiveButton("Ijinkan") {
                            ActivityCompat.requestPermissions(
                                this@MyBaseLocationRequestActivity,
                                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                                MY_PERMISSIONS_REQUEST
                            )
                            it.dismiss()
                        }
                        negativeButton("Batal") {
                            it.dismiss()
                        }
                    }.show()

                } else {
                    toast("Lokasi tidak diijinkan")
                }
            }

        } else {
            checkForGps()
        }
    }

    private fun checkForGps() {

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(mLocationRequest)
//        builder.setAlwaysShow(true)

        val client: SettingsClient = LocationServices.getSettingsClient(this)
        val task: Task<LocationSettingsResponse> = client.checkLocationSettings(builder.build())

        task.addOnSuccessListener {
            updateLocation()
        }

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                // Location settings are not satisfied, but this can be fixed
                // by showing the user a dialog.
                try {
                    // Show the dialog by calling startResolutionForResult(),
                    // and check the result in onActivityResult().
                    exception.startResolutionForResult(
                        this@MyBaseLocationRequestActivity,
                        REQUEST_CHECK_SETTINGS
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                    // Ignore the error.
                }
            }

        }
    }

    private fun updateLocation() {
        try {

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                fusedLocationProviderClient.requestLocationUpdates(
                    mLocationRequest,
                    locationCallback,
                    null
                )
            }

        } catch (ex: SecurityException) {
            ex.printStackTrace()
        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {


        when (requestCode) {

            MY_PERMISSIONS_REQUEST -> {
                getLocation()
            }


        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CHECK_SETTINGS -> {
                when (resultCode) {
                    Activity.RESULT_OK -> updateLocation()
                    Activity.RESULT_CANCELED -> {
                        if (configIsForce) {

                            alert {
                                message = "Anda harus menyalakan GPS untuk menikmati fitur ini"
                                positiveButton("Nyalakan GPS") {
                                    getLocation()
                                    it.dismiss()
                                }
                                negativeButton("Batal") {
                                    it.dismiss()
                                }
                            }.show()

                        } else {
                            toast("GPS tidak diaktifkan")
                        }
                    }
                }
            }
        }

    }


    protected fun removeLocationUpdate() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
    }

    /* override fun onPause() {
         removeLocationUpdate()
         super.onPause()
     }*/

    override fun onDestroy() {
        removeLocationUpdate()

        super.onDestroy()
    }
}