package com.example.nutrisaapplication.ui.main.maps

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.example.nutrisaapplication.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

class MapsActivity : AppCompatActivity() {
    private val permisoFineLocation= android.Manifest.permission.ACCESS_FINE_LOCATION
    private val permisoCoarseLocation= android.Manifest.permission.ACCESS_COARSE_LOCATION

    private val CODIGO_COLICITUD_PERMISO=100
    var funsedLocationClient:FusedLocationProviderClient?=null
    var locationRequest:LocationRequest?= null
    var callback:LocationCallback ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        funsedLocationClient = FusedLocationProviderClient(this)
        inicializarLocation()
    }

    private fun validarPermisos():Boolean{
        val hayUbicacionPrecisa= ActivityCompat.checkSelfPermission(this,permisoFineLocation) == PackageManager.PERMISSION_GRANTED
        val hayUbicacionOrdinaria = ActivityCompat.checkSelfPermission(this,permisoCoarseLocation) == PackageManager.PERMISSION_GRANTED

        return hayUbicacionPrecisa && hayUbicacionOrdinaria
    }
    private fun pedirPermisos() {
    val deboProveerContexto = ActivityCompat.shouldShowRequestPermissionRationale(this,permisoFineLocation)
        if(deboProveerContexto){
        //mandar un mensaje explicado
        }else{
            solicitudPermiso()
        }
    }

    private fun solicitudPermiso() {

        requestPermissions(arrayOf(permisoFineLocation,permisoCoarseLocation),CODIGO_COLICITUD_PERMISO)
    }

    private fun inicializarLocation(){
        locationRequest = LocationRequest()
        locationRequest?.interval = 10000
        locationRequest?.fastestInterval=5000
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }
    @SuppressLint("MissingPermission")
    private fun obtenerUbicacion() {
       /* funsedLocationClient?.lastLocation?.addOnSuccessListener(
            this,
            object : OnSuccessListener<Location> {
                override fun onSuccess(location: Location?) {
                    if (location != null) {
                        Toast.makeText(applicationContext, location.latitude.toString()+ "-"+ location.longitude.toString(), Toast.LENGTH_LONG).show()
                    }
                }

            })*/
        callback= object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                super.onLocationResult(locationResult)
                for(ubicacion in locationResult?.locations!!){
                    Toast.makeText(applicationContext, "las cordenadas son:"+ ubicacion.latitude.toString()+","+ ubicacion.longitude.toString(), Toast.LENGTH_LONG).show()
                }
            }
        }
        funsedLocationClient?.requestLocationUpdates(locationRequest,callback,null)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            CODIGO_COLICITUD_PERMISO -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //obtener ubicacion
                    obtenerUbicacion()
                } else {
                    Toast.makeText(this, "No diste permiso para acceder a la ubicación", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onStart() {
        super.onStart()
        if (validarPermisos()){
            obtenerUbicacion()
        }else{
            pedirPermisos()
        }
    }

    override fun onPause() {
        super.onPause()
        detenerActualizacionUbicacion()
    }

    private fun detenerActualizacionUbicacion() {
        funsedLocationClient?.removeLocationUpdates(callback)
    }
}