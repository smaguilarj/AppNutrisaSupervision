package com.example.nutrisaapplication.ui.main.notificacion.model

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.example.nutrisaapplication.ui.main.notificacion.FcmFirebaseActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FcmService : FirebaseMessagingService(){
    /*override fun onNewToken(token: String) {
        Log.d(TAG, "Refreshed token: $token")
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }*/
    var dataList = PushNotification()
    var miClase: FcmFirebaseActivity = FcmFirebaseActivity()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        val TAG= "notificacion"
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "token del dispositivo: " + remoteMessage.from)
           Looper.prepare()
        Handler().post {
            // Check if message contains a data payload.
            if (remoteMessage.data.size >= 0) {
                Log.d(TAG, "Message data payload: " + remoteMessage.data)
                Toast.makeText(applicationContext, "tienes una lista mayor a 0 " + remoteMessage.data.size, Toast.LENGTH_SHORT).show()
            } else {
                // Handle message within 10 seconds
                Toast.makeText(applicationContext, "ncesitas handle" , Toast.LENGTH_SHORT).show()
            }
            // Check if message contains a notification payload.
            if (remoteMessage.notification != null) {
                Log.d(TAG, "Mensaje Notificacion Body: " + remoteMessage.notification!!.body)
                Log.d(TAG, "Mensaje Notificacion Title: " + remoteMessage.notification!!.title)
                Log.d(TAG, "Mensaje Notificacion date: " + remoteMessage.getData().get("expiry_date"))
                dataList= PushNotification("0",remoteMessage.notification!!.title,remoteMessage.notification!!.body,remoteMessage.getData().get("expiry_date"))
                miClase.onSetData(dataList)
            }else{
                Log.d(TAG, "no tienes notificaciones")
            }
        }
        Looper.loop()
       /* // Check if message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            if ( true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                //scheduleJob()
                Toast.makeText(this, "tienes una lista mayor a 0 " + remoteMessage.data.size, Toast.LENGTH_SHORT).show()
            } else {
                // Handle message within 10 seconds
                Toast.makeText(this, "ncesitas handle" , Toast.LENGTH_SHORT).show()
            }
        }
        // Check if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Mensaje Notificacion Body: " + remoteMessage.notification!!.body)
            Toast.makeText(this, "body  " + remoteMessage.notification!!.body, Toast.LENGTH_SHORT).show()
        }
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    */
    }

}