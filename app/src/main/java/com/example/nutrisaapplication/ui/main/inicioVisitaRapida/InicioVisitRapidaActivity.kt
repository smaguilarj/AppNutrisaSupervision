package com.example.nutrisaapplication.ui.main.inicioVisitaRapida

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.data.SharedApp
import com.example.nutrisaapplication.data.model.FirebaseData
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.example.nutrisaapplication.ui.main.maps.MapaActivity
import com.example.nutrisaapplication.ui.main.navigationDrawer.NavigationDrawerActivity
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_list_questions.*
import java.text.FieldPosition
import java.util.*
import kotlin.collections.ArrayList


class InicioVisitRapidaActivity : BaseActivity(), OnItemSelectedListener {
    private lateinit var baseDatos: DatabaseReference

    val list = mutableListOf<FirebaseData>()
    val listaTiendas = arrayListOf<String>()
    val listaRegiones = arrayListOf<String>()
    val listaDistritos = arrayListOf<String>()
    val listCentroNorte = arrayListOf<String>()
    val listCentroSur = arrayListOf<String>()
    val listPacifico = arrayListOf<String>()
    val listFranquicia = arrayListOf<String>()
    val listNorteGolfo = arrayListOf<String>()
    val listTlanepantla = arrayListOf<String>()
    val listBenito = arrayListOf<String>()
    val listCentro = arrayListOf<String>()
    val listCoacalco = arrayListOf<String>()
    val listCoapa = arrayListOf<String>()
    val listCoyoacan = arrayListOf<String>()
    val listEcatepec = arrayListOf<String>()
    val listFRCN = arrayListOf<String>()
    val listFRCS = arrayListOf<String>()
    val listaFRNG = arrayListOf<String>()
    val listaFRPC = arrayListOf<String>()
    val listGam = arrayListOf<String>()
    val listGuadalajara1 = arrayListOf<String>()
    val listGuadalajara2 = arrayListOf<String>()
    val listGuadalajara3 = arrayListOf<String>()
    val listGuanajuato = arrayListOf<String>()
    val listGuerrero = arrayListOf<String>()
    val listPotosi = arrayListOf<String>()
    val listLeon = arrayListOf<String>()
    val listMonterrey1 = arrayListOf<String>()
    val listMonterrey2 = arrayListOf<String>()
    val listMonterrey3 = arrayListOf<String>()
    val listMorelos = arrayListOf<String>()
    val listNaucalpan = arrayListOf<String>()
    val listNeza = arrayListOf<String>()
    val listNoroeste = arrayListOf<String>()
    val listOaxaca = arrayListOf<String>()
    val listPolanco = arrayListOf<String>()
    val listQueretaro = arrayListOf<String>()
    val listRoo = arrayListOf<String>()
    val listTlahuac = arrayListOf<String>()
    val listSantafe = arrayListOf<String>()
    val listTlalpan = arrayListOf<String>()
    val listVer1 = arrayListOf<String>()
    val listVer2 = arrayListOf<String>()
    val listToluca = arrayListOf<String>()
    val listVillahermosa = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_questions)
        baseDatos = FirebaseDatabase.getInstance().getReference()
        obtenerTienda()
        buttonVisitaRapida.setOnClickListener {
            onBackPressed()
            SharedApp.prefs.visitaRapida = true
        }
        imgBtn_mapa.setOnClickListener {
            this.startActivity( Intent(this, MapaActivity::class.java))
        }

    }

    private fun getShop(listTienda: ArrayList<String>) {
        listTienda.add(0, "Selecciona la tienda")
        val array_adapter_tienda =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, listTienda)
        array_adapter_tienda.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_tiendaL.adapter = array_adapter_tienda
       // spinner_tiendaL.onItemSelectedListener=this
        spinner_tiendaL.onItemSelectedListener=object : OnItemSelectedListener{
            override fun onItemSelected(adapter: AdapterView<*>?, vista: View?, position: Int, p3: Long) {
                val seleccionado = adapter?.getItemAtPosition(position).toString()
                if (seleccionado != "Selecciona la tienda"){
                    SharedApp.prefs.tienda = seleccionado
                    Toast.makeText(applicationContext, "item seleccionado: $seleccionado", Toast.LENGTH_LONG).show()
                }

                Log.i("tienda", seleccionado)
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
               //no hacer
            }
        }
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
        val array_adapter_distrito = ArrayAdapter(this, android.R.layout.simple_spinner_item, listDistrito)
        array_adapter_distrito.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner_distritoL.adapter = array_adapter_distrito
        spinner_distritoL.onItemSelectedListener = this
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        //accion sin seleccion
    }

    override fun onItemSelected(adaptador: AdapterView<*>?, vista: View?, posicion: Int, p3: Long) {
        //cuando seleccionen algun item
        val item = adaptador?.selectedItem.toString()
        // Log.i("tienda", "resultado de vista: $vist")
        when (item) {
            "CENTRO NORTE" -> {
                getDistrito(listCentroNorte)
            }
            "CENTRO SUR" -> {
                getDistrito(listCentroSur)
            }
            "PACÍFICO" -> {
                getDistrito(listPacifico)
            }
            "FRANQUICIAS" -> {
                getDistrito(listFranquicia)
            }
            "NORTE GOLFO" -> {
                getDistrito(listNorteGolfo)
            }
            "BENITO JUÁREZ" -> {
                getShop(listBenito)
            }
            "CENTRO" -> {
                getShop(listCentro)

            }
            "COACALCO" -> {
                getShop(listCoacalco)
            }
            "COAPA" -> {
                getShop(listCoapa)
            }
            "COYOACÁN" -> {
                getShop(listCoyoacan)
            }
            "ECATEPEC" -> {
                getShop(listEcatepec)
            }
            "FR CN" -> {
                getShop(listFRCN)
            }
            "FR CS" -> {
                getShop(listFRCS)
            }
            "FR NG" -> {
                getShop(listaFRNG)
            }
            "FR PC" -> {
                getShop(listaFRPC)
            }
            "GAM" -> {
                getShop(listGam)
            }
            "GUADALAJARA 1" -> {
                getShop(listGuadalajara1)
            }
            "GUADALAJARA 2" -> {
                getShop(listGuadalajara2)
            }
            "GUADALAJARA 3" -> {
                getShop(listGuadalajara3)
            }
            "GUANAJUATO" -> {
                getShop(listGuanajuato)
            }
            "GUERRERO" -> {
                getShop(listGuerrero)
            }
            "HUASTECA POTOSINA" -> {
                getShop(listPotosi)
            }
            "LEÓN" -> {
                getShop(listLeon)
            }
            "MONTERREY 1" -> {
                getShop(listMonterrey1)
            }
            "MONTERREY 2" -> {
                getShop(listMonterrey2)
            }
            "MONTERREY 3" -> {
                getShop(listMonterrey3)
            }
            "MORELOS" -> {
                getShop(listMorelos)
            }
            "NAUCALPAN" -> {
                getShop(listNaucalpan)
            }
            "NEZAHUALCÓYOTL" -> {
                getShop(listNeza)
            }
            "NOROESTE" -> {
                getShop(listNoroeste)
            }
            "OAXACA CHIAPAS" -> {
                getShop(listOaxaca)
            }
            "POLANCO" -> {
                getShop(listPolanco)
            }
            "QUERÉTARO" -> {
                getShop(listQueretaro)
            }
            "QUINTANA ROO" -> {
                getShop(listRoo)
            }
            "SANTA FE" -> {
                getShop(listSantafe)
            }
            "TLÁHUAC" -> {
                getShop(listTlahuac)
            }
            "TLALNEPANTLA" -> {
                getShop(listTlanepantla)
            }
            "TLALPAN" -> {
                getShop(listTlalpan)
            }
            "TOLUCA" -> {
                getShop(listToluca)
            }
            "VERACRUZ 1" -> {
                getShop(listVer1)
            }
            "VERACRUZ 2" -> {
                getShop(listVer2)
            }
            "VILLAHERMOSA" -> {
                getShop(listVillahermosa)
            }
        }
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
                        firebaseData.region?.let { item ->
                            listaRegiones.add(item)
                            when (item) {
                                "CENTRO NORTE" -> {
                                    firebaseData.distrito?.let { distrito ->
                                        listCentroNorte.add(distrito)
                                    }
                                }
                                "CENTRO SUR" -> {
                                    firebaseData.distrito?.let { distrito ->
                                        listCentroSur.add(distrito)
                                    }
                                }
                                "PACÍFICO" -> {
                                    firebaseData.distrito?.let { distrito ->
                                        listPacifico.add(distrito)
                                    }
                                }
                                "FRANQUICIAS" -> {
                                    firebaseData.distrito?.let { distrito ->
                                        listFranquicia.add(distrito)
                                    }
                                }
                                "NORTE GOLFO" -> {
                                    firebaseData.distrito?.let { distrito ->
                                        listNorteGolfo.add(distrito)
                                    }
                                }
                                else -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "no has seleccionado un distrito",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        firebaseData.distrito?.let { distrito ->
                            listaDistritos.add(distrito)
                            when (distrito) {
                                "BENITO JUÁREZ" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listBenito.add(item)
                                    }
                                }
                                "CENTRO" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listCentro.add(item)
                                    }
                                }
                                "COACALCO" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listCoacalco.add(item)
                                    }
                                }
                                "COAPA" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listCoapa.add(item)
                                    }
                                }
                                "COYOACÁN" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listCoyoacan.add(item)
                                    }
                                }
                                "ECATEPEC" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listEcatepec.add(item) }
                                }
                                "FR CN" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listFRCN.add(item) }
                                }
                                "FR CS" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listFRCS.add(item) }
                                }
                                "FR NG" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listaFRNG.add(item) }
                                }
                                "FR PC" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listaFRPC.add(item) }
                                }
                                "GAM" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listGam.add(item) }
                                }
                                "GUADALAJARA 1" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listGuadalajara1.add(
                                            item
                                        )
                                    }
                                }
                                "GUADALAJARA 2" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listGuadalajara2.add(
                                            item
                                        )
                                    }
                                }
                                "GUADALAJARA 3" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listGuadalajara3.add(
                                            item
                                        )
                                    }
                                }
                                "GUANAJUATO" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listGuanajuato.add(
                                            item
                                        )
                                    }
                                }
                                "GUERRERO" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listGuerrero.add(item) }
                                }
                                "HUASTECA POTOSINA" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listPotosi.add(item) }
                                }
                                "LEÓN" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listLeon.add(item) }
                                }
                                "MONTERREY 1" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listMonterrey1.add(
                                            item
                                        )
                                    }
                                }
                                "MONTERREY 2" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listMonterrey2.add(
                                            item
                                        )
                                    }
                                }
                                "MONTERREY 3" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listMonterrey3.add(
                                            item
                                        )
                                    }
                                }
                                "MORELOS" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listMorelos.add(item) }
                                }
                                "NAUCALPAN" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listNaucalpan.add(item) }
                                }
                                "NEZAHUALCÓYOTL" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listNeza.add(item) }
                                }
                                "NOROESTE" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listNoroeste.add(item) }
                                }
                                "OAXACA CHIAPAS" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listOaxaca.add(item) }
                                }
                                "POLANCO" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listPolanco.add(item) }
                                }
                                "QUERÉTARO" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listQueretaro.add(item) }
                                }
                                "QUINTANA ROO" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listRoo.add(item) }
                                }
                                "SANTA FE" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listSantafe.add(item) }
                                }
                                "TLÁHUAC" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listTlahuac.add(item) }
                                }
                                "TLALNEPANTLA" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listTlanepantla.add(
                                            item
                                        )
                                    }
                                }
                                "TLALPAN" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listTlalpan.add(item) }
                                }
                                "TOLUCA" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listToluca.add(item) }
                                }
                                "VERACRUZ 1" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listVer1.add(item) }
                                }
                                "VERACRUZ 2" -> {
                                    firebaseData.Nombre_tienda?.let { item -> listVer2.add(item) }
                                }
                                "VILLAHERMOSA" -> {
                                    firebaseData.Nombre_tienda?.let { item ->
                                        listVillahermosa.add(
                                            item
                                        )
                                    }
                                }
                                else -> {
                                    Toast.makeText(
                                        applicationContext,
                                        "no has seleccionado un distrito",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                        firebaseData.Nombre_tienda?.let { it -> listaTiendas.add(it) }
                    }
                    getRegion(listaRegiones)
                }
            }
        })
    }
}