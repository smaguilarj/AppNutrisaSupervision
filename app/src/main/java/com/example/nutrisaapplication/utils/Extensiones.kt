package com.example.nutrisaapplication.utils

import android.location.Location
import com.google.android.gms.maps.model.LatLng



fun LatLng.toLocation() = Location("").apply {
    latitude = latitude
    longitude = longitude
}

fun LatLng.distanceTo(latLng: LatLng) = toLocation().distanceTo(latLng.toLocation())
