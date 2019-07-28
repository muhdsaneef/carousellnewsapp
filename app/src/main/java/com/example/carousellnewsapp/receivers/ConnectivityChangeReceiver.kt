package com.example.carousellnewsapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import javax.inject.Inject

class ConnectivityChangeReceiver @Inject constructor() : BroadcastReceiver() {

    var connectionChangeListener: ((isConnected: Boolean) -> Unit)? = null

    override fun onReceive(context: Context?, intent: Intent?) {
        context?.let {
            val connectivityManager = it.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            connectionChangeListener?.invoke(networkInfo != null && networkInfo.isConnected)
        }
    }

}