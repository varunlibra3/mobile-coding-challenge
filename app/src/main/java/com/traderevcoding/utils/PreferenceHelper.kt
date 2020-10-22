package com.traderevcoding.utils

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {
    lateinit var preferences: SharedPreferences
    fun init(context: Context) {
        preferences = context.getSharedPreferences("SharedPref", Context.MODE_PRIVATE)
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    fun setInt(key: String, value: Int) {
        with(preferences.edit()) {
            putInt(key, value)
            apply()
        }
    }
}