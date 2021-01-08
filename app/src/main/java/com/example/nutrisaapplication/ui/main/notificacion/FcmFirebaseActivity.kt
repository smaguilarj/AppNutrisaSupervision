package com.example.nutrisaapplication.ui.main.notificacion

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.main.notificacion.model.PushNotification
import layout.NotificacionAdapter


class FcmFirebaseActivity : AppCompatActivity(),createDataInterface {

    private var lista: RecyclerView? = null
    private var mNoMessagesView: LinearLayout? = null
    private var mNotificatiosAdapter: NotificacionAdapter? = null
    private lateinit var adapter:NotificacionAdapter
    var dataList = ArrayList <PushNotification>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fcm_firebase)
        crearDatos()
        mNotificatiosAdapter = NotificacionAdapter(dataList, object : NotificationInterface {
            override fun onClicked(vista: View, index: Int) {
                Toast.makeText(applicationContext, "este es el click en la posicion $index", Toast.LENGTH_SHORT).show()
            }
        })
        lista = findViewById(R.id.rv_notifications_list)
        lista?.setHasFixedSize(true)
        val layoutManager= LinearLayoutManager(this)
        lista?.layoutManager=layoutManager
        mNoMessagesView = findViewById(R.id.noMessages)
        lista!!.adapter= mNotificatiosAdapter
        val dummyList = mutableListOf<PushNotification>()
        adapter.notifyDataSetChanged()
        //lista!!.adapter = mNotificatiosAdapter
        if(true){
            mostrarEmptyState(false)
        }else{
            mostrarEmptyState(true)
        }
    }

    private fun crearDatos() {
        mostrarNotificacion(dataList)
        }

    fun mostrarEmptyState(empty: Boolean) {
        lista!!.visibility = if (empty) View.GONE else View.VISIBLE
        mNoMessagesView!!.visibility = if (empty) View.VISIBLE else View.GONE
    }

    fun popPushNotificacion(pushMessage: PushNotification?) {
        mNotificatiosAdapter?.addItem(pushMessage)
    }

    fun mostrarNotificacion(notifications: ArrayList<PushNotification>) {
        mNotificatiosAdapter?.replaceData(notifications)
    }

    override fun onSetData(lista: PushNotification) {
        dataList.add(lista)
        popPushNotificacion(lista)
        mostrarNotificacion(dataList)
        Log.d("notificacion","la lista es: $dataList")
    }

    override fun onResume() {
        super.onResume()
        if(dataList.size==0){
            mostrarEmptyState(false)
        }else{
            mostrarEmptyState(true)
        }
    }
}