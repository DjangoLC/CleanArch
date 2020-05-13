package com.example.cleanarchme.data

import android.content.Context
import com.example.data.UserPreferences

class UserPreferencesImpl(context: Context) : UserPreferences {

    private val preferences =
        context.getSharedPreferences(UserPreferences.PREFERENCES, Context.MODE_PRIVATE)

    override fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    override fun getString(key: String): String {
        return preferences.getString(key,"")!!
    }

    override fun saveBoolean(key: String, boolean: Boolean) {
        with(preferences.edit()) {
            putBoolean(key, boolean)
            apply()
        }
    }

    override fun saveString(key: String, string: String) {
        with(preferences.edit()) {
            putString(key, string)
            apply()
        }
    }
}