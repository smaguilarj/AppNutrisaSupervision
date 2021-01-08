package com.example.nutrisaapplication.ui.main.notificacion

import android.view.View
import com.example.nutrisaapplication.ui.main.notificacion.model.PushNotification

interface NotificationInterface {
    /*fun onSetView(list: ArrayList<PushNotification>)*/
    fun onClicked(vista: View, index: Int)
}