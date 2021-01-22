package com.example.nutrisaapplication.ui.main.notificacion

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.work.ListenableWorker
import androidx.work.Operation.SUCCESS
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.nutrisaapplication.R


private const val LOG_TAG = "UploadWorker"
private const val KEY_ZIP_PATH = "ZIP_PATH"

class UploadWorker(context: Context, workerParams: WorkerParameters) : Worker(context, workerParams) {

    val mContext = context
    override fun doWork(): ListenableWorker.Result {
        sendNotification()
        return ListenableWorker.Result.success()
    }

    fun sendNotification() {
        val notificationManager = mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "WorkManager_00"
        //If on Oreo then notification required a notification channel.
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, "WorkManager", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        val notification = NotificationCompat.Builder(mContext, channelId)
            .setContentTitle("Single Worker")
            .setContentText("This notification is from Single Worker!!")
            .setSmallIcon(R.drawable.ic_abeja_naranja)

        notificationManager.notify(1, notification.build())
    }
}