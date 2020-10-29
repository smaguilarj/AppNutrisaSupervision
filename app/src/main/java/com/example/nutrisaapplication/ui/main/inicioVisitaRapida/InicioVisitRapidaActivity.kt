package com.example.nutrisaapplication.ui.main.inicioVisitaRapida

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.data.model.DatosRegion
import com.example.nutrisaapplication.data.model.TiendaMod
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_questions.*

class InicioVisitRapidaActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var baseDatos:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_questions)
        baseDatos= FirebaseDatabase.getInstance().getReference()
        obtenerTienda()
        buttonVisitaRapida.setOnClickListener { onBackPressed()
            SharedApp.prefs.visitaRapida= true}
        obtenerDatos()
    }

    private fun obtenerDatos() {

        val region = arrayOf("Selecciona tu region","CENTRO NORTE", "CENTRO SUR", "NORTE GOLFO", "PACIFICO", "FRANQUICIAS")
        val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, region)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = array_adapter
        spinner.onItemSelectedListener = this

        val distrito = arrayOf("Selecciona tu distrito","CENTRO", "COACALCO", "GAM", "NAUCALPAN", "NEZAHUALCOYOTL","TLANEPANTLA", "TOLUCA", "BENITO JUAREZ", "COAPA", "COYOACAN","MORELOS", "POLANCO", "SANTA FE", "TLAHUAC", "TLALPAN","HUASTECA POTOSINA", "MONTERREY 1", "MONTERREY 2", "MONTERREY 3", "NOROESTE","QUINTANA ROO", "VERACRUZ 1", "VERACRUZ 2", "VILLA HERMOSA", "GUADALAJARA 1", "GUADALAJARA 2", "GUADALAJARA 3", "GUANAJUATO", "GUERRERO","LEON", "OAXACA CHIAPAS", "QUERETARO", "FR CN", "FR CS", "FR NG", "FR PC")
        val array_adapter_distrito = ArrayAdapter(this, android.R.layout.simple_spinner_item, distrito)
        array_adapter_distrito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_distrito.adapter = array_adapter_distrito
        spinner_distrito.onItemSelectedListener = this

        val tienda = arrayOf("Selecciona tu tienda","1 UNIVERSIDAD", "2 PERISUR", "3 PLAZA DE LAS ESTRELLAS 1", "4 GALERIAS TABASCO", "99999 PRUEBAS IT")
        val array_adapter_tienda = ArrayAdapter(this, android.R.layout.simple_spinner_item, tienda)
        array_adapter_tienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_tienda.adapter = array_adapter_tienda
        spinner_tienda.onItemSelectedListener = this

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
       //accion sin seleccion
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
       //cuando seleccionen algun item
    }

    fun obtenerTienda(){
   // var ciudades= arrayListOf<Regiones>()
        val tienda = baseDatos.child("Tienda Nutrisa").orderByChild("datos_distrito")
        //Log.i("tienda",respuesta"$tienda")
        val tiendas = baseDatos.child("Tienda Nutrisa").addListenerForSingleValueEvent(object :ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("firebase", error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
        if (snapshot.exists()){
            var algo = snapshot.children
            val children = snapshot.children
            for(ds in children){
                //og.d("spinner brand",searchable_spinner_country.selectedItem.toString())
                val region = ds.child("datos_region").getValue(Any::class.java)
                val pruebaRegion= region?.equals("datos_region")
                val  continentselected = region.toString()
                Log.d("tienda","datos_region: "+continentselected)
                Log.d("tienda","datos_region: "+ pruebaRegion)
            }
            // This returns the correct child count...
            Log.i("tienda", "respuesta: " + children.toString())
        }
            }
        })
        Log.i("tienda","respuesta: $tiendas")
    }
}