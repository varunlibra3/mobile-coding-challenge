package com.traderevcoding.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.traderevcoding.api.APIHelper

class PhotoGridViewModelFactory(val apiHelper: APIHelper) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoGridViewModel::class.java)) {
            return PhotoGridViewModel(
                repository = PhotoGridRepository(
                    apiHelper = apiHelper
                )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}