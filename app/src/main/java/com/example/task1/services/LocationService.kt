package com.example.task1.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.example.task1.*

class LocationService : Service(), LocationListener {

    private var mContext: Context? = null

    var checkGPSOn = false
    var checkNetworkOn = false
    var getLocation = false
    var loc: Location? = null
    var latitude = 0.0
    var longitude = 0.0

    protected var locationManager: LocationManager? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            if (!intent.hasExtra("kill self!")) {
                initLocation()
                initNotification()
            } else {
                killSelf()
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun initLocation(): Location? {
        try {
            locationManager =
                applicationContext?.getSystemService(LOCATION_SERVICE) as LocationManager
            checkGPSOn = locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) == true
            checkNetworkOn =
                locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) == true
            if (!checkGPSOn && !checkNetworkOn) {
                Toast.makeText(mContext, "No Service Provider is available", Toast.LENGTH_SHORT)
                    .show()
            } else {
                getLocation = true
                mContext?.let {
                    if (checkGPSOn) {
                        if (ContextCompat.checkSelfPermission(
                                it,
                                android.Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                            && ContextCompat.checkSelfPermission(
                                it,
                                android.Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {
                            locationManager?.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER,
                                MIN_TIME_BW_UPDATE,
                                MIN_DISTANCE_FOR_UPDATES.toFloat(), this
                            )
                            if (locationManager != null) {
                                loc =
                                    locationManager?.getLastKnownLocation(LocationManager.GPS_PROVIDER)
                                if (loc != null) {
                                    latitude = loc?.latitude ?: 0.0
                                    longitude = loc?.longitude ?: 0.0
                                }
                            }
                        }

                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return loc
    }

    private fun initNotification() {
        val intent = Intent()
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.foreground_channel_name)
            val importance = NotificationManager.IMPORTANCE_MIN
            val mChannel = NotificationChannel(
                getString(R.string.foreground_channel_id),
                name,
                importance
            )
            val notificationManager = getSystemService(
                NOTIFICATION_SERVICE
            ) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val builder: NotificationCompat.Builder = NotificationCompat.Builder(
            this, getString(
                R.string.foreground_channel_id
            )
        )

        val bigTextStyle: NotificationCompat.BigTextStyle = NotificationCompat.BigTextStyle()
        bigTextStyle.setBigContentTitle(getString(R.string.foreground_notification_name))

        builder.setStyle(bigTextStyle)
        builder.setWhen(System.currentTimeMillis())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.priority = NotificationManager.IMPORTANCE_MIN
        } else {
            builder.priority = Notification.PRIORITY_MIN
        }

        builder.setFullScreenIntent(pendingIntent, true)

        val notification: Notification = builder.build()

        startForeground(SERVICE_ID, notification)
    }

    private fun killSelf() {
        stopListening()
        stopForeground(true)
        stopSelf()
    }

    private fun stopListening() {
        if (locationManager != null) {
            locationManager?.let { manager ->
                applicationContext?.let {
                    manager.removeUpdates(this@LocationService)
                }
            }
        }
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)
        killSelf()
    }

    override fun onLocationChanged(location: Location) {
        val intent = Intent()
        intent.action = NEW_LOCATION_ACTION
        intent.putExtra("Latitude", location.latitude)
        intent.putExtra("Longitude", location.longitude)
        sendBroadcast(intent)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}