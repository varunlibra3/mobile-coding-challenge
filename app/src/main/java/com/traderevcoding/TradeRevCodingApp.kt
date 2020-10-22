package com.traderevcoding

import android.app.Application
import com.traderevcoding.utils.PreferenceHelper

class TradeRevCodingApp : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)
    }
}