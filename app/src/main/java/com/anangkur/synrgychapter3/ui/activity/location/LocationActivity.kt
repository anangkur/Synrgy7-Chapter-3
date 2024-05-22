package com.anangkur.synrgychapter3.ui.activity.location

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.anangkur.synrgychapter3.MyApplication
import com.anangkur.synrgychapter3.databinding.ActivityLocationBinding
import org.koin.android.ext.android.inject

class LocationActivity : AppCompatActivity(), LocationListener {

    private val binding by lazy { ActivityLocationBinding.inflate(layoutInflater) }
    private val locationManager: LocationManager by inject()
    private val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ::handleLocationPermissionResult,
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        setupLocationListener()
        getLatLon()
    }

    @SuppressLint("MissingPermission")
    private fun setupLocationListener() {
        if (isCoarseLocationGranted() && isFineLocationGranted()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000L, 10f, this)
        } else {
            askLocationPermission()
        }
    }

    @SuppressLint("MissingPermission")
    private fun getLatLon() {
        if (isCoarseLocationGranted() && isFineLocationGranted()) {
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            updateLocation(location)
        } else {
            askLocationPermission()
        }
    }

    override fun onLocationChanged(location: Location) {
        updateLocation(location)
    }

    private fun updateLocation(location: Location?) {
        val latitude = location?.latitude
        val longitude = location?.longitude
        binding.textLatLong.text = "latitude: $latitude | longitude: $longitude"
    }

    private fun handleLocationPermissionResult(result: Map<String, Boolean>) {
        if (result.containsValue(false)) {
            Toast.makeText(this, "permission ditolak", Toast.LENGTH_SHORT).show()
            onBackPressed()
        } else {
            Toast.makeText(this, "permission diterima", Toast.LENGTH_SHORT).show()
            setupLocationListener()
            getLatLon()
        }
    }

    private fun isCoarseLocationGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun isFineLocationGranted(): Boolean {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    private fun askLocationPermission() {
        locationPermissionRequest.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )
    }
}