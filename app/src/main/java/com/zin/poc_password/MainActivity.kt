package com.zin.poc_password

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // Button for opening notification listener settings
    var btnNotif: Button? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.poc_password_manager)


        // Find the Button with ID R.id.notif
        val button = findViewById<Button>(R.id.notif)
        this.btnNotif = button

        // Set click listener for the button
        button.setOnClickListener { // Create an intent to open Notification Listener settings
            val intent =
                Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS")

            // Start the activity to open Notification Listener settings
            this@MainActivity.startActivity(intent)
        }
    }
}