package com.example.kino.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.core.content.ContextCompat

class NetworkConnectionChecker(ctx: Context, listener: NetworkServiceListener) {
    companion object {
        private const val TAG: String = "NETWORK_SERVICE"
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        // network is available for use
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            listener.onChangeNetworkStatus(true)
        }

        // Network capabilities have changed for the network
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            super.onCapabilitiesChanged(network, networkCapabilities)
            val unmetered =
                networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
            // Log.d(TAG, "${unmetered}")
        }

        // lost network connection
        override fun onLost(network: Network) {
            super.onLost(network)
            listener.onChangeNetworkStatus(false)
        }
    }

    init {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        val connectivityManager =
            ContextCompat.getSystemService(
                ctx,
                ConnectivityManager::class.java
            ) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }

    interface NetworkServiceListener {
        fun onChangeNetworkStatus(status: Boolean)
    }
}


// class NetworkConnectionChecker(ctx: Context, listener: ((Boolean) -> Unit)) {
//     companion object {
//         private const val TAG: String = "NETWORK_SERVICE"
//     }
//
//     private val networkCallback = object : ConnectivityManager.NetworkCallback() {
//         // network is available for use
//         override fun onAvailable(network: Network) {
//             super.onAvailable(network)
//             listener(true)
//         }
//
//         // Network capabilities have changed for the network
//         override fun onCapabilitiesChanged(
//             network: Network,
//             networkCapabilities: NetworkCapabilities
//         ) {
//             super.onCapabilitiesChanged(network, networkCapabilities)
//             val unmetered =
//                 networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
//             // Log.d(TAG, "${unmetered}")
//         }
//
//         // lost network connection
//         override fun onLost(network: Network) {
//             super.onLost(network)
//             listener(false)
//         }
//     }
//
//     init {
//         val networkRequest = NetworkRequest.Builder()
//             .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
//             .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
//             .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
//             .build()
//
//         val connectivityManager =
//             ContextCompat.getSystemService(
//                 ctx,
//                 ConnectivityManager::class.java
//             ) as ConnectivityManager
//         connectivityManager.requestNetwork(networkRequest, networkCallback)
//     }
//
//     interface NetworkServiceListener {
//         fun onChangeNetworkStatus(status: Boolean)
//     }
// }