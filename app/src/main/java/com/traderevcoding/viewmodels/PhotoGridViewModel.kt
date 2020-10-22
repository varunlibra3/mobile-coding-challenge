package com.traderevcoding.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.traderevcoding.api.Resource
import com.traderevcoding.data.UnsplashPhoto
import kotlinx.coroutines.launch

class PhotoGridViewModel internal constructor(
    private val repository: PhotoGridRepository
) : ViewModel() {
    private val liveData = MutableLiveData<Resource<ArrayList<UnsplashPhoto>>>()
    val photoList = ArrayList<UnsplashPhoto>()
    private var page = 1

    init {
        loadPhotos()
    }

    fun loadPhotos() {
        viewModelScope.launch {
            liveData.postValue(Resource.loading(null))
            try {
                photoList.addAll(repository.getPhotos(page++))
                liveData.postValue(Resource.success(data = photoList))
            } catch (e: Exception) {
                liveData.postValue(Resource.networkError(exception = e))
            }
        }
    }

    fun getAllPhotos(): LiveData<Resource<ArrayList<UnsplashPhoto>>> {
        return liveData
    }
}
