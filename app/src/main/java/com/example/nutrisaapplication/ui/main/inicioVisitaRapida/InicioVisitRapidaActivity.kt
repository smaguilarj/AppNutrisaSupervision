package com.example.nutrisaapplication.ui.main.inicioVisitaRapida

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.data.model.FirebaseData
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_questions.*
import java.util.*
import kotlin.collections.ArrayList


class InicioVisitRapidaActivity : BaseActivity(), AdapterView.OnItemSelectedListener {
    private lateinit var baseDatos: DatabaseReference

    val list = mutableListOf<FirebaseData>()
    val listaTiendas = arrayListOf<String>()
    val listRegion = arrayListOf<String>()
    val listDistrito= arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_questions)
        baseDatos = FirebaseDatabase.getInstance().getReference()
        obtenerTienda()
        buttonVisitaRapida.setOnClickListener {
            onBackPressed()
            SharedApp.prefs.visitaRapida = true
        }

    }

    private fun getShop(listTienda: ArrayList<String>) {
        listTienda.add(0, "Selecciona la tienda")
        val array_adapter_tienda =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listTienda)
        array_adapter_tienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_tiendaL.adapter = array_adapter_tienda
        spinner_tiendaL.onItemSelectedListener = this
    }

    private fun getRegion(listRegion: ArrayList<String>) {

        val hashSet: Set<String> = HashSet(listRegion)
        listRegion.clear()
        listRegion.addAll(hashSet)
        listRegion.add(0, "Selecciona tu region")
        val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listRegion)
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerL.adapter = array_adapter
        spinnerL.onItemSelectedListener = this
    }

    private fun getDistrito(listDistrito: java.util.ArrayList<String>) {
        val hashSetD: Set<String> = HashSet(listDistrito)
        listDistrito.clear()
        listDistrito.addAll(hashSetD)
        listDistrito.add(0, "Selecciona tu distrito")
        val array_adapter_distrito = ArrayAdapter(this,android.R.layout.simple_spinner_item, listDistrito)
        array_adapter_distrito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_distritoL.adapter = array_adapter_distrito
        spinner_distritoL.onItemSelectedListener = this
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {
        //accion sin seleccion
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        //cuando seleccionen algun item
    }

    fun obtenerTienda() {

        val tiendas = baseDatos.child("tienda").addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                Log.e("tienda", error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {

                    for (item in snapshot.children) {
                        list.add(item.getValue(FirebaseData::class.java)!!)
                    }

                    list.forEachIndexed { index, firebaseData ->
                        firebaseData.Nombre_tienda?.let { it -> listaTiendas.add(it) }
                        firebaseData.region?.let { item ->
                            listRegion.add(item)
                        }
                        firebaseData.distrito?.let {
                            distrito->
                                listDistrito.add(distrito)
                        }
                    }
                    getShop(listaTiendas)
                    getRegion(listRegion)
                    getDistrito(listDistrito)
                }

            }
        })

    }

}