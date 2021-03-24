package com.example.nutrisaapplication.utils

import android.location.Location
import com.google.android.gms.maps.model.LatLng



fun LatLng.toLocation() = Location("").apply {
    latitude=this.latitude
    longitude=this.longitude
}

fun LatLng.distanceTo(latLng: LatLng) = toLocation().distanceTo(latLng.toLocation())
 fun LatLng.algo()=""