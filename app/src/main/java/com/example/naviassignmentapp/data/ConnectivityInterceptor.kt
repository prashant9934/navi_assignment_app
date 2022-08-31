package com.example.naviassignmentapp.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.naviassignmentapp.data.exceptions.NoConnectivityException
import com.example.naviassignmentapp.ui.NaviAssignmentApplication
import okhttp3.Interceptor
import okhttp3.Response
import java.net.InetAddress


class ConnectivityInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isNetworkConnected() || !isInternetAvailable()) {
            throw NoConnectivityException()
        } else {
            chain.proceed(chain.request())
        }
    }
}

fun isInternetAvailable(): Boolean {
    return try {
        val ipAddress = InetAddress.getByName("google.com")
        !ipAddress.equals("")
    } catch (e: Exception) {
        false
    }
}

private fun isNetworkConnected(): Boolean {
    val connectivityManager = NaviAssignmentApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val nw = connectivityManager?.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        val nwInfo = connectivityManager?.activeNetworkInfo ?: return false
        return nwInfo.isConnected
    }
}