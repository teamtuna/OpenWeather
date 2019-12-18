package team.tuna.openweather.repository

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

object LocationRepository {

    private val subjectMyLocation = BehaviorSubject.create<Location>()

    private var hasObserveCurrentLocation = false

    fun observeCurrentLocation(): Observable<Location> = subjectMyLocation

    fun getCurrentLocation(): Observable<Location> {
        return subjectMyLocation.take(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("MissingPermission")
    @Synchronized
    fun loadCurrentLocation(context: Context){
        if(hasObserveCurrentLocation) return
        hasObserveCurrentLocation = true

        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0F,
            object : LocationListener {
                override fun onLocationChanged(l: Location?) {
                    Log.d("LocationRepository", "onLocationChanged(${l?.latitude}, ${l?.longitude})")
                    l?.let { subjectMyLocation.onNext(it) }
                }

                override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
                }

                override fun onProviderEnabled(p0: String?) {
                }

                override fun onProviderDisabled(p0: String?) {
                }

            })
    }
}