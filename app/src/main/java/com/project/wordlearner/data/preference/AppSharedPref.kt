package com.project.wordlearner.data.preference

import android.content.Context
import com.okcodex.mentalmathmaster.data.preference.KPSettings
import com.project.wordlearner.common.AppConstants


class AppSharedPref(context: Context) {

    private val kpSettings: KPSettings = KPSettings.getInstance(context)!!


    fun setDbUpdateNeeded(value: Boolean) {
        kpSettings.setBoolValue(AppConstants.DB_UPDATE_NEEDED, value)
    }

    fun isDbUpdateNeeded(): Boolean {
        return kpSettings.getBoolValue(AppConstants.DB_UPDATE_NEEDED, true)
    }


    fun setId(value: Int) {
        kpSettings.setIntValue(AppConstants.ID, value)
    }

    fun getId(): Int {
        return kpSettings.getIntValue(AppConstants.ID, -1)
    }


    fun setTodayWordList(value: String) {
        kpSettings.setStringValue(AppConstants.TODAY_WORD_LIST, value)
    }

    fun getTodayWordList(): String {
        return kpSettings.getStringValue(AppConstants.TODAY_WORD_LIST, "[]")
    }

    fun setWord(value: String) {
        kpSettings.setStringValue(AppConstants.WORD, value)
    }

    fun getWord(): String {
        return kpSettings.getStringValue(AppConstants.WORD, "")
    }


    fun setTodayDate(value: String) {
        kpSettings.setStringValue(AppConstants.TODAY_DATE, value)
    }

    fun getTodayDate(): String {
        return kpSettings.getStringValue(AppConstants.TODAY_DATE, "")
    }


    companion object {
        private var appSharedPref: AppSharedPref? = null
        fun getInstance(context: Context): AppSharedPref? {
            if (appSharedPref == null) {
                appSharedPref = AppSharedPref(context)
            }
            return appSharedPref
        }
    }
}