package com.okcodex.mentalmathmaster.data.preference

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.text.TextUtils

class KPSettings/*
     * A private Constructor prevents any other class from instantiating.
     */
private constructor(private val mContext: Context) {

    val preference: SharedPreferences
    private var mEditor: SharedPreferences.Editor? = null
    private var mEditorOpened = false

    val preferenceEditor: SharedPreferences.Editor?
        get() {
            if (mEditorOpened) {
                return mEditor
            }
            mEditorOpened = true
            mEditor = preference.edit()
            return mEditor
        }

    init {
        preference = getSharedPreferenceFile(mContext, "new_mypref")
    }

    /**
     * if a prefFilename is not defined the getDefaultSharedPreferences is used.
     *
     * @param context should be ApplicationContext not Activity
     * @return
     */
    private fun getSharedPreferenceFile(context: Context, prefFilename: String): SharedPreferences {
        return if (TextUtils.isEmpty(prefFilename)) {
            PreferenceManager
                .getDefaultSharedPreferences(context)
        } else {
            context.getSharedPreferences(prefFilename, Context.MODE_PRIVATE)
        }
    }

    fun commitPreferenceEditor() {
        try {
            mEditor!!.commit()
        } catch (e: Exception) {
            // TODO: handle exception
        }

        mEditorOpened = false
    }

    // string
    fun setStringValue(key: String, `val`: String) {
        // TODO Auto-generated method stub
        try {
            mEditor!!.putString(getSecureText(key), getSecureText(`val`))
        } catch (e: Exception) {
            // TODO: handle exception
            val editor = preference.edit()
            editor.putString(getSecureText(key), getSecureText(`val`))
            editor.commit()
        }

    }

    fun getStringValue(key: String): String {
        val defff = preference.getString(getSecureText(key), "")
        return if (defff!!.equals("", ignoreCase = true)) {
            defff
        } else {
            getPlainText(defff)
        }
    }

    fun getStringValue(key: String, defaultstring: String): String {
        // TODO Auto-generated method stub)
        val defff = preference.getString(getSecureText(key), defaultstring)
        return if (defff!!.equals(defaultstring, ignoreCase = true)) {
            defff
        } else {
            getPlainText(defff)
        }
    }

    // int
    fun getIntValue(key: String): Int {
        // TODO Auto-generated method stub
        return preference.getInt(getSecureText(key), 0)
    }

    // int
    fun getIntValue(key: String, defaultvalue: Int): Int {
        // TODO Auto-generated method stub
        return preference.getInt(getSecureText(key), defaultvalue)
    }

    fun setIntValue(key: String, `val`: Int) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putInt(getSecureText(key), `val`)
        editor.commit()
    }

    // long
    fun getLongValue(key: String): Long {
        // TODO Auto-generated method stub
        return preference.getLong(getSecureText(key), 0)
    }

    // long
    fun getLongValue(key: String, defaultvalue: Long): Long {
        // TODO Auto-generated method stub
        return preference.getLong(getSecureText(key), defaultvalue)
    }

    fun setLongValue(key: String, `val`: Long) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putLong(getSecureText(key), `val`)
        editor.commit()
    }


    // float
    fun getFloatValue(key: String): Float {
        // TODO Auto-generated method stub
        return preference.getFloat(getSecureText(key), -1.0f)
    }

    fun getFloatValue(key: String, `val`: Float): Float {
        // TODO Auto-generated method stub
        return preference.getFloat(getSecureText(key), `val`)
    }

    fun setFloatValue(key: String, `val`: Float) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putFloat(getSecureText(key), `val`)
        editor.commit()
    }

    // double
    fun getDoubleValue(key: String): Float {
        // TODO Auto-generated method stub
        return preference.getFloat(getSecureText(key), -1.0f)
    }

    fun setDoubleValue(key: String, `val`: Double) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putFloat(getSecureText(key), `val`.toFloat())
        editor.commit()
    }

    // boolean
    fun setBoolValue(key: String, b: Boolean) {
        // TODO Auto-generated method stub
        val editor = preference.edit()
        editor.putBoolean(getSecureText(key), b)
        editor.commit()
    }

    fun removeKeyValue(key: String) {
        try {
            mEditor!!.remove(getSecureText(key))
        } catch (e: Exception) {
            // TODO: handle exception
            val editor = preference.edit()
            editor.remove(getSecureText(key))
            editor.commit()
        }

    }


    fun onResetExcept(data: MutableMap<String, Any>) {
        val editor = preference.edit()
        editor.clear()

        val it = data.entries.iterator()
        while (it.hasNext()) {
            val pair = it.next() as MutableMap.MutableEntry<String, Any>

            val itemObject = pair.value
            if (itemObject is Int) {
                this.setIntValue(getSecureText(pair.key), itemObject as Int)
            } else if (itemObject is String) {
                this.setStringValue(getSecureText(pair.key), getSecureText(itemObject as String))
            } else if (itemObject is Boolean) {
                this.setBoolValue(getSecureText(pair.key), itemObject as Boolean)
            } else if (itemObject is Long) {
                this.setLongValue(getSecureText(pair.key), itemObject as Long)
            } else if (itemObject is Double) {
                this.setDoubleValue(getSecureText(pair.key), itemObject as Double)
            }

            it.remove() // avoids a ConcurrentModificationException
        }
        editor.commit()
    }

    fun getBoolValue(key: String): Boolean {
        return preference.getBoolean(getSecureText(key), false)
    }

    fun getBoolValue(key: String, defaultvalue: Boolean): Boolean {
        // TODO Auto-generated method stub
        return preference.getBoolean(getSecureText(key), defaultvalue)
    }

    private fun getSecureText(plain_data: String): String {
        return plain_data
    }

    private fun getPlainText(encrypted_data: String): String {
        return encrypted_data
    }

    companion object {
        private var appSettingSingleton: KPSettings? = null


        /* Static 'instance' method */
        fun getInstance(_mContext: Context): KPSettings? {
            if (appSettingSingleton == null)
                appSettingSingleton =
                    KPSettings(
                        _mContext
                    )


                return appSettingSingleton
        }
    }
}