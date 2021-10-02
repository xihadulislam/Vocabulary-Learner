package com.project.wordlearner

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import java.io.IOException

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        getData()

    }

    private fun getData() {
        val agentsJsonFileString = getJsonDataFromAsset(applicationContext, "data.json")
        val list = getQuestionsListFromJsonString(agentsJsonFileString).toMutableList()
        Log.d("jjj", "getData: ${list.size}")
        Log.d("jjj", "getData: "+list[2].bn)
        Log.d("jjj", "getData: "+list[2].en)
        Log.d("jjj", "getData: "+list[2].pron)
    }


    private fun getQuestionsListFromJsonString(json: String?): List<Data> {
        return GsonBuilder().create().fromJson(json, Array<Data>::class.java).toList()
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String? {
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


}


