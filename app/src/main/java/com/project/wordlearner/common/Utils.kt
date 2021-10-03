
package com.project.wordlearner.common

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.google.gson.GsonBuilder
import java.io.IOException

private const val TAG = "Utils"


fun getQuestionsListFromJsonString(json: String?): List<Data> {
    return GsonBuilder().create().fromJson(json, Array<Data>::class.java).toList()
}

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        Log.d(TAG, "getJsonDataFromAsset: $jsonString")
    } catch (ioException: IOException) {
        Log.e(TAG, "getJsonDataFromAsset: ", ioException)
        ioException.printStackTrace()
        return null
    }
    return jsonString
}

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    var result = false

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    } else {
        connectivityManager.run {
            connectivityManager.activeNetworkInfo?.run {
                result = when (type) {
                    ConnectivityManager.TYPE_WIFI -> true
                    ConnectivityManager.TYPE_MOBILE -> true
                    ConnectivityManager.TYPE_ETHERNET -> true
                    else -> false
                }
            }
        }
    }

    return result
}


