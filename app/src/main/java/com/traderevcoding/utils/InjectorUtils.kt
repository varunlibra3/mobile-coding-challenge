package com.traderevcoding.utils

import com.traderevcoding.api.APIHelper
import com.traderevcoding.api.RetrofitBuilder
import com.traderevcoding.viewmodels.PhotoGridViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {
    private val apiHelper = APIHelper(RetrofitBuilder.apiService)
    fun providePhotoGridViewModelFactory(): PhotoGridViewModelFactory {
        return PhotoGridViewModelFactory(apiHelper)
    }
}
