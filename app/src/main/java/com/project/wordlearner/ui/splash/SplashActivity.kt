package com.project.wordlearner.ui.splash

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.google.gson.GsonBuilder
import com.project.wordlearner.R
import com.project.wordlearner.data.db.AppDatabase
import com.project.wordlearner.data.models.Data
import com.project.wordlearner.data.preference.AppSharedPref
import com.project.wordlearner.data.repositories.DataRepo
import com.project.wordlearner.ui.dashboard.DashboardViewModel
import com.project.wordlearner.ui.main.MainActivity
import java.io.IOException

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "SplashActivity"
    }

    private lateinit var appSharedPref: AppSharedPref
    private lateinit var appDatabase: AppDatabase
    private lateinit var dataRepo: DataRepo
    private lateinit var factory: SplashViewModelFactory
    private lateinit var viewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        appSharedPref = AppSharedPref(this)
        appDatabase = AppDatabase.getAppDataBase(this)!!
        dataRepo = DataRepo(appDatabase)
        factory = SplashViewModelFactory(dataRepo)
        viewModel = ViewModelProvider(this, factory).get(SplashViewModel::class.java)


        getData()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }


    private fun getData() {
        val agentsJsonFileString = getJsonDataFromAsset(applicationContext, "data.json")
        val list = getQuestionsListFromJsonString(agentsJsonFileString).toMutableList()
        Log.d("jjj", "getData: ${list.size}")

        viewModel.storeQBFromLocal(list)
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