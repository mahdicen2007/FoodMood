package com.mahdicen.foodmood.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class NetworkChecker(private val context: Context) {

    val isInternetConnected :Boolean
        get() {

            var result = false

            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                    as ConnectivityManager
            val networkCapabilities = connectivityManager.activeNetwork ?: return false

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                val myNetwork =
                    connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

                result = when {
                    myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                    myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                    myNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                    else -> false
                }


            } else {

                result = when(connectivityManager.activeNetworkInfo?.type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }

            }

            return result

        }

}