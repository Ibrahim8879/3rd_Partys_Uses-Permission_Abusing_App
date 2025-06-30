package com.zin.poc_password

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.util.Log

class ReadAndDisplayContanctsActivity : Activity() {
    // URI to store contacts uri
    private var contactsUri: Uri? = null

    // TextView to display contacts information in the UI
    private var textView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // Set the layout for the activity
        setContentView(R.layout.readactivity)


        // Initialize the TextView for displaying contacts
        textView = findViewById<TextView>(R.id.contacts)


        // Retrieve the intent that started the activity
        val intent = intent


        // Extract the ClipData from the intent, if available
        val clipData = intent.clipData


        // Set the URI to the first item in the ClipData, if available
        contactsUri = clipData!!.getItemAt(0).uri

        // StringBuilder to store contact information
        val contactsInfo = StringBuilder()

        // Cursor to query the media provider for contact information
        val uri = contactsUri ?: return
        val view = textView ?: return

        val cursor = contentResolver.query(uri, null, null, null, null)

        cursor?.use {
            while (it.moveToNext()) {
                val nameIndex = it.getColumnIndex("name")
                val numberIndex = it.getColumnIndex("number")

                if (nameIndex >= 0 && numberIndex >= 0) {
                    val name = it.getString(nameIndex)
                    val number = it.getString(numberIndex)

                    contactsInfo.append(name).append(": ").append(number).append("\n")
                } else {
                    Log.w(
                        "VULN-ZIN-POC",
                        "Invalid column name(s) — check your projection or content provider schema."
                    )
                }
            }
        }

        view.text = contactsInfo.toString()

    }
}