package com.example.nutrisaapplication.ui.main.inicioVisitaRapida

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.data.model.FirebaseData
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue
import kotlinx.android.synthetic.main.activity_list_questions.*
import java.util.*


class InicioVisitRapidaActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var baseDatos:DatabaseReference
    val messageList = ArrayList<FirebaseData>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_questions)
        baseDatos= FirebaseDatabase.getInstance().getReference()
        obtenerTienda()
        buttonVisitaRapida.setOnClickListener {
            onBackPressed()
            SharedApp.prefs.visitaRapida= true}
             obtenerDatos()

    }

    private fun obtenerDatos() {

        val region = arrayOf(
            "Selecciona tu region",
            "CENTRO NORTE",
            "CENTRO SUR",
            "NORTE GOLFO",
            "PACIFICO",
            "FRANQUICIAS"
        )
        val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, region)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerL.adapter = array_adapter
        spinnerL.onItemSelectedListener = this

        val distrito = arrayOf(
            "Selecciona tu distrito",
            "CENTRO",
            "COACALCO",
            "GAM",
            "NAUCALPAN",
            "NEZAHUALCOYOTL",
            "TLANEPANTLA",
            "TOLUCA",
            "BENITO JUAREZ",
            "COAPA",
            "COYOACAN",
            "MORELOS",
            "POLANCO",
            "SANTA FE",
            "TLAHUAC",
            "TLALPAN",
            "HUASTECA POTOSINA",
            "MONTERREY 1",
            "MONTERREY 2",
            "MONTERREY 3",
            "NOROESTE",
            "QUINTANA ROO",
            "VERACRUZ 1",
            "VERACRUZ 2",
            "VILLA HERMOSA",
            "GUADALAJARA 1",
            "GUADALAJARA 2",
            "GUADALAJARA 3",
            "GUANAJUATO",
            "GUERRERO",
            "LEON",
            "OAXACA CHIAPAS",
            "QUERETARO",
            "FR CN",
            "FR CS",
            "FR NG",
            "FR PC"
        )
        val array_adapter_distrito = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            distrito
        )
        array_adapter_distrito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_distritoL.adapter = array_adapter_distrito
        spinner_distritoL.onItemSelectedListener = this

        val tienda = arrayOf(
            "Selecciona tu tienda",
            "1 UNIVERSIDAD",
            "2 PERISUR",
            "3 PLAZA DE LAS ESTRELLAS 1",
            "4 GALERIAS TABASCO",
            "99999 PRUEBAS IT"
        )
        val array_adapter_tienda = ArrayAdapter(this, android.R.layout.simple_spinner_item, tienda)
        array_adapter_tienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_tiendaL.adapter = array_adapter_tienda
        spinner_tiendaL.onItemSelectedListener = this

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
       //accion sin seleccion
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
       //cuando seleccionen algun item
    }

    fun obtenerTienda(){
   // var ciudades= arrayListOf<Regiones>()

        val tiendas = baseDatos.child("Tienda Nutrisa").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("tienda", error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val children = snapshot.child("Nutrisa")
                    val region = children.child("0").getValue().toString()
                    val respuestaHijo = children.child("1").value
                    Log.i("tienda", "children de Nutrisa: " + respuestaHijo)
                    val shop = arrayListOf<Any>()
                    var objectMap: MutableMap<String, Objects>
                    val listData= arrayListOf<FirebaseData>()
                    val list = mutableListOf<FirebaseData>()

                    for (item in children.children) {
                        val valor = item.value
                        if (valor != null) {
                            shop.add(valor)
                        }

                        list.add(item.getValue(FirebaseData::class.java)!!)
                        Log.i("tienda", "MutableListOf: $list")
                        objectMap = item.value as MutableMap<String, Objects>

                        Log.i("tienda", "valor: $valor")
                        Log.i("tienda", "object map: $objectMap")
                        Log.i("tienda", "respuesta: $item")
                    }
                    Log.i("tienda", "valor: $listData")
                    Log.i("tienda", "array shop: " + shop)
                    Log.i("tienda", "hijo: " + region)
                    Log.i("tienda", "respuesta base de datos: " + children)
                }
            }
        })

    }
}