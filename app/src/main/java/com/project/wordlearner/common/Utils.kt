package com.project.wordlearner.common

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.GsonBuilder
import com.project.wordlearner.data.models.Word
import java.io.IOException
import java.util.*

private const val TAG = "Utils"


fun getQuestionsListFromJsonString(json: String?): List<Word> {
    return GsonBuilder().create().fromJson(json, Array<Word>::class.java).toList()
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


fun String.capitalizeWords() = split(' ').joinToString(" ", transform = String::capitalize)


fun TextView.setHtmlText(string: String?) {
    text = HtmlCompat.fromHtml(string ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
}


fun getToday(): String {
    val c = Calendar.getInstance().time
    val df = java.text.SimpleDateFormat("yyyy_MM_dd", Locale.US)
    return df.format(c)
}


fun View.showKeyboard(
) {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
            InputMethodManager
    imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun View.hideKeyboard(
) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as
            InputMethodManager
    imm.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.isKeyboardVisible(): Boolean = ViewCompat.getRootWindowInsets(this)
    ?.isVisible(WindowInsetsCompat.Type.ime())
    ?: false
