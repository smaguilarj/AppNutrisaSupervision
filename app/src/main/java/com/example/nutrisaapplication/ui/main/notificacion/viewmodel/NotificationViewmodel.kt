package com.example.nutrisaapplication.ui.main.notificacion.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nutrisaapplication.ui.main.notificacion.model.CloudMessangingService
import com.example.nutrisaapplication.ui.main.notificacion.model.PushNotification

class NotificationViewmodel : ViewModel() {

    //private var repo = FcmService()
    private var repo = CloudMessangingService()
    val mutableData = MutableLiveData<MutableList<PushNotification>>()
    fun fetchData(): LiveData<MutableList<PushNotification>> {
        repo.getDataFirebase().observeForever{ respuesta ->
            mutableData.value=respuesta
        }
        return mutableData
    }
fun recibir(data: MutableList<PushNotification>)
{
    mutableData.value= data
}
    /*private var deviceData : LiveData<MutableList<PushNotification>>? = null
    fun fetchDeviceData(deviceId:String):LiveData<MutableList<PushNotification>>{
        deviceData = repo.getDataFirebase(deviceId)
        return deviceData!!
    }*/
}