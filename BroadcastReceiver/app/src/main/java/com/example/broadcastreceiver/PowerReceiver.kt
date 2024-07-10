package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class PowerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_POWER_CONNECTED -> {
                Toast.makeText(context, "Power Connected", Toast.LENGTH_SHORT).show()
            }
            Intent.ACTION_POWER_DISCONNECTED -> {
                Toast.makeText(context, "Power Disconnected", Toast.LENGTH_SHORT).show()
            }
            "com.example.CUSTOM_BROADCAST" -> {
                Toast.makeText(context, "Custom Broadcast Received", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
