package com.github.ahmadriza.mvvmboilerplate.ui.location

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.github.ahmadriza.mvvmboilerplate.R
import com.github.ahmadriza.mvvmboilerplate.utils.gone
import com.github.ahmadriza.mvvmboilerplate.utils.jump
import com.github.ahmadriza.mvvmboilerplate.utils.visible
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_location_picker.*
import org.jetbrains.anko.intentFor

/**
 * Created by riza@deliv.co.id on 6/15/20.
 */

class LocationPickerActivity : MyBaseLocationRequestActivity(), OnMapReadyCallback {

    companion object {
        private const val ZOOM = 16f

        const val EXTRA_LAT_LNG = "latlng"

        fun getIntent(context: Context, locationModel: LatLng?) =
            context.intentFor<LocationPickerActivity>(
                EXTRA_LAT_LNG to locationModel
            )
    }

    private var mMap: GoogleMap? = null
    private var mFusedLocationProviderClient: FusedLocationProviderClient? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_picker)
        (fragment_map as SupportMapFragment).getMapAsync(this)
        configIsForce = true
        configIsRepeat = false
    }

    //permission is always granted from home
    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            mMap?.isMyLocationEnabled = true
            mMap?.uiSettings?.isMyLocationButtonEnabled = false
        } else {
            getLocation()
        }


        mMap?.setOnCameraMoveStartedListener {
            shadow_pin?.gone()
            pin?.jump(-40f)
            dot?.visible()
        }

        mMap?.setOnCameraIdleListener {
            shadow_pin?.visible()
            pin?.jump(0f)
            dot?.gone()
        }

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        intent.getParcelableExtra<LatLng>(EXTRA_LAT_LNG).let {

            if (it == null) {
                getLocation()
            } else {
                zoomTo(it)
            }

        }

        initView()


    }

    private fun zoomTo(latLng: LatLng) {
        mMap?.animateCamera(
            CameraUpdateFactory.newLatLngZoom(
                latLng, ZOOM
            )
        )
    }

    private fun initView() {

        btn_back?.setOnClickListener { onBackPressed() }
        btn_confirm?.setOnClickListener {

            val target = mMap?.cameraPosition?.target ?: LatLng(0.0, 0.0)

            setResult(RESULT_OK, Intent().apply { putExtra(EXTRA_LAT_LNG, target) })
            finish()

        }

        fab_my_location?.setOnClickListener { getLocation() }

    }

    override fun onLocationRetrieved(location: Location) {
        zoomTo(LatLng(location.latitude, location.longitude))
    }


}