package com.example.numberchecker.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class InternetConnectionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val isConnecting = intent?.getBooleanExtra("state",false) ?: return

        if (isConnecting){
            Toast.makeText(context,"Connected", Toast.LENGTH_SHORT)
        }else{
            Toast.makeText(context,"Not connected", Toast.LENGTH_SHORT)

        }
    }
}