package team.tuna.openweather.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.content.PermissionChecker
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

object LocationRepository {

    private const val MIN_DISTANCE_CHANGE_FOR_UPDATES = 10F
    private const val MIN_TIME_BW_UPDATES = 1000 * 60 * 1.toLong()

    private lateinit var locationManager: LocationManager

    private var lastLocation: Location? = null

    fun create(context: Context) {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            Log.d(
                "LocationRepository",
                "onLocationChanged(${location.latitude}, ${location.longitude})"
            )
            lastLocation = location
            onLocationChanged?.invoke(location)

        }

        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        }

        override fun onProviderEnabled(p0: String?) {
        }

        override fun onProviderDisabled(p0: String?) {
        }
    }

    private var onLocationChanged: ((Location) -> Unit)? = null

    @SuppressLint("MissingPermission")
    @Synchronized
    fun getLocation() = Single.create<Location> { emitter ->

        val isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        Log.d("LocationRepository", "isNetworkEnabled : $isNetworkEnabled")

        if (isNetworkEnabled) {
            onLocationChanged = { location ->
                locationManager.removeUpdates(locationListener)
                onLocationChanged = null

                emitter.onSuccess(location)
            }

            locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                MIN_TIME_BW_UPDATES,
                MIN_DISTANCE_CHANGE_FOR_UPDATES,
                locationListener
            )
        } else {
            emitter.onError(IllegalStateException("cannot use network provider"))
        }

    }.timeout(3, TimeUnit.SECONDS) {
        locationManager.removeUpdates(locationListener)
        onLocationChanged = null

        if (lastLocation != null) {
            it.onSuccess(lastLocation)
        } else {
            it.onError(TimeoutException("Loading takes too long."))
        }
    }
}