package com.example.cleanarchme.data

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import android.location.Location
import com.example.data.source.LocationDataSource
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PlayServicesLocationDataSource(application: Application) : LocationDataSource {

    private val geocoder = Geocoder(application)
    private val fusedLocationClientInfoStatus =
        LocationServices.getFusedLocationProviderClient(application)

    @SuppressLint("MissingPermission")
    override suspend fun getLastLocation(): String? =
        suspendCancellableCoroutine { continuation ->
            fusedLocationClientInfoStatus.lastLocation.addOnCompleteListener { l ->
                continuation.resume(l.result?.toRegion())
            }
        }

    private fun Location?.toRegion(): String? {
        val address = this?.let {
            geocoder.getFromLocation(latitude, longitude, 1)
        }
        return address?.firstOrNull()?.countryCode
    }
}