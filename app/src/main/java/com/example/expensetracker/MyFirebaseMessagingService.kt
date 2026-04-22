package com.example.expensetracker

import android.annotation.SuppressLint
import android.app.AppComponentFactory
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class MyFirebaseMessagingService:FirebaseMessagingService(){


    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val title = message.notification?.title

        val message = message.notification?.body

        shownotification(title,message)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        Log.d("TAG", "onNewToken: ${token}")

    }

    @SuppressLint("SuspiciousIndentation")
    private fun shownotification(title: String?, message: String?) {

        val channelId = "fcm_channel"

        val notificationmanager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = NotificationCompat.Builder(this,channelId)

            .setContentTitle(title)

            .setContentText(message)

            .setSmallIcon(R.drawable.ic_launcher_foreground)

            .build()

           if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

              val channel = NotificationChannel(

                  channelId,"WARNING",

                  NotificationManager.IMPORTANCE_HIGH
              )

               notificationmanager.createNotificationChannel(channel)

           }

           notificationmanager.notify(0,notification)

    }

}

