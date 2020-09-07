package com.amo.motherfinancetest.datasource.pref

import android.app.Application
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import javax.inject.Inject

interface Pref {

    fun getAppId(): String?
    fun setAppId(id: String)

    fun getSecretKey(): String?
    fun setSecretKey(key: String)

}

class MyPref @Inject constructor(context: Application) : Pref {

    companion object {
        const val PREF_APP_ID = "PREF_APP_ID"
        const val PREF_SECRET_KEY = "PREF_SECRET_KEY"
    }

    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    override fun getAppId(): String? {
        return pref.getString(PREF_APP_ID, "")
    }

    override fun setAppId(id: String) {
        pref.edit { putString(PREF_APP_ID, id) }
    }

    override fun getSecretKey(): String? {
        return pref.getString(PREF_SECRET_KEY, "")
    }

    override fun setSecretKey(key: String) {
        pref.edit { putString(PREF_SECRET_KEY, key) }
    }

}