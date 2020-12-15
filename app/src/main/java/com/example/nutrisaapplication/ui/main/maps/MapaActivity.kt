package com.example.nutrisaapplication.ui.main.maps

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.utils.distanceTo
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class MapaActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private lateinit var mMap: GoogleMap
    private val permisoFineLocation= android.Manifest.permission.ACCESS_FINE_LOCATION
    private val permisoCoarseLocation= android.Manifest.permission.ACCESS_COARSE_LOCATION

    private val CODIGO_COLICITUD_PERMISO=100
    var funsedLocationClient: FusedLocationProviderClient?=null
    var locationRequest: LocationRequest?= null
    var callback: LocationCallback? = null
    private var marcadorGolden:Marker?= null
    private var marcadorPiramide:Marker?= null
    private var marcadorTorre:Marker?= null
    var miPosicion:LatLng? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        val mapFragment2 = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        funsedLocationClient = FusedLocationProviderClient(this)
        inicializarLocation()

        callback= object : LocationCallback(){
            override fun onLocationResult(locationResult: LocationResult?) {
                //super.onLocationResult(locationResult)
                locationResult?:return
                    if(mMap!=null){
                        if (ActivityCompat.checkSelfPermission(
                                this@MapaActivity,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                                this@MapaActivity,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ) != PackageManager.PERMISSION_GRANTED
                        ) {
                            // TODO: Consider calling
                            //    ActivityCompat#requestPermissions
                            // here to request the missing permissions, and then overriding
                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                            //                                          int[] grantResults)
                            // to handle the case where the user grants the permission. See the documentation
                            // for ActivityCompat#requestPermissions for more details.
                            return
                        }
                        mMap.isMyLocationEnabled=true
                        mMap.uiSettings.isMyLocationButtonEnabled=true
                        for(ubicacion in locationResult.locations){
                            Toast.makeText(
                                applicationContext,
                                "las cordenadas son:" + ubicacion.latitude.toString() + "," + ubicacion.longitude.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                            // Add a marker in Sydney and move the camera
                            miPosicion = LatLng(ubicacion.latitude, ubicacion.longitude)
                            mMap.addMarker(
                                MarkerOptions().position(miPosicion!!).title("Aqui estoy")
                            )
                            //mMap.moveCamera(CameraUpdateFactory.newLatLng(miPosicion))
                            val zoomLevel = 19.0f
                            mMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    miPosicion,
                                    zoomLevel
                                )
                            )
                        }
                    }
            }
        }
    }

    private fun inicializarLocation(){
        locationRequest = LocationRequest()
        locationRequest?.interval = 100000
        locationRequest?.fastestInterval=50000
        locationRequest?.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    @SuppressLint("MissingPermission")
    private fun obtenerUbicacion() {
        /*funsedLocationClient?.lastLocation?.addOnSuccessListener(
             this,
             object : OnSuccessListener<Location> {
                 override fun onSuccess(location: Location?) {
                     if (location != null) {
                         Toast.makeText(applicationContext, location.latitude.toString()+ "-"+ location.longitude.toString(), Toast.LENGTH_LONG).show()
                     }
                 }
             })*/
        funsedLocationClient?.requestLocationUpdates(locationRequest, callback, null)
    }

    private fun pedirPermisos() {
        val deboProveerContexto = ActivityCompat.shouldShowRequestPermissionRationale(
            this,
            permisoFineLocation
        )
        if(deboProveerContexto){
            //mandar un mensaje explicado
        }else{
            solicitudPermiso()
        }
    }

    private fun solicitudPermiso() {
        requestPermissions(
            arrayOf(permisoFineLocation, permisoCoarseLocation),
            CODIGO_COLICITUD_PERMISO
        )
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.mapType=GoogleMap.MAP_TYPE_NORMAL
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        mMap.isMyLocationEnabled=true
        mMap.uiSettings.isMyLocationButtonEnabled=true
        mMap.uiSettings.isZoomControlsEnabled=true

        if(validarPermisos()){
            obtenerUbicacion()
        }else{
            pedirPermisos()
        }

            //.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        val GOLDEN_GATE= LatLng(19.4551, -96.9662)
        val PIRAMIDES= LatLng(19.5548008, -96.9648655)
        val TORRE_PISA = LatLng(19.4590949, -96.953369)
        marcadorGolden = mMap.addMarker(
            MarkerOptions()
                .position(GOLDEN_GATE)
                .title("Campo deportivo").snippet("Lugar en estados unidos")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_shop_yellow))
        )

        marcadorGolden?.tag=0
        marcadorPiramide = mMap.addMarker(
            MarkerOptions()
                .position(PIRAMIDES)
                .snippet("Primaide")
                .title("Piramides de Giza")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_shop_yellow))
        )
        marcadorPiramide?.tag=0
        marcadorTorre = mMap.addMarker(
            MarkerOptions()
                .position(TORRE_PISA)
                .snippet("Hamburguesas May zamora")
                .title("Hamburguesas May")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_shop_yellow))
        )
        marcadorTorre?.tag=0
        mMap.setOnMarkerClickListener(this)

    }
    override fun onMarkerClick(marcador: Marker?): Boolean {
        var numeroClick = marcador?.tag as? Int
        if(numeroClick != null){
            numeroClick++
            marcador?.tag= numeroClick
            prueba()
            Toast.makeText(this, "se ha dado $numeroClick clicks", Toast.LENGTH_LONG).show()
        }
    return false
    }

    fun prueba(){
        val marcadores: MutableList<LatLng> = ArrayList()
        val PRUEBA1= LatLng(19.4551, -96.9662)
        val PRUEBA2= LatLng(19.4546, -96.9664)
        val PRUEBA3 = LatLng(19.4590949, -96.953369)
        marcadores.add(PRUEBA1)
        mMap.addMarker(
            MarkerOptions().position(PRUEBA1).title("Ciclovia Callao")
                .snippet("Calle / Bidireccional / Alcántara a Sánchez Fontecilla")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )
        marcadores.add(PRUEBA2)
        mMap.addMarker(
            MarkerOptions().position(PRUEBA2).title("Ciclovia Antonio Varas")
                .snippet("Calle y Vereda / Bidireccional / Nueva Providencia a Irarrázaval")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )
        marcadores.add(PRUEBA3)
        mMap.addMarker(
            MarkerOptions().position(PRUEBA3).title("AntiA")
                .snippet("Calle y Vereda / Bidireccional / Nueva Providencia a Irarrázaval")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
        )
        val miPos = miPosicion
        var posicionMasCercana:LatLng? = null
        var distanciaActual = Double.MAX_VALUE
        var distancia:Float?= null
        val results = floatArrayOf(.1f)

        for(marcador in marcadores) marcadores.forEachIndexed { index, latLng ->

            if (miPos != null) {
                Location.distanceBetween(
                    miPos.latitude, miPos.longitude,
                    PRUEBA2.latitude, PRUEBA2.longitude,
                    results
                )
                distancia= results[0]
            }
                   }
        Toast.makeText(this, "la distancia es de: $distancia metros ", Toast.LENGTH_SHORT).show()

// en este punto, posicionMasCercana, tiene la latitud y longitud del punto
// mas cercano a miPos
    }

    private fun validarPermisos():Boolean{
        val hayUbicacionPrecisa= ActivityCompat.checkSelfPermission(this, permisoFineLocation) == PackageManager.PERMISSION_GRANTED
        val hayUbicacionOrdinaria = ActivityCompat.checkSelfPermission(this, permisoCoarseLocation) == PackageManager.PERMISSION_GRANTED

        return hayUbicacionPrecisa && hayUbicacionOrdinaria
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
                    Toast.makeText(
                        this,
                        "No diste permiso para acceder a la ubicación",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    private fun detenerActualizacionUbicacion() {
        funsedLocationClient?.removeLocationUpdates(callback)
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



}