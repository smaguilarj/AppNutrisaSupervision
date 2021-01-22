package com.example.nutrisaapplication.ui.main.notificacion

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.example.nutrisaapplication.ui.main.notificacion.model.CloudMessangingService.Companion.INTENT_ACTION_SEND_MESSAGE
import com.example.nutrisaapplication.ui.main.notificacion.model.PushNotification
import com.example.nutrisaapplication.ui.main.notificacion.viewmodel.NotificationViewmodel
import layout.NotificacionAdapter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class FcmFirebaseActivity : BaseActivity(),createDataInterface {

    private var recyclerView: RecyclerView? = null
    private var mNoMessagesView: LinearLayout? = null
    private var mNotificatiosAdapter: NotificacionAdapter? = null
    private lateinit var adapter:MainAdapter
    var dataList = ArrayList<PushNotification>()
    val viewModel by lazy {
        ViewModelProviders.of(this).get(NotificationViewmodel::class.java)
    }
    //val newViewModel by viewModels<NotificationViewmodel> ()
    val mutableData = MutableLiveData<MutableList<PushNotification>>()
    lateinit var mNotificationsReceiver:BroadcastReceiver
    val notificacion = mutableListOf<PushNotification>()

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onThrowEvent(data : PushNotification) {
        // your preferred set of actions here.
        // Here I'm showing a snackbar.
        if (data.toString().isNotEmpty()) {
            val title = data.mTitle
            val cuerpo=data.mDescription
            val fecha= data.mExpiryDate
            Log.d("notificacion", "$title $cuerpo $fecha")
        }else{
            Toast.makeText(this, "no se pudieron recuperar la notificacion", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fcm_firebase)
        mNotificationsReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {
                val title = intent.getStringExtra("title")
                val description = intent.getStringExtra("description")
                val expiryDate = intent.getStringExtra("fecha")
                val mensaje= intent.extras?.getString("message")
                savePushMessage(title, description, expiryDate)
                Toast.makeText(applicationContext, "este es el primero", Toast.LENGTH_SHORT).show()
            }
        }
        val description: String? = intent.extras?.get("description") as String?
        val title: String? = intent.extras?.get("title") as String?
        val expiryDate= intent.extras?.get("fecha") as String?
        savePushMessage(title, description, expiryDate)
        /*LocalBroadcastManager.getInstance(this).registerReceiver(
            mNotificationsReceiver, IntentFilter(INTENT_ACTION_SEND_MESSAGE)
        )*/
        mutableData.observe(this, dataObserver)
        mNotificatiosAdapter = NotificacionAdapter(dataList, object : NotificationInterface {
            override fun onClicked(vista: View, index: Int) {
                Toast.makeText(
                    applicationContext,
                    "este es el click en la posicion $index",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
        recyclerView = findViewById(R.id.rv_notifications_list)
        recyclerView?.setHasFixedSize(true)
        val layoutManager= LinearLayoutManager(this)
        recyclerView?.layoutManager=layoutManager
        mNoMessagesView = findViewById(R.id.noMessages)
        adapter = MainAdapter(applicationContext, object : NotificationInterface {
            override fun onClicked(vista: View, index: Int) {
                Toast.makeText(applicationContext, "este es el click en la posicion $index", Toast.LENGTH_SHORT).show()
            }
        })

        recyclerView!!.adapter= adapter
        //observeData()
       /* dataList.add(0, PushNotification("0","Prueba0","este es el mensaje","20/02/21"))
        val dummyList = mutableListOf<PushNotification>()
        adapter.setList(dataList)
        adapter.notifyDataSetChanged()*/
        //lista!!.adapter = mNotificatiosAdapter
        if(true){
            mostrarEmptyState(false)
        }else{
            mostrarEmptyState(true)
        }


    }

    /*fun onNotificationsLoaded(notifications: List<PushNotification?>) {
        if (notifications.size > 0) {
            mostrarEmptyState(false)
            mostrarNotificacion(notifications as ArrayList<PushNotification>)
        } else {
            mostrarEmptyState(true)
        }
    }*/

    fun savePushMessage(title: String?, description: String?, expiryDate: String?) {
        val lista = PushNotification("0", title, description, expiryDate)
        dataList.add(lista)
        notificacion.add(0, lista)
        mutableData.value=notificacion
        viewModel.recibir(notificacion)
    }


    private val dataObserver = Observer<MutableList<PushNotification>> { respuesta->
        respuesta.let{
            adapter.setListData(it)
            adapter.notifyDataSetChanged()
        }
    }

    fun mostrarEmptyState(empty: Boolean) {
        recyclerView!!.visibility = if (empty) View.GONE else View.VISIBLE
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
        Log.d("notificacion", "la lista es: $dataList")
    }

    override fun onResume() {
        super.onResume()
        val filter= IntentFilter(INTENT_ACTION_SEND_MESSAGE)
        LocalBroadcastManager.getInstance(this).registerReceiver(mNotificationsReceiver, filter)
    }
    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mNotificationsReceiver)
    }
   /* override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mNotificationsReceiver)
    }*/

}