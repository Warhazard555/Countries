package com.example.task1.ext

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.task1.THOUSAND
import com.example.task1.ZERO
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task

private lateinit var fusedLocationClient: FusedLocationProviderClient
private lateinit var myLocation: Location
var distance = ZERO

fun lastLocation(context: Context) {
    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    ) {
        val task: Task<Location>? = fusedLocationClient.lastLocation
        task?.addOnSuccessListener { location ->
            location?.let {
                myLocation = location
            }
        }
    } else {
        val listPermissionsNeeded = ArrayList<String>()
        listPermissionsNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION)
        ActivityCompat.requestPermissions(context as Activity,listPermissionsNeeded.toTypedArray(),
            ZERO
            )
    }
        // TODO: Consider calling
        //    ActivityCompat#requestPermissions
        // here to request the missing permissions, and then overriding
        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
        //                                          int[] grantResults)
        // to handle the case where the user grants the permission. See the documentation
        // for ActivityCompat#requestPermissions for more details.
}

fun distanceFromMyLocation(latLng: List<Double>, distanceMax: Int): Boolean {
    distance = ZERO
    return if (latLng.isNotEmpty()) {
        val lastCurrentLocation = Location(LocationManager.GPS_PROVIDER).apply {
            latitude = latLng[0]
            longitude = latLng[1]
        }
        myLocation.let {
            distance = myLocation.distanceTo(lastCurrentLocation).toInt().div(THOUSAND)
        }
        distance <= distanceMax
    } else {false}
}