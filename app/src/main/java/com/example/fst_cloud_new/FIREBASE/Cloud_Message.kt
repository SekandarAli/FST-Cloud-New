package com.example.fst_cloud_new.FIREBASE

import android.app.NotificationManager
import android.content.Context
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.example.fst_cloud_new.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class Cloud_Message : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
    }

    override fun onMessageReceived(p0: RemoteMessage) {
        if(p0.notification != null)
        {
            showNotification(p0.notification!!.title,p0.notification!!.body)
        }
    }

    private fun showNotification(title: String?, body: String?) {

        val sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.common_google_signin_btn_text_dark)
            .setContentText(title)
            .setContentText(body)
            .setSound(sound)
            .setAutoCancel(true)

        val notifyManager = getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        notifyManager.notify(0,notification.build())

    }



}