package com.traderevcoding.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.traderevcoding.R
import com.traderevcoding.utils.PreferenceHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PreferenceHelper.setInt("imagePosition", 0)
    }
}