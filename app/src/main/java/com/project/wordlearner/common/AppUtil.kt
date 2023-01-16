package com.project.wordlearner.common

import android.os.SystemClock

object AppUtil {
    var mLastClickTime = 0L

    fun isOpenRecently(): Boolean {
        if(SystemClock.elapsedRealtime() - mLastClickTime < 2200){
            return true
        }
        mLastClickTime = SystemClock.elapsedRealtime()
        return false
    }

    var lastClickTime = 0L
    fun isOpenLastSecond(): Boolean {
        if(SystemClock.elapsedRealtime() - lastClickTime < 1000){
            return true
        }
        lastClickTime = SystemClock.elapsedRealtime()
        return false
    }



}