package com.traderevcoding.viewmodels

import com.traderevcoding.api.APIHelper
import com.traderevcoding.data.UnsplashPhoto

class PhotoGridRepository(val apiHelper: APIHelper) {
    suspend fun getPhotos(page: Int): List<UnsplashPhoto> {
        return apiHelper.getLatestPhotos(page, perPage = PAGE_SIZE)
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}