package com.example.nutrisaapplication.ui.main.notificacion.model


import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import androidx.lifecycle.*
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.nutrisaapplication.R
import com.example.nutrisaapplication.ui.base.BaseActivity
import com.example.nutrisaapplication.ui.main.notificacion.FcmFirebaseActivity
import com.example.nutrisaapplication.ui.main.notificacion.UploadWorker
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.greenrobot.eventbus.EventBus


class CloudMessangingService: FirebaseMessagingService(), LifecycleObserver {
    private val TAG = "CloudMessagingService"
    var dataList: PushNotification?=null
    var miClase: FcmFirebaseActivity = FcmFirebaseActivity()
    val notificacion = mutableListOf<PushNotification>()
    private var isAppInForeground = false

    override fun onCreate() {
        super.onCreate()
        ProcessLifecycleOwner.get().lifecycle.addObserver(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ProcessLifecycleOwner.get().lifecycle.removeObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForegroundStart() {
        isAppInForeground = true
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onForegroundStop() {
        isAppInForeground = false
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        // log the getting message from firebase
        Log.d(TAG, "From: " + remoteMessage.from)
        /*displayNotification(remoteMessage.notification!!, remoteMessage.getData())
        sendNewPromoBroadcast(remoteMessage)*/
        //  if remote message contains a data payload.
        if (remoteMessage.data.size > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.data)
            val data = remoteMessage.data
            val jobType = data["job"]
            if (jobType.equals("title", ignoreCase = true)) {
                // For long-running tasks (10 seconds or more) use WorkManager.
                scheduleLongRunningJob()
            } else {
                // Handle message within 10 seconds
               // handleNow(data)
            }
        }
        // We MUST READ values from Data
        /*val title = remoteMessage.data.get("title") ?: "Nutrisa"
        val messageText = remoteMessage.data.get("description") ?: "No tiene mensaje"
        val expireDate = remoteMessage.data.get("expiry_date") ?: ""*/
        val title = remoteMessage.notification!!.title ?: "Nutrisa"
        val messageText = remoteMessage.notification!!.body ?: "No tiene mensaje"
        val expireDate = remoteMessage.data["fecha"] ?: "12/02/20"

       /* val components = clickAction.split("?type=")
        clickAction = if (components.count() == 2) components[1] else ""*/
        if(isAppInForeground && messageText.isEmpty()) {
            EventBus.getDefault().post(PushNotification("0",title, messageText, expireDate))
        } else {
            sendNotification(PushNotification("0",title,messageText, expireDate))
        }
        // if message contains a notification payload.
        if (remoteMessage.notification != null) {
            Log.d(TAG, "Mensaje Notificacion Body: " + remoteMessage.notification!!.body)
            Log.d(TAG, "Mensaje Notificacion Title: " + remoteMessage.notification!!.title)
            Log.d(TAG, "Mensaje Notificacion date: " + remoteMessage.getData().get("fecha"))
            //  miClase.onSetData(dataList)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "Refreshed token: $token")
        sendRegistrationToServer(token)
    }

    /**
     * Persist token on third-party servers using your Retrofit APIs client.
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     *
     * @param token The new token.
     */
    private fun sendRegistrationToServer(token: String) {
        // make a own server request here using your http client
    }

    private fun handleNow(data: MutableMap<String, String>) {
        if (data.containsKey("title") && data.containsKey("message")) {
            //sendNotification(data["title"], data["message"], data["expiry_date"])
        }
    }

    /**
     * Schedule async work using WorkManager mostly these are one type job.
     */
    private fun scheduleLongRunningJob() {
        val constraints = Constraints.Builder()
            .setRequiresCharging(true)
            .build()
        val work = OneTimeWorkRequest.Builder(UploadWorker::class.java).build()
        WorkManager.getInstance().beginWith(work).enqueue()
    }

    /**
     * Create and show notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private fun sendNotifications(title: String?, messageBody: String?, fecha: String?) {
        val intent = Intent(this, FcmFirebaseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0 /* Request code */, intent,
            PendingIntent.FLAG_ONE_SHOT)
        val channelId: String = getString(R.string.default_notification_channel_id)
        val defaultSoundUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder= NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_abeja_naranja)
            .setContentTitle(title)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        //notificationManager.notify(1, notificationBuilder.build())
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Channel human readable title
            val channel = NotificationChannel(
                channelId, "Cloud Messaging Service",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager!!.createNotificationChannel(channel)
        }
        notificationManager!!.notify(0 /* ID of notification */, notificationBuilder.build())
        dataList = PushNotification("0", title, messageBody, fecha)

    }

    private fun sendNotification(data: PushNotification) {
        // 1. Get Intent of activity which you want to be launched
        val intent = Intent(this.applicationContext, FcmFirebaseActivity::class.java)
        // 2. Add Flags
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)
        // 3. Add Extras
        intent.putExtra("title", data.mTitle)
        intent.putExtra("description", data.mDescription)
        intent.putExtra("fecha", data.mExpiryDate)

        // 4. Create Pending Intent (Intent yet to launch) with above intent
        val pendingIntent = TaskStackBuilder.create(this)
            .addNextIntentWithParentStack(intent)
            .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)

        // 5. Default sound for notification.
       // val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        // 6. Create NotificationCompact Object
        /*val notificationBuilder = NotificationCompat.Builder(this, "ChannelID")
            .setSmallIcon(R.mipmap.ic_launcher_round) // 6.1 Set Small Icon - MUST
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher_round)) // 6.2 Set Large Icon - optional
            .setContentTitle(data.mTitle) // 6.3 Set Content Title - MUST
            .setContentText(data.mDescription) // 6.3 Set Content Text - MUST
            .setAutoCancel(true) // 6.4 notification is automatically canceled when the user clicks it in the panel
            .setSound(defaultSoundUri) // 6.5 Set sound
            .setContentIntent(pendingIntent) // 6.6 set pending intent - IMPORTANT
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .build()*/
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_abeja_naranja)
            .setContentTitle(data.mTitle)
            .setContentText(data.mDescription)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(pendingIntent)
            .build()
      /*  val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1002, notificationBuilder)*/

        // 7. With notification manager, send above notification using above object object
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("Nutrisa Supervisión",
                "Nutrisa Push Notifications",
                NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder)
    }

    fun getDataFirebase(): LiveData<MutableList<PushNotification>> {
        dataList?.let { notificacion.add(it) }
        // FcmFirebaseActivity.Events.serviceEvent.postValue(notificacion)
        val dataMutable= miClase.mutableData
        dataMutable.postValue(notificacion)
        return dataMutable
    }
    private fun sendNewPromoBroadcast(remoteMessage: RemoteMessage) {
        val intent = Intent().apply {
            action= INTENT_ACTION_SEND_MESSAGE
            putExtra("title", remoteMessage.notification!!.title)
            putExtra("description", remoteMessage.notification!!.body)
            putExtra("fecha", remoteMessage.data["fecha"])
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
        //LocalBroadcastManager.getInstance(applicationContext).sendBroadcast(intent)
    }

    private fun displayNotification(notification: RemoteMessage.Notification, data: Map<String, String>
    ) {
        val intent = Intent(this, FcmFirebaseActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.ic_abeja_naranja)
            .setContentTitle(notification.title)
            .setContentText(notification.body)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(pendingIntent)
            .build()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1002, notificationBuilder)
    }

    companion object{
        const val INTENT_ACTION_SEND_MESSAGE = "INTENT_ACTION_SEND_MESSAGE"
    }
}

