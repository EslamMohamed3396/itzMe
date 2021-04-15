package com.itzme.utilits.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.itzme.R
import com.itzme.ui.activity.main.MainActivity
import java.util.*


class NotificationService : FirebaseMessagingService() {

    var TAG = "NotificationService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        try {

            val map = remoteMessage.data
            val notification = remoteMessage.notification

            var title: String? = ""
            var body: String? = ""
            if (notification != null) {
                if (notification.title != null && !notification.title!!.isEmpty()) title =
                        notification.title
                if (notification.body != null && !notification.body!!.isEmpty()) body =
                        notification.body
            } else if (map.isNotEmpty()) {
                if (map.containsKey("Title") || map.containsKey("title")) title =
                        getElementByIndex(map, 1) as String?
                if (map.containsKey("Body") || map.containsKey("body")) body =
                        getElementByIndex(map, 0) as String?
            }


            sendNotification(body, title)
        } catch (ignored: Exception) {

        }
    }


    private fun sendNotification(
            messageBody: String?,
            title: String?
    ) {
        val GROUP_KEY = "com.itzme"
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)


        val pendingIntent = PendingIntent.getActivity(
                this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )
        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder =
                NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.dark_logo)
                        .setContentTitle(title)
                        .setAutoCancel(true)
                        .setPriority(NotificationManagerCompat.IMPORTANCE_MAX)
                        .setContentText(messageBody)
                        .setGroup(GROUP_KEY)
                        .setGroupSummary(true)
                        .setLights(Color.RED, 3000, 3000)
                        .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
        val notificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                    channelId,
                    title,
                    NotificationManager.IMPORTANCE_HIGH
            )
            channel.description = messageBody
            channel.enableLights(true)
            channel.setSound(defaultSoundUri, attributes)
            channel.lightColor = Color.RED
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            channel.importance = NotificationManager.IMPORTANCE_HIGH
            channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
            channel.enableVibration(true)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(0, notificationBuilder.build())
    }

    fun getElementByIndex(map: Map<String, String>, index: Int): Any? {
        return map[Objects.requireNonNull<Array<Any>>(map.keys.toTypedArray())[index]]
    }


}
