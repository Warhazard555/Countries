package com.example.task1.ext

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.task1.THOUSAND
import com.example.task1.ZERO
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable

private var fusedLocationClient: FusedLocationProviderClient? = null
private var myLocation = Location("")
var distance = ZERO

fun checkPermissionStatus(context: Context): Boolean {
    val permissionStatus =
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    return if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
        true

    } else {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            ZERO
        )
        false
    }
}

@SuppressLint("MissingPermission")
fun lastLocation(context: Context): Flowable<Location> {
    return Flowable.create<Location>({
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        if (checkPermissionStatus(context)) {
            val task: Task<Location>? = fusedLocationClient?.lastLocation
            task?.addOnSuccessListener { location ->
                location?.let {
                    myLocation = location
                }
            }
            it.onNext(myLocation)
            it.onComplete()
        } else {
            it.onError(Exception())
            it.onComplete()
        }
    }, BackpressureStrategy.LATEST)
}

fun distanceFromMyLocation(latLng: List<Double>): Int {
    distance = ZERO
    return if (latLng.isNotEmpty()) {
        val lastCurrentLocation = Location(LocationManager.GPS_PROVIDER).apply {
            latitude = latLng[0]
            longitude = latLng[1]
        }
        myLocation.let {
            distance = myLocation.distanceTo(lastCurrentLocation).toInt().div(THOUSAND)
        }
        distance
    } else {
        ZERO
    }
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
    } else {
        false
    }
}

fun distanceFromMyLocation(current: Int, distanceMax: Int): Boolean {
    return current <= distanceMax
}